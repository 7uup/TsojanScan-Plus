/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.ws;

import kotlin.Metadata;
import okio.ByteString;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0004"}, d2={"EMPTY_DEFLATE_BLOCK", "Lokio/ByteString;", "LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION", "", "okhttp"})
public final class MessageDeflaterKt {
    private static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;

    public static final /* synthetic */ ByteString access$getEMPTY_DEFLATE_BLOCK$p() {
        return EMPTY_DEFLATE_BLOCK;
    }
}

