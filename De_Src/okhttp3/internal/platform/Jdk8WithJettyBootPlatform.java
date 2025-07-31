/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\rH\u0016R\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform;", "Lokhttp3/internal/platform/Platform;", "putMethod", "Ljava/lang/reflect/Method;", "getMethod", "removeMethod", "clientProviderClass", "Ljava/lang/Class;", "serverProviderClass", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "AlpnProvider", "Companion", "okhttp"})
public final class Jdk8WithJettyBootPlatform
extends Platform {
    private final Method putMethod;
    private final Method getMethod;
    private final Method removeMethod;
    private final Class<?> clientProviderClass;
    private final Class<?> serverProviderClass;
    public static final Companion Companion = new Companion(null);

    public void configureTlsExtensions(@NotNull SSLSocket sslSocket, @Nullable String hostname, @NotNull List<? extends Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        List<String> names = Platform.Companion.alpnProtocolNames(protocols);
        try {
            Object alpnProvider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new AlpnProvider(names));
            this.putMethod.invoke(null, sslSocket, alpnProvider);
        } catch (InvocationTargetException e) {
            throw (Throwable)((Object)new AssertionError("failed to set ALPN", e));
        } catch (IllegalAccessException e) {
            throw (Throwable)((Object)new AssertionError("failed to set ALPN", e));
        }
    }

    @Override
    public void afterHandshake(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            this.removeMethod.invoke(null, sslSocket);
        } catch (IllegalAccessException e) {
            throw (Throwable)((Object)new AssertionError("failed to remove ALPN", e));
        } catch (InvocationTargetException e) {
            throw (Throwable)((Object)new AssertionError("failed to remove ALPN", e));
        }
    }

    @Override
    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.getMethod.invoke(null, sslSocket));
            if (invocationHandler == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            }
            AlpnProvider provider = (AlpnProvider)invocationHandler;
            if (!provider.getUnsupported() && provider.getSelected() == null) {
                Platform.log$default(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
                return null;
            }
            return provider.getUnsupported() ? null : provider.getSelected();
        } catch (InvocationTargetException e) {
            throw (Throwable)((Object)new AssertionError("failed to get ALPN selected protocol", e));
        } catch (IllegalAccessException e) {
            throw (Throwable)((Object)new AssertionError("failed to get ALPN selected protocol", e));
        }
    }

    public Jdk8WithJettyBootPlatform(@NotNull Method putMethod, @NotNull Method getMethod, @NotNull Method removeMethod, @NotNull Class<?> clientProviderClass, @NotNull Class<?> serverProviderClass) {
        Intrinsics.checkNotNullParameter(putMethod, "putMethod");
        Intrinsics.checkNotNullParameter(getMethod, "getMethod");
        Intrinsics.checkNotNullParameter(removeMethod, "removeMethod");
        Intrinsics.checkNotNullParameter(clientProviderClass, "clientProviderClass");
        Intrinsics.checkNotNullParameter(serverProviderClass, "serverProviderClass");
        this.putMethod = putMethod;
        this.getMethod = getMethod;
        this.removeMethod = removeMethod;
        this.clientProviderClass = clientProviderClass;
        this.serverProviderClass = serverProviderClass;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J0\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0017H\u0096\u0002\u00a2\u0006\u0002\u0010\u0018R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0019"}, d2={"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$AlpnProvider;", "Ljava/lang/reflect/InvocationHandler;", "protocols", "", "", "(Ljava/util/List;)V", "selected", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "unsupported", "", "getUnsupported", "()Z", "setUnsupported", "(Z)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "okhttp"})
    private static final class AlpnProvider
    implements InvocationHandler {
        private boolean unsupported;
        @Nullable
        private String selected;
        private final List<String> protocols;

        public final boolean getUnsupported() {
            return this.unsupported;
        }

        public final void setUnsupported(boolean bl) {
            this.unsupported = bl;
        }

        @Nullable
        public final String getSelected() {
            return this.selected;
        }

        public final void setSelected(@Nullable String string) {
            this.selected = string;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        @Nullable
        public Object invoke(@NotNull Object proxy, @NotNull Method method, @Nullable Object[] args2) throws Throwable {
            int n;
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(method, "method");
            Object[] objectArray = args2;
            if (args2 == null) {
                objectArray = new Object[]{};
            }
            Object[] callArgs = objectArray;
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (Intrinsics.areEqual(methodName, "supports") && Intrinsics.areEqual(Boolean.TYPE, returnType)) {
                return true;
            }
            if (Intrinsics.areEqual(methodName, "unsupported") && Intrinsics.areEqual(Void.TYPE, returnType)) {
                this.unsupported = true;
                return null;
            }
            if (Intrinsics.areEqual(methodName, "protocols")) {
                Object[] objectArray2 = callArgs;
                n = 0;
                if (objectArray2.length == 0) {
                    return this.protocols;
                }
            }
            if ((Intrinsics.areEqual(methodName, "selectProtocol") || Intrinsics.areEqual(methodName, "select")) && Intrinsics.areEqual(String.class, returnType) && callArgs.length == 1 && callArgs[0] instanceof List) {
                Object object = callArgs[0];
                if (object == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
                }
                n = 0;
                List peerProtocols = (List)object;
                int n2 = peerProtocols.size();
                if (n <= n2) {
                    while (true) {
                        void i;
                        Object e = peerProtocols.get((int)i);
                        if (e == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        String protocol = (String)e;
                        if (this.protocols.contains(protocol)) {
                            this.selected = protocol;
                            return this.selected;
                        }
                        if (i == n2) break;
                        ++i;
                    }
                }
                this.selected = this.protocols.get(0);
                return this.selected;
            }
            if ((Intrinsics.areEqual(methodName, "protocolSelected") || Intrinsics.areEqual(methodName, "selected")) && callArgs.length == 1) {
                Object object = callArgs[0];
                if (object == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                this.selected = (String)object;
                return null;
            }
            return method.invoke(this, Arrays.copyOf(callArgs, callArgs.length));
        }

        public AlpnProvider(@NotNull List<String> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            this.protocols = protocols;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a8\u0006\u0005"}, d2={"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"})
    public static final class Companion {
        @Nullable
        public final Platform buildIfSupported() {
            String jvmVersion = System.getProperty("java.specification.version", "unknown");
            try {
                String string = jvmVersion;
                Intrinsics.checkNotNullExpressionValue(string, "jvmVersion");
                String string2 = string;
                boolean bl = false;
                int version = Integer.parseInt(string2);
                if (version >= 9) {
                    return null;
                }
            } catch (NumberFormatException version) {
                // empty catch block
            }
            try {
                String alpnClassName = "org.eclipse.jetty.alpn.ALPN";
                Class<?> alpnClass = Class.forName(alpnClassName, true, null);
                Class<?> providerClass = Class.forName(alpnClassName + "$Provider", true, null);
                Class<?> clientProviderClass = Class.forName(alpnClassName + "$ClientProvider", true, null);
                Class<?> serverProviderClass = Class.forName(alpnClassName + "$ServerProvider", true, null);
                Method putMethod = alpnClass.getMethod("put", SSLSocket.class, providerClass);
                Method getMethod = alpnClass.getMethod("get", SSLSocket.class);
                Method removeMethod = alpnClass.getMethod("remove", SSLSocket.class);
                Method method = putMethod;
                Intrinsics.checkNotNullExpressionValue(method, "putMethod");
                Method method2 = getMethod;
                Intrinsics.checkNotNullExpressionValue(method2, "getMethod");
                Method method3 = removeMethod;
                Intrinsics.checkNotNullExpressionValue(method3, "removeMethod");
                Class<?> clazz = clientProviderClass;
                Intrinsics.checkNotNullExpressionValue(clazz, "clientProviderClass");
                Class<?> clazz2 = serverProviderClass;
                Intrinsics.checkNotNullExpressionValue(clazz2, "serverProviderClass");
                return new Jdk8WithJettyBootPlatform(method, method2, method3, clazz, clazz2);
            } catch (ClassNotFoundException classNotFoundException) {
            } catch (NoSuchMethodException noSuchMethodException) {
                // empty catch block
            }
            return null;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

