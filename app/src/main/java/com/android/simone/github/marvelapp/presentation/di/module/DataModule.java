package com.android.simone.github.marvelapp.presentation.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simone Bellotti
 */

@Module
public class DataModule {

    @Provides
    @Named("marvel_public_api_key")
    public String provideMarvelPublicKey() {
        return "6a7ed890b4b941a925202a5630d5b162";
    }

    @Provides
    @Named("marvel_private_api_key")
    public String provideMarvelPrivateKey() {
        return "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";
    }

    @Provides
    @Named("character_id")
    public String provideCharacterId() {
        return "1009220";
    }

    @Provides
    @Named("comic_per_page")
    public int provideComicPerPage() {
        return 20;
    }

}
