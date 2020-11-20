package com.ozvale.utils.net;

import java.net.URI;

/**
 * URL地址拼接
 */
public class URLConnector {

    private StringBuilder builder = new StringBuilder();

    public static URLConnector create(String... path) {
        return new URLConnector().connect(path);
    }

    public URLConnector connect(String... path) {
        if (path == null) return this;
        for (String u : path) {
            if (u == null) continue;
            String append = u.replace('\\', '/');
            if (builder.length() > 0) {
                char last = builder.charAt(builder.length() - 1);
                if (last != '/' && !append.startsWith("/")) {
                    builder.append('/');
                }
            }
            builder.append(append);
        }
        return this;
    }

    public URI toURI() {
        return URI.create(builder.toString());
    }
}
