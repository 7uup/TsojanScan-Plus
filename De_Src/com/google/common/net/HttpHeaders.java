/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class HttpHeaders {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DATE = "Date";
    public static final String PRAGMA = "Pragma";
    public static final String VIA = "Via";
    public static final String WARNING = "Warning";
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONNECTION = "Connection";
    public static final String COOKIE = "Cookie";
    public static final String CROSS_ORIGIN_RESOURCE_POLICY = "Cross-Origin-Resource-Policy";
    public static final String EARLY_DATA = "Early-Data";
    public static final String EXPECT = "Expect";
    public static final String FROM = "From";
    public static final String FORWARDED = "Forwarded";
    @Beta
    public static final String FOLLOW_ONLY_WHEN_PRERENDER_SHOWN = "Follow-Only-When-Prerender-Shown";
    public static final String HOST = "Host";
    public static final String HTTP2_SETTINGS = "HTTP2-Settings";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_RANGE = "If-Range";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String LAST_EVENT_ID = "Last-Event-ID";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String ORIGIN = "Origin";
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String REFERRER_POLICY = "Referrer-Policy";
    public static final String SERVICE_WORKER = "Service-Worker";
    public static final String TE = "TE";
    public static final String UPGRADE = "Upgrade";
    public static final String UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests";
    public static final String USER_AGENT = "User-Agent";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    public static final String AGE = "Age";
    public static final String ALLOW = "Allow";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_SECURITY_POLICY = "Content-Security-Policy";
    public static final String CONTENT_SECURITY_POLICY_REPORT_ONLY = "Content-Security-Policy-Report-Only";
    public static final String X_CONTENT_SECURITY_POLICY = "X-Content-Security-Policy";
    public static final String X_CONTENT_SECURITY_POLICY_REPORT_ONLY = "X-Content-Security-Policy-Report-Only";
    public static final String X_WEBKIT_CSP = "X-WebKit-CSP";
    public static final String X_WEBKIT_CSP_REPORT_ONLY = "X-WebKit-CSP-Report-Only";
    public static final String CROSS_ORIGIN_OPENER_POLICY = "Cross-Origin-Opener-Policy";
    public static final String ETAG = "ETag";
    public static final String EXPIRES = "Expires";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LINK = "Link";
    public static final String LOCATION = "Location";
    public static final String ORIGIN_TRIAL = "Origin-Trial";
    public static final String P3P = "P3P";
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    public static final String REFRESH = "Refresh";
    public static final String REPORT_TO = "Report-To";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String SERVER = "Server";
    public static final String SERVER_TIMING = "Server-Timing";
    public static final String SERVICE_WORKER_ALLOWED = "Service-Worker-Allowed";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String SET_COOKIE2 = "Set-Cookie2";
    @Beta
    public static final String SOURCE_MAP = "SourceMap";
    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
    public static final String TIMING_ALLOW_ORIGIN = "Timing-Allow-Origin";
    public static final String TRAILER = "Trailer";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String VARY = "Vary";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    public static final String DNT = "DNT";
    public static final String X_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
    public static final String X_DO_NOT_TRACK = "X-Do-Not-Track";
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    public static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    public static final String X_FORWARDED_PORT = "X-Forwarded-Port";
    public static final String X_FRAME_OPTIONS = "X-Frame-Options";
    public static final String X_POWERED_BY = "X-Powered-By";
    @Beta
    public static final String PUBLIC_KEY_PINS = "Public-Key-Pins";
    @Beta
    public static final String PUBLIC_KEY_PINS_REPORT_ONLY = "Public-Key-Pins-Report-Only";
    public static final String X_REQUESTED_WITH = "X-Requested-With";
    public static final String X_USER_IP = "X-User-IP";
    @Beta
    public static final String X_DOWNLOAD_OPTIONS = "X-Download-Options";
    public static final String X_XSS_PROTECTION = "X-XSS-Protection";
    public static final String X_DNS_PREFETCH_CONTROL = "X-DNS-Prefetch-Control";
    public static final String PING_FROM = "Ping-From";
    public static final String PING_TO = "Ping-To";
    public static final String PURPOSE = "Purpose";
    public static final String X_PURPOSE = "X-Purpose";
    public static final String X_MOZ = "X-Moz";
    public static final String SEC_FETCH_DEST = "Sec-Fetch-Dest";
    public static final String SEC_FETCH_MODE = "Sec-Fetch-Mode";
    public static final String SEC_FETCH_SITE = "Sec-Fetch-Site";
    public static final String SEC_FETCH_USER = "Sec-Fetch-User";
    public static final String SEC_METADATA = "Sec-Metadata";
    public static final String SEC_TOKEN_BINDING = "Sec-Token-Binding";
    public static final String SEC_PROVIDED_TOKEN_BINDING_ID = "Sec-Provided-Token-Binding-ID";
    public static final String SEC_REFERRED_TOKEN_BINDING_ID = "Sec-Referred-Token-Binding-ID";
    public static final String SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
    public static final String SEC_WEBSOCKET_EXTENSIONS = "Sec-WebSocket-Extensions";
    public static final String SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    public static final String SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    public static final String SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    public static final String CDN_LOOP = "CDN-Loop";

    private HttpHeaders() {
    }

    public static final class ReferrerPolicyValues {
        public static final String NO_REFERRER = "no-referrer";
        public static final String NO_REFFERER_WHEN_DOWNGRADE = "no-referrer-when-downgrade";
        public static final String SAME_ORIGIN = "same-origin";
        public static final String ORIGIN = "origin";
        public static final String STRICT_ORIGIN = "strict-origin";
        public static final String ORIGIN_WHEN_CROSS_ORIGIN = "origin-when-cross-origin";
        public static final String STRICT_ORIGIN_WHEN_CROSS_ORIGIN = "strict-origin-when-cross-origin";
        public static final String UNSAFE_URL = "unsafe-url";

        private ReferrerPolicyValues() {
        }
    }
}

