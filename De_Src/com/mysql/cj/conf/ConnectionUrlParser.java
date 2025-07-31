/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.DatabaseUrlContainer;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.UnsupportedConnectionStringException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionUrlParser
implements DatabaseUrlContainer {
    private static final String DUMMY_SCHEMA = "cj://";
    private static final String USER_PASS_SEPARATOR = ":";
    private static final String USER_HOST_SEPARATOR = "@";
    private static final String HOSTS_SEPARATOR = ",";
    private static final String KEY_VALUE_HOST_INFO_OPENING_MARKER = "(";
    private static final String KEY_VALUE_HOST_INFO_CLOSING_MARKER = ")";
    private static final String HOSTS_LIST_OPENING_MARKERS = "[(";
    private static final String HOSTS_LIST_CLOSING_MARKERS = "])";
    private static final String ADDRESS_EQUALS_HOST_INFO_PREFIX = "ADDRESS=";
    private static final Pattern CONNECTION_STRING_PTRN = Pattern.compile("(?<scheme>[\\w:%]+)\\s*(?://(?<authority>[^/?#]*))?\\s*(?:/(?!\\s*/)(?<path>[^?#]*))?(?:\\?(?!\\s*\\?)(?<query>[^#]*))?(?:\\s*#(?<fragment>.*))?");
    private static final Pattern SCHEME_PTRN = Pattern.compile("(?<scheme>[\\w:%]+).*");
    private static final Pattern HOST_LIST_PTRN = Pattern.compile("^\\[(?<hosts>.*)\\]$");
    private static final Pattern GENERIC_HOST_PTRN = Pattern.compile("^(?<host>.*?)(?::(?<port>[^:]*))?$");
    private static final Pattern KEY_VALUE_HOST_PTRN = Pattern.compile("[,\\s]*(?<key>[\\w\\.\\-\\s%]*)(?:=(?<value>[^,=]*))?");
    private static final Pattern ADDRESS_EQUALS_HOST_PTRN = Pattern.compile("\\s*\\(\\s*(?<key>[\\w\\.\\-%]+)?\\s*(?:=(?<value>[^)]*))?\\)\\s*");
    private static final Pattern PROPERTIES_PTRN = Pattern.compile("[&\\s]*(?<key>[\\w\\.\\-\\s%]*)(?:=(?<value>[^&=]*))?");
    private final String baseConnectionString;
    private String scheme;
    private String authority;
    private String path;
    private String query;
    private List<HostInfo> parsedHosts = null;
    private Map<String, String> parsedProperties = null;

    public static ConnectionUrlParser parseConnectionString(String connString) {
        return new ConnectionUrlParser(connString);
    }

    private ConnectionUrlParser(String connString) {
        if (connString == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.0"));
        }
        if (!ConnectionUrlParser.isConnectionStringSupported(connString)) {
            throw ExceptionFactory.createException(UnsupportedConnectionStringException.class, Messages.getString("ConnectionString.17", new String[]{connString}));
        }
        this.baseConnectionString = connString;
        this.parseConnectionString();
    }

    public static boolean isConnectionStringSupported(String connString) {
        if (connString == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.0"));
        }
        Matcher matcher = SCHEME_PTRN.matcher(connString);
        return matcher.matches() && ConnectionUrl.Type.isSupported(ConnectionUrlParser.decode(matcher.group("scheme")));
    }

    private void parseConnectionString() {
        String connString = this.baseConnectionString;
        Matcher matcher = CONNECTION_STRING_PTRN.matcher(connString);
        if (!matcher.matches()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.1"));
        }
        this.scheme = ConnectionUrlParser.decode(matcher.group("scheme"));
        this.authority = matcher.group("authority");
        this.path = matcher.group("path") == null ? null : ConnectionUrlParser.decode(matcher.group("path")).trim();
        this.query = matcher.group("query");
    }

    private void parseAuthoritySection() {
        if (StringUtils.isNullOrEmpty(this.authority)) {
            this.parsedHosts.add(new HostInfo());
            return;
        }
        List<String> authoritySegments = StringUtils.split(this.authority, HOSTS_SEPARATOR, HOSTS_LIST_OPENING_MARKERS, HOSTS_LIST_CLOSING_MARKERS, true, StringUtils.SEARCH_MODE__MRK_WS);
        for (String hi : authoritySegments) {
            this.parseAuthoritySegment(hi);
        }
    }

    private void parseAuthoritySegment(String authSegment) {
        String hostInfo;
        HostInfo hi;
        Pair<String, String> userHostInfoSplit = this.splitByUserInfoAndHostInfo(authSegment);
        String userInfo = StringUtils.safeTrim((String)userHostInfoSplit.left);
        String user = null;
        String password = null;
        if (!StringUtils.isNullOrEmpty(userInfo)) {
            Pair<String, String> userInfoPair = ConnectionUrlParser.parseUserInfo(userInfo);
            user = ConnectionUrlParser.decode(StringUtils.safeTrim((String)userInfoPair.left));
            password = ConnectionUrlParser.decode(StringUtils.safeTrim((String)userInfoPair.right));
        }
        if ((hi = this.buildHostInfoForEmptyHost(user, password, hostInfo = StringUtils.safeTrim((String)userHostInfoSplit.right))) != null) {
            this.parsedHosts.add(hi);
            return;
        }
        hi = this.buildHostInfoResortingToUriParser(user, password, authSegment);
        if (hi != null) {
            this.parsedHosts.add(hi);
            return;
        }
        List<HostInfo> hiList = this.buildHostInfoResortingToSubHostsListParser(user, password, hostInfo);
        if (hiList != null) {
            this.parsedHosts.addAll(hiList);
            return;
        }
        hi = this.buildHostInfoResortingToKeyValueSyntaxParser(user, password, hostInfo);
        if (hi != null) {
            this.parsedHosts.add(hi);
            return;
        }
        hi = this.buildHostInfoResortingToAddressEqualsSyntaxParser(user, password, hostInfo);
        if (hi != null) {
            this.parsedHosts.add(hi);
            return;
        }
        hi = this.buildHostInfoResortingToGenericSyntaxParser(user, password, hostInfo);
        if (hi != null) {
            this.parsedHosts.add(hi);
            return;
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.2", new Object[]{authSegment}));
    }

    private HostInfo buildHostInfoForEmptyHost(String user, String password, String hostInfo) {
        if (StringUtils.isNullOrEmpty(hostInfo)) {
            if (StringUtils.isNullOrEmpty(user) && StringUtils.isNullOrEmpty(password)) {
                return new HostInfo();
            }
            return new HostInfo(this, null, -1, user, password);
        }
        return null;
    }

    private HostInfo buildHostInfoResortingToUriParser(String user, String password, String hostInfo) {
        String host = null;
        int port = -1;
        try {
            URI uri = URI.create(DUMMY_SCHEMA + hostInfo);
            if (uri.getHost() != null) {
                host = ConnectionUrlParser.decode(uri.getHost());
            }
            if (uri.getPort() != -1) {
                port = uri.getPort();
            }
            if (uri.getUserInfo() != null) {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (host != null || port != -1) {
            return new HostInfo(this, host, port, user, password);
        }
        return null;
    }

    private List<HostInfo> buildHostInfoResortingToSubHostsListParser(String user, String password, String hostInfo) {
        Matcher matcher = HOST_LIST_PTRN.matcher(hostInfo);
        if (matcher.matches()) {
            String hosts = matcher.group("hosts");
            List<String> hostsList = StringUtils.split(hosts, HOSTS_SEPARATOR, HOSTS_LIST_OPENING_MARKERS, HOSTS_LIST_CLOSING_MARKERS, true, StringUtils.SEARCH_MODE__MRK_WS);
            boolean maybeIPv6 = hostsList.size() == 1 && hostsList.get(0).matches("(?i)^[\\dabcdef:]+$");
            ArrayList<HostInfo> hostInfoList = new ArrayList<HostInfo>();
            for (String h2 : hostsList) {
                HostInfo hi = this.buildHostInfoForEmptyHost(user, password, h2);
                if (hi != null) {
                    hostInfoList.add(hi);
                    continue;
                }
                hi = this.buildHostInfoResortingToUriParser(user, password, h2);
                if (hi != null || maybeIPv6 && (hi = this.buildHostInfoResortingToUriParser(user, password, "[" + h2 + "]")) != null) {
                    hostInfoList.add(hi);
                    continue;
                }
                hi = this.buildHostInfoResortingToKeyValueSyntaxParser(user, password, h2);
                if (hi != null) {
                    hostInfoList.add(hi);
                    continue;
                }
                hi = this.buildHostInfoResortingToAddressEqualsSyntaxParser(user, password, h2);
                if (hi != null) {
                    hostInfoList.add(hi);
                    continue;
                }
                hi = this.buildHostInfoResortingToGenericSyntaxParser(user, password, h2);
                if (hi != null) {
                    hostInfoList.add(hi);
                    continue;
                }
                return null;
            }
            return hostInfoList;
        }
        return null;
    }

    private HostInfo buildHostInfoResortingToKeyValueSyntaxParser(String user, String password, String hostInfo) {
        if (!hostInfo.startsWith(KEY_VALUE_HOST_INFO_OPENING_MARKER) || !hostInfo.endsWith(KEY_VALUE_HOST_INFO_CLOSING_MARKER)) {
            return null;
        }
        hostInfo = hostInfo.substring(KEY_VALUE_HOST_INFO_OPENING_MARKER.length(), hostInfo.length() - KEY_VALUE_HOST_INFO_CLOSING_MARKER.length());
        return new HostInfo(this, null, -1, user, password, this.processKeyValuePattern(KEY_VALUE_HOST_PTRN, hostInfo));
    }

    private HostInfo buildHostInfoResortingToAddressEqualsSyntaxParser(String user, String password, String hostInfo) {
        int p = StringUtils.indexOfIgnoreCase(hostInfo, ADDRESS_EQUALS_HOST_INFO_PREFIX);
        if (p != 0) {
            return null;
        }
        hostInfo = hostInfo.substring(p + ADDRESS_EQUALS_HOST_INFO_PREFIX.length()).trim();
        return new HostInfo(this, null, -1, user, password, this.processKeyValuePattern(ADDRESS_EQUALS_HOST_PTRN, hostInfo));
    }

    private HostInfo buildHostInfoResortingToGenericSyntaxParser(String user, String password, String hostInfo) {
        if (this.splitByUserInfoAndHostInfo((String)hostInfo).left != null) {
            return null;
        }
        Pair<String, Integer> hostPortPair = ConnectionUrlParser.parseHostPortPair(hostInfo);
        String host = ConnectionUrlParser.decode(StringUtils.safeTrim((String)hostPortPair.left));
        Integer port = (Integer)hostPortPair.right;
        return new HostInfo(this, StringUtils.isNullOrEmpty(host) ? null : host, port, user, password);
    }

    private Pair<String, String> splitByUserInfoAndHostInfo(String authSegment) {
        String userInfoPart = null;
        String hostInfoPart = authSegment;
        int p = authSegment.indexOf(USER_HOST_SEPARATOR);
        if (p >= 0) {
            userInfoPart = authSegment.substring(0, p);
            hostInfoPart = authSegment.substring(p + USER_HOST_SEPARATOR.length());
        }
        return new Pair<String, String>(userInfoPart, hostInfoPart);
    }

    public static Pair<String, String> parseUserInfo(String userInfo) {
        if (StringUtils.isNullOrEmpty(userInfo)) {
            return null;
        }
        String[] userInfoParts = userInfo.split(USER_PASS_SEPARATOR, 2);
        String userName = userInfoParts[0];
        String password = userInfoParts.length > 1 ? userInfoParts[1] : null;
        return new Pair<String, String>(userName, password);
    }

    public static Pair<String, Integer> parseHostPortPair(String hostInfo) {
        if (StringUtils.isNullOrEmpty(hostInfo)) {
            return null;
        }
        Matcher matcher = GENERIC_HOST_PTRN.matcher(hostInfo);
        if (matcher.matches()) {
            String host = matcher.group("host");
            String portAsString = ConnectionUrlParser.decode(StringUtils.safeTrim(matcher.group("port")));
            Integer portAsInteger = -1;
            if (!StringUtils.isNullOrEmpty(portAsString)) {
                try {
                    portAsInteger = Integer.parseInt(portAsString);
                } catch (NumberFormatException e) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.3", new Object[]{hostInfo}), e);
                }
            }
            return new Pair<String, Integer>(host, portAsInteger);
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.3", new Object[]{hostInfo}));
    }

    private void parseQuerySection() {
        if (StringUtils.isNullOrEmpty(this.query)) {
            this.parsedProperties = new HashMap<String, String>();
            return;
        }
        this.parsedProperties = this.processKeyValuePattern(PROPERTIES_PTRN, this.query);
    }

    private Map<String, String> processKeyValuePattern(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        int p = 0;
        HashMap<String, String> kvMap = new HashMap<String, String>();
        while (matcher.find()) {
            if (matcher.start() != p) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.4", new Object[]{input.substring(p)}));
            }
            String key = ConnectionUrlParser.decode(StringUtils.safeTrim(matcher.group("key")));
            String value = ConnectionUrlParser.decode(StringUtils.safeTrim(matcher.group("value")));
            if (!StringUtils.isNullOrEmpty(key)) {
                kvMap.put(key, value);
            } else if (!StringUtils.isNullOrEmpty(value)) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.4", new Object[]{input.substring(p)}));
            }
            p = matcher.end();
        }
        if (p != input.length()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.4", new Object[]{input.substring(p)}));
        }
        return kvMap;
    }

    private static String decode(String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            return text;
        }
        try {
            return URLDecoder.decode(text, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return "";
        }
    }

    @Override
    public String getDatabaseUrl() {
        return this.baseConnectionString;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getAuthority() {
        return this.authority;
    }

    public String getPath() {
        return this.path;
    }

    public String getQuery() {
        return this.query;
    }

    public List<HostInfo> getHosts() {
        if (this.parsedHosts == null) {
            this.parsedHosts = new ArrayList<HostInfo>();
            this.parseAuthoritySection();
        }
        return this.parsedHosts;
    }

    public Map<String, String> getProperties() {
        if (this.parsedProperties == null) {
            this.parseQuerySection();
        }
        return Collections.unmodifiableMap(this.parsedProperties);
    }

    public String toString() {
        StringBuilder asStr = new StringBuilder(super.toString());
        asStr.append(String.format(" :: {scheme: \"%s\", authority: \"%s\", path: \"%s\", query: \"%s\", parsedHosts: %s, parsedProperties: %s}", this.scheme, this.authority, this.path, this.query, this.parsedHosts, this.parsedProperties));
        return asStr.toString();
    }

    public static class Pair<T, U> {
        public final T left;
        public final U right;

        public Pair(T left, U right) {
            this.left = left;
            this.right = right;
        }

        public String toString() {
            StringBuilder asStr = new StringBuilder(super.toString());
            asStr.append(String.format(" :: { left: %s, right: %s }", this.left, this.right));
            return asStr.toString();
        }
    }
}

