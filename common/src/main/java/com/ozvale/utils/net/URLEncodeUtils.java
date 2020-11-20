package com.ozvale.utils.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * URL编码工具类
 */
public class URLEncodeUtils {

    public static final String CHARSET_UTF8_NAME = "utf-8";
    public static final Charset CHARSET_UTF8 = Charset.forName(CHARSET_UTF8_NAME);


    /**
     * 以utf编码url
     *
     * @param url
     * @return
     */
    public static String utf8Encode(String url) {
        try {
            return URLEncoder.encode(url, CHARSET_UTF8_NAME).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
