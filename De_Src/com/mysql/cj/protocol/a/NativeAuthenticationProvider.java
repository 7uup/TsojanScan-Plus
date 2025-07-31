/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.ServerSession;
import com.mysql.cj.protocol.a.NativeCapabilities;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.authentication.CachingSha2PasswordPlugin;
import com.mysql.cj.protocol.a.authentication.MysqlClearPasswordPlugin;
import com.mysql.cj.protocol.a.authentication.MysqlNativePasswordPlugin;
import com.mysql.cj.protocol.a.authentication.MysqlOldPasswordPlugin;
import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;
import com.mysql.cj.protocol.a.result.OkPacket;
import com.mysql.cj.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class NativeAuthenticationProvider
implements AuthenticationProvider<NativePacketPayload> {
    protected static final int AUTH_411_OVERHEAD = 33;
    private static final String NONE = "none";
    protected String seed;
    private boolean useConnectWithDb;
    private ExceptionInterceptor exceptionInterceptor;
    private PropertySet propertySet;
    private Protocol<NativePacketPayload> protocol;
    private Log log;
    private Map<String, AuthenticationPlugin<NativePacketPayload>> authenticationPlugins = null;
    private List<String> disabledAuthenticationPlugins = null;
    private String clientDefaultAuthenticationPlugin = null;
    private String clientDefaultAuthenticationPluginName = null;
    private String serverDefaultAuthenticationPluginName = null;

    public NativeAuthenticationProvider(Log log) {
        this.log = log;
    }

    @Override
    public void init(Protocol<NativePacketPayload> prot, PropertySet propSet, ExceptionInterceptor excInterceptor) {
        this.protocol = prot;
        this.propertySet = propSet;
        this.exceptionInterceptor = excInterceptor;
    }

    @Override
    public void connect(ServerSession sessState, String user, String password, String database) {
        StringBuilder newSeed;
        String seedPart2;
        long clientParam = sessState.getClientParam();
        NativeCapabilities capabilities = (NativeCapabilities)sessState.getCapabilities();
        NativePacketPayload buf = capabilities.getInitialHandshakePacket();
        this.seed = capabilities.getSeed();
        sessState.setServerDefaultCollationIndex(capabilities.getServerDefaultCollationIndex());
        sessState.setStatusFlags(capabilities.getStatusFlags());
        int capabilityFlags = capabilities.getCapabilityFlags();
        if ((capabilityFlags & 0x8000) != 0) {
            clientParam |= 0x8000L;
            int authPluginDataLength = capabilities.getAuthPluginDataLength();
            if (authPluginDataLength > 0) {
                seedPart2 = buf.readString(NativeConstants.StringLengthDataType.STRING_FIXED, "ASCII", authPluginDataLength - 8);
                newSeed = new StringBuilder(authPluginDataLength);
            } else {
                seedPart2 = buf.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII");
                newSeed = new StringBuilder(20);
            }
        } else {
            throw ExceptionFactory.createException(UnableToConnectException.class, "CLIENT_SECURE_CONNECTION is required", this.getExceptionInterceptor());
        }
        newSeed.append(this.seed);
        newSeed.append(seedPart2);
        this.seed = newSeed.toString();
        if ((capabilityFlags & 0x20) != 0 && this.propertySet.getBooleanProperty("useCompression").getValue().booleanValue()) {
            clientParam |= 0x20L;
        }
        boolean bl = this.useConnectWithDb = database != null && database.length() > 0 && this.propertySet.getBooleanProperty("createDatabaseIfNotExist").getValue() == false;
        if (this.useConnectWithDb) {
            clientParam |= 8L;
        }
        RuntimeProperty<Boolean> useSSL = this.propertySet.getProperty("useSSL");
        if (this.protocol.versionMeetsMinimum(5, 7, 0) && !((Boolean)useSSL.getValue()).booleanValue() && !useSSL.isExplicitlySet()) {
            useSSL.setValue(true);
            this.propertySet.getProperty("verifyServerCertificate").setValue(false);
            if (this.log != null) {
                this.log.logWarn(Messages.getString("MysqlIO.SSLWarning"));
            }
        }
        RuntimeProperty<Boolean> useInformationSchema = this.propertySet.getProperty("useInformationSchema");
        if (this.protocol.versionMeetsMinimum(8, 0, 3) && !((Boolean)useInformationSchema.getValue()).booleanValue() && !useInformationSchema.isExplicitlySet()) {
            useInformationSchema.setValue(true);
        }
        if ((capabilityFlags & 0x800) == 0 && ((Boolean)useSSL.getValue()).booleanValue()) {
            if (this.propertySet.getBooleanProperty("requireSSL").getValue().booleanValue()) {
                throw ExceptionFactory.createException(UnableToConnectException.class, Messages.getString("MysqlIO.15"), this.getExceptionInterceptor());
            }
            useSSL.setValue(false);
        }
        if ((capabilityFlags & 4) != 0) {
            clientParam |= 4L;
            sessState.setHasLongColumnInfo(true);
        }
        if (!this.propertySet.getBooleanProperty("useAffectedRows").getValue().booleanValue()) {
            clientParam |= 2L;
        }
        if (this.propertySet.getBooleanProperty("allowLoadLocalInfile").getValue().booleanValue()) {
            clientParam |= 0x80L;
        }
        if (this.propertySet.getBooleanProperty("interactiveClient").getValue().booleanValue()) {
            clientParam |= 0x400L;
        }
        if ((capabilityFlags & 0x800000) != 0) {
            // empty if block
        }
        if ((capabilityFlags & 0x1000000) != 0) {
            clientParam |= 0x1000000L;
        }
        if ((capabilityFlags & 0x80000) == 0) {
            throw ExceptionFactory.createException(UnableToConnectException.class, "CLIENT_PLUGIN_AUTH is required", this.getExceptionInterceptor());
        }
        sessState.setClientParam(clientParam);
        this.proceedHandshakeWithPluggableAuthentication(sessState, user, password, database, buf);
    }

    private void loadAuthenticationPlugins() {
        this.clientDefaultAuthenticationPlugin = this.propertySet.getStringProperty("defaultAuthenticationPlugin").getValue();
        if (this.clientDefaultAuthenticationPlugin == null || "".equals(this.clientDefaultAuthenticationPlugin.trim())) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.BadDefaultAuthenticationPlugin", new Object[]{this.clientDefaultAuthenticationPlugin}), this.getExceptionInterceptor());
        }
        String disabledPlugins = this.propertySet.getStringProperty("disabledAuthenticationPlugins").getValue();
        if (disabledPlugins != null && !"".equals(disabledPlugins)) {
            this.disabledAuthenticationPlugins = new ArrayList<String>();
            List<String> pluginsToDisable = StringUtils.split(disabledPlugins, ",", true);
            Iterator<String> iter = pluginsToDisable.iterator();
            while (iter.hasNext()) {
                this.disabledAuthenticationPlugins.add(iter.next());
            }
        }
        this.authenticationPlugins = new HashMap<String, AuthenticationPlugin<NativePacketPayload>>();
        boolean defaultIsFound = false;
        LinkedList<AuthenticationPlugin> pluginsToInit = new LinkedList<AuthenticationPlugin>();
        pluginsToInit.add(new MysqlNativePasswordPlugin());
        pluginsToInit.add(new MysqlClearPasswordPlugin());
        pluginsToInit.add(new Sha256PasswordPlugin());
        pluginsToInit.add(new CachingSha2PasswordPlugin());
        pluginsToInit.add(new MysqlOldPasswordPlugin());
        String authenticationPluginClasses = this.propertySet.getStringProperty("authenticationPlugins").getValue();
        if (authenticationPluginClasses != null && !"".equals(authenticationPluginClasses)) {
            List<String> pluginsToCreate = StringUtils.split(authenticationPluginClasses, ",", true);
            String className = null;
            try {
                int s2 = pluginsToCreate.size();
                for (int i = 0; i < s2; ++i) {
                    className = pluginsToCreate.get(i);
                    pluginsToInit.add((AuthenticationPlugin)Class.forName(className).newInstance());
                }
            } catch (Throwable t) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.BadAuthenticationPlugin", new Object[]{className}), t, this.exceptionInterceptor);
            }
        }
        for (AuthenticationPlugin plugin : pluginsToInit) {
            plugin.init(this.protocol);
            if (!this.addAuthenticationPlugin(plugin)) continue;
            defaultIsFound = true;
        }
        if (!defaultIsFound) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.DefaultAuthenticationPluginIsNotListed", new Object[]{this.clientDefaultAuthenticationPlugin}), this.getExceptionInterceptor());
        }
    }

    private boolean addAuthenticationPlugin(AuthenticationPlugin<NativePacketPayload> plugin) {
        boolean disabledByMechanism;
        boolean isDefault = false;
        String pluginClassName = plugin.getClass().getName();
        String pluginProtocolName = plugin.getProtocolPluginName();
        boolean disabledByClassName = this.disabledAuthenticationPlugins != null && this.disabledAuthenticationPlugins.contains(pluginClassName);
        boolean bl = disabledByMechanism = this.disabledAuthenticationPlugins != null && this.disabledAuthenticationPlugins.contains(pluginProtocolName);
        if (disabledByClassName || disabledByMechanism) {
            if (this.clientDefaultAuthenticationPlugin.equals(pluginClassName)) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.BadDisabledAuthenticationPlugin", new Object[]{disabledByClassName ? pluginClassName : pluginProtocolName}), this.getExceptionInterceptor());
            }
        } else {
            this.authenticationPlugins.put(pluginProtocolName, plugin);
            if (this.clientDefaultAuthenticationPlugin.equals(pluginClassName)) {
                this.clientDefaultAuthenticationPluginName = pluginProtocolName;
                isDefault = true;
            }
        }
        return isDefault;
    }

    private AuthenticationPlugin<NativePacketPayload> getAuthenticationPlugin(String pluginName) {
        AuthenticationPlugin plugin = this.authenticationPlugins.get(pluginName);
        if (plugin != null && !plugin.isReusable()) {
            try {
                plugin = (AuthenticationPlugin)plugin.getClass().newInstance();
                plugin.init(this.protocol);
            } catch (Throwable t) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.BadAuthenticationPlugin", new Object[]{plugin.getClass().getName()}), t, this.getExceptionInterceptor());
            }
        }
        return plugin;
    }

    private void checkConfidentiality(AuthenticationPlugin<?> plugin) {
        if (plugin.requiresConfidentiality() && !this.protocol.getSocketConnection().isSSLEstablished()) {
            throw ExceptionFactory.createException(Messages.getString("AuthenticationProvider.AuthenticationPluginRequiresSSL", new Object[]{plugin.getProtocolPluginName()}), this.getExceptionInterceptor());
        }
    }

    private void proceedHandshakeWithPluggableAuthentication(ServerSession sessState, String user, String password, String database, NativePacketPayload challenge) {
        if (this.authenticationPlugins == null) {
            this.loadAuthenticationPlugins();
        }
        boolean skipPassword = false;
        int passwordLength = 16;
        int userLength = user != null ? user.length() : 0;
        int databaseLength = database != null ? database.length() : 0;
        int packLength = (userLength + passwordLength + databaseLength) * 3 + 7 + 33;
        long clientParam = sessState.getClientParam();
        int serverCapabilities = sessState.getCapabilities().getCapabilityFlags();
        AuthenticationPlugin<NativePacketPayload> plugin = null;
        NativePacketPayload fromServer = null;
        ArrayList toServer = new ArrayList();
        boolean done = false;
        NativePacketPayload last_sent = null;
        boolean old_raw_challenge = false;
        int counter = 100;
        while (0 < counter--) {
            Object enc;
            String pluginName;
            if (!done) {
                if (challenge != null) {
                    if (challenge.isOKPacket()) {
                        throw ExceptionFactory.createException(Messages.getString("AuthenticationProvider.UnexpectedAuthenticationApproval", new Object[]{plugin.getProtocolPluginName()}), this.getExceptionInterceptor());
                    }
                    clientParam |= 0xEA201L;
                    if (this.propertySet.getBooleanProperty("allowMultiQueries").getValue().booleanValue()) {
                        clientParam |= 0x10000L;
                    }
                    if ((serverCapabilities & 0x400000) != 0 && !this.propertySet.getBooleanProperty("disconnectOnExpiredPasswords").getValue().booleanValue()) {
                        clientParam |= 0x400000L;
                    }
                    if ((serverCapabilities & 0x100000) != 0 && !NONE.equals(this.propertySet.getStringProperty("connectionAttributes").getValue())) {
                        clientParam |= 0x100000L;
                    }
                    if ((serverCapabilities & 0x200000) != 0) {
                        clientParam |= 0x200000L;
                    }
                    sessState.setClientParam(clientParam);
                    if (this.propertySet.getBooleanProperty("useSSL").getValue().booleanValue()) {
                        this.negotiateSSLConnection(packLength);
                    }
                    pluginName = null;
                    if ((serverCapabilities & 0x80000) != 0) {
                        pluginName = !this.protocol.versionMeetsMinimum(5, 5, 10) || this.protocol.versionMeetsMinimum(5, 6, 0) && !this.protocol.versionMeetsMinimum(5, 6, 2) ? challenge.readString(NativeConstants.StringLengthDataType.STRING_FIXED, "ASCII", ((NativeCapabilities)sessState.getCapabilities()).getAuthPluginDataLength()) : challenge.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII");
                    }
                    if ((plugin = this.getAuthenticationPlugin(pluginName)) == null) {
                        plugin = this.getAuthenticationPlugin(this.clientDefaultAuthenticationPluginName);
                    } else if (pluginName.equals(Sha256PasswordPlugin.PLUGIN_NAME) && !this.protocol.getSocketConnection().isSSLEstablished() && this.propertySet.getStringProperty("serverRSAPublicKeyFile").getValue() == null && !this.propertySet.getBooleanProperty("allowPublicKeyRetrieval").getValue().booleanValue()) {
                        plugin = this.getAuthenticationPlugin(this.clientDefaultAuthenticationPluginName);
                        skipPassword = !this.clientDefaultAuthenticationPluginName.equals(pluginName);
                    }
                    this.serverDefaultAuthenticationPluginName = plugin.getProtocolPluginName();
                    this.checkConfidentiality(plugin);
                    fromServer = new NativePacketPayload(StringUtils.getBytes(this.seed));
                } else {
                    plugin = this.getAuthenticationPlugin(this.serverDefaultAuthenticationPluginName == null ? this.clientDefaultAuthenticationPluginName : this.serverDefaultAuthenticationPluginName);
                    this.checkConfidentiality(plugin);
                    fromServer = new NativePacketPayload(StringUtils.getBytes(this.seed));
                }
            } else {
                challenge = this.protocol.checkErrorMessage();
                old_raw_challenge = false;
                if (plugin == null) {
                    plugin = this.getAuthenticationPlugin(this.serverDefaultAuthenticationPluginName == null ? this.clientDefaultAuthenticationPluginName : this.serverDefaultAuthenticationPluginName);
                }
                if (challenge.isOKPacket()) {
                    OkPacket ok = OkPacket.parse(challenge, null);
                    sessState.setStatusFlags(ok.getStatusFlags(), true);
                    plugin.destroy();
                    break;
                }
                if (challenge.isAuthMethodSwitchRequestPacket()) {
                    skipPassword = false;
                    pluginName = challenge.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII");
                    if (!plugin.getProtocolPluginName().equals(pluginName)) {
                        plugin.destroy();
                        plugin = this.getAuthenticationPlugin(pluginName);
                        if (plugin == null) {
                            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("AuthenticationProvider.BadAuthenticationPlugin", new Object[]{pluginName}), this.getExceptionInterceptor());
                        }
                    } else {
                        plugin.reset();
                    }
                    this.checkConfidentiality(plugin);
                    fromServer = new NativePacketPayload(StringUtils.getBytes(challenge.readString(NativeConstants.StringSelfDataType.STRING_TERM, "ASCII")));
                } else {
                    if (!this.protocol.versionMeetsMinimum(5, 5, 16)) {
                        old_raw_challenge = true;
                        challenge.setPosition(challenge.getPosition() - 1);
                    }
                    fromServer = new NativePacketPayload(challenge.readBytes(NativeConstants.StringSelfDataType.STRING_EOF));
                }
            }
            plugin.setAuthenticationParameters(user, skipPassword ? null : password);
            done = plugin.nextAuthenticationStep(fromServer, toServer);
            if (toServer.size() <= 0) continue;
            if (challenge == null) {
                enc = this.getEncodingForHandshake();
                last_sent = new NativePacketPayload(packLength + 1);
                last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, 17L);
                last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(user, (String)enc));
                if (((NativePacketPayload)toServer.get(0)).getPayloadLength() < 256) {
                    last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, ((NativePacketPayload)toServer.get(0)).getPayloadLength());
                    last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_EOF, ((NativePacketPayload)toServer.get(0)).getByteBuffer());
                } else {
                    last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                }
                if (this.useConnectWithDb) {
                    last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(database, (String)enc));
                } else {
                    last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                }
                last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, AuthenticationProvider.getCharsetForHandshake((String)enc, sessState.getCapabilities().getServerVersion()));
                last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                if ((serverCapabilities & 0x80000) != 0) {
                    last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(plugin.getProtocolPluginName(), (String)enc));
                }
                if ((clientParam & 0x100000L) != 0L) {
                    this.appendConnectionAttributes(last_sent, this.propertySet.getStringProperty("connectionAttributes").getValue(), (String)enc);
                }
                this.protocol.send(last_sent, last_sent.getPosition());
                continue;
            }
            if (challenge.isAuthMethodSwitchRequestPacket()) {
                this.protocol.send((Message)toServer.get(0), ((NativePacketPayload)toServer.get(0)).getPayloadLength());
                continue;
            }
            if (challenge.isAuthMoreData() || old_raw_challenge) {
                for (NativePacketPayload buffer : toServer) {
                    this.protocol.send(buffer, buffer.getPayloadLength());
                }
                continue;
            }
            enc = this.getEncodingForHandshake();
            last_sent = new NativePacketPayload(packLength);
            last_sent.writeInteger(NativeConstants.IntegerDataType.INT4, clientParam);
            last_sent.writeInteger(NativeConstants.IntegerDataType.INT4, 0xFFFFFFL);
            last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, AuthenticationProvider.getCharsetForHandshake((String)enc, sessState.getCapabilities().getServerVersion()));
            last_sent.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, new byte[23]);
            last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(user, (String)enc));
            if ((serverCapabilities & 0x200000) != 0) {
                last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, ((NativePacketPayload)toServer.get(0)).readBytes(NativeConstants.StringSelfDataType.STRING_EOF));
            } else {
                last_sent.writeInteger(NativeConstants.IntegerDataType.INT1, ((NativePacketPayload)toServer.get(0)).getPayloadLength());
                last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_EOF, ((NativePacketPayload)toServer.get(0)).getByteBuffer());
            }
            if (this.useConnectWithDb) {
                last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(database, (String)enc));
            }
            if ((serverCapabilities & 0x80000) != 0) {
                last_sent.writeBytes(NativeConstants.StringSelfDataType.STRING_TERM, StringUtils.getBytes(plugin.getProtocolPluginName(), (String)enc));
            }
            if ((clientParam & 0x100000L) != 0L) {
                this.appendConnectionAttributes(last_sent, this.propertySet.getStringProperty("connectionAttributes").getValue(), (String)enc);
            }
            this.protocol.send(last_sent, last_sent.getPosition());
        }
        if (counter == 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("CommunicationsException.TooManyAuthenticationPluginNegotiations"), this.getExceptionInterceptor());
        }
        this.protocol.afterHandshake();
        if (!this.useConnectWithDb) {
            this.protocol.changeDatabase(database);
        }
    }

    private Properties getConnectionAttributesAsProperties(String atts) {
        Properties props = new Properties();
        if (atts != null) {
            String[] pairs;
            for (String pair : pairs = atts.split(",")) {
                int keyEnd = pair.indexOf(":");
                if (keyEnd <= 0 || keyEnd + 1 >= pair.length()) continue;
                props.setProperty(pair.substring(0, keyEnd), pair.substring(keyEnd + 1));
            }
        }
        props.setProperty("_client_name", "MySQL Connector/J");
        props.setProperty("_client_version", "8.0.12");
        props.setProperty("_runtime_vendor", Constants.JVM_VENDOR);
        props.setProperty("_runtime_version", Constants.JVM_VERSION);
        props.setProperty("_client_license", "GPL");
        return props;
    }

    private void appendConnectionAttributes(NativePacketPayload buf, String attributes, String enc) {
        NativePacketPayload lb = new NativePacketPayload(100);
        Properties props = this.getConnectionAttributesAsProperties(attributes);
        for (Object key : props.keySet()) {
            lb.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes((String)key, enc));
            lb.writeBytes(NativeConstants.StringSelfDataType.STRING_LENENC, StringUtils.getBytes(props.getProperty((String)key), enc));
        }
        buf.writeInteger(NativeConstants.IntegerDataType.INT_LENENC, lb.getPosition());
        buf.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, lb.getByteBuffer(), 0, lb.getPosition());
    }

    @Override
    public String getEncodingForHandshake() {
        String enc = this.propertySet.getStringProperty("characterEncoding").getValue();
        if (enc == null) {
            enc = "UTF-8";
        }
        return enc;
    }

    public ExceptionInterceptor getExceptionInterceptor() {
        return this.exceptionInterceptor;
    }

    private void negotiateSSLConnection(int packLength) {
        this.protocol.negotiateSSLConnection(packLength);
    }

    @Override
    public void changeUser(ServerSession serverSession, String userName, String password, String database) {
        this.proceedHandshakeWithPluggableAuthentication(serverSession, userName, password, database, null);
    }
}

