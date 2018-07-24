package tk.roydgar.util;

import org.springframework.http.HttpHeaders;

public class HttpHeadersUtil {

    private HttpHeadersUtil() {}

    public static HttpHeaders httpHeaders(String key, String value) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(key, value);
        return httpHeaders;
    }
}
