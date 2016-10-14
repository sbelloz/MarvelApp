package com.android.simone.github.marvelapp.data.api.interceptor;

import com.android.simone.github.marvelapp.data.api.exception.MarvelApiException;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */
public class AuthHashInterceptor implements Interceptor {
    private static final String TIMESTAMP_KEY = "ts";
    private static final String HASH_KEY = "hash";
    private static final String APIKEY_KEY = "apikey";

    private final String publicKey;
    private final String privateKey;
    private final long timestamp;
    private final AuthHashGenerator hashGenerator;

    public AuthHashInterceptor(String publicKey, String privateKey, long timestamp) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.timestamp = timestamp;
        this.hashGenerator = new AuthHashGenerator();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String timestampString = String.valueOf(timestamp);
        String hash = null;
        try {
            hash = hashGenerator.generateHash(timestampString, publicKey, privateKey);
        } catch (MarvelApiException e) {
            e.printStackTrace();
        }
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestampString)
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
