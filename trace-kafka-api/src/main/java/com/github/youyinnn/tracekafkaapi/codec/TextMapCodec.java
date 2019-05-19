package com.github.youyinnn.tracekafkaapi.codec;

import com.github.youyinnn.tracekafkaapi.api.Constants;
import com.github.youyinnn.tracekafkaapi.api.YouObjectFactory;
import com.github.youyinnn.tracekafkaapi.api.YouSpanContext;
import io.opentracing.propagation.TextMap;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youyinnn
 * Date 4/27/2019
 */
public class TextMapCodec implements Codec<TextMap>{

    private final String contextKey;

    private final String baggagePrefix;

    private final boolean urlEncoding;

    private final YouObjectFactory objectFactory;

    public TextMapCodec(boolean urlEncoding) {
        this.contextKey = Constants.CONTEXT_KEY;
        this.baggagePrefix = Constants.TEXT_MAP_BAGGAGE_PREFIX;
        this.urlEncoding = urlEncoding;
        this.objectFactory = YouObjectFactory.getInstance();
    }

    public TextMapCodec(String contextKey, String baggagePrefix, boolean urlEncoding) {
        this.contextKey = contextKey;
        this.baggagePrefix = baggagePrefix;
        this.urlEncoding = urlEncoding;
        this.objectFactory = YouObjectFactory.getInstance();
    }

    @Override
    public void inject(YouSpanContext context, TextMap carrier) {
        carrier.put(contextKey, context.getContextKey());
        for (Map.Entry<String, String> entry : context.baggageItems()) {
            carrier.put(baggagePrefix + entry.getKey(),
                    encodedValue(entry.getValue()));
        }
    }

    @Override
    public YouSpanContext extract(TextMap carrier) {
        YouSpanContext context = null;
        Map<String, String> baggage = new HashMap<>(16);
        String[] yspcId = null;
        for (Map.Entry<String, String> entry : carrier) {
            String key = entry.getKey();
            if (key.equals(contextKey)) {
                yspcId = entry.getValue().split("-");
            } else if (key.startsWith(baggagePrefix)) {
                baggage.put(key.substring(baggagePrefix.length() - 1), decodedValue(entry.getValue()));
            }
        }
        if (yspcId != null) {
            context =  objectFactory.createContext(
                    yspcId[0],
                    yspcId[1],
                    yspcId[2],
                    baggage);
        }
        return context;
    }

    private String encodedValue(String value) {
        if (!urlEncoding) {
            return value;
        }
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // not much we can do, try raw value
            return value;
        }
    }

    private String decodedValue(String value) {
        if (!urlEncoding) {
            return value;
        }
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // not much we can do, try raw value
            return value;
        }
    }
}
