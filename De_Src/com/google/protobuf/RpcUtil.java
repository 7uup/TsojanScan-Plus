/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class RpcUtil {
    private RpcUtil() {
    }

    public static <Type extends Message> RpcCallback<Type> specializeCallback(RpcCallback<Message> originalCallback) {
        return originalCallback;
    }

    public static <Type extends Message> RpcCallback<Message> generalizeCallback(final RpcCallback<Type> originalCallback, final Class<Type> originalClass, final Type defaultInstance) {
        return new RpcCallback<Message>(){

            @Override
            public void run(Message parameter) {
                Message typedParameter;
                try {
                    typedParameter = (Message)originalClass.cast(parameter);
                } catch (ClassCastException ignored) {
                    typedParameter = RpcUtil.copyAsType(defaultInstance, parameter);
                }
                originalCallback.run(typedParameter);
            }
        };
    }

    private static <Type extends Message> Type copyAsType(Type typeDefaultInstance, Message source2) {
        return (Type)typeDefaultInstance.newBuilderForType().mergeFrom(source2).build();
    }

    public static <ParameterType> RpcCallback<ParameterType> newOneTimeCallback(final RpcCallback<ParameterType> originalCallback) {
        return new RpcCallback<ParameterType>(){
            private boolean alreadyCalled = false;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run(ParameterType parameter) {
                2 var2_2 = this;
                synchronized (var2_2) {
                    if (this.alreadyCalled) {
                        throw new AlreadyCalledException();
                    }
                    this.alreadyCalled = true;
                }
                originalCallback.run(parameter);
            }
        };
    }

    public static final class AlreadyCalledException
    extends RuntimeException {
        private static final long serialVersionUID = 5469741279507848266L;

        public AlreadyCalledException() {
            super("This RpcCallback was already called and cannot be called multiple times.");
        }
    }
}

