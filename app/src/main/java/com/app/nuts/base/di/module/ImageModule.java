package com.app.nuts.base.di.module;

import com.app.nuts.widget.imageloader.BaseImageLoaderStrategy;
import com.app.nuts.widget.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 王立强 on 2017/2/4.
 */

@Module
public class ImageModule {

    @Singleton
    @Provides
    public BaseImageLoaderStrategy provideImageLoaderStrategy(GlideImageLoaderStrategy glideImageLoaderStrategy) {
        return glideImageLoaderStrategy;
    }

}
