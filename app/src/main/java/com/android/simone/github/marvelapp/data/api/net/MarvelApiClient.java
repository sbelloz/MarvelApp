package com.android.simone.github.marvelapp.data.api.net;

import com.android.simone.github.marvelapp.data.api.interceptor.AuthHashInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Simone Bellotti
 */

@Singleton
public class MarvelApiClient {

    public static final String BASE_URL = "http://gateway.marvel.com";
    public static final String PUBLIC_KEY = "6a7ed890b4b941a925202a5630d5b162";
    public static final String PRIVATE_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    private Retrofit retrofit;

    @Inject
    public MarvelApiClient(String publicKey, String privateKey) {
        initRetrofit(publicKey, privateKey);
    }

    public <T> T createService(final Class<T> service) {
        return retrofit.create(service);
    }

    private void initRetrofit(String publicKey, String privateKey) {
        retrofit = new Retrofit.Builder()
                .client(buildOkHttpClient(publicKey, privateKey))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private OkHttpClient buildOkHttpClient(String publicKey, String privateKey) {
        long timestamp = System.currentTimeMillis();
        return new OkHttpClient.Builder()
                .addInterceptor(provideAuthHashInterceptor(publicKey, privateKey, timestamp))
                .addInterceptor(provideHttpLoggingInterceptor())
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private AuthHashInterceptor provideAuthHashInterceptor(String publicKey, String privateKey, long timestamp) {
        return new AuthHashInterceptor(publicKey, privateKey, timestamp);
    }

    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.NONE);
    }
}
