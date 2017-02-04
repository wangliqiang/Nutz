package com.app.nuts.widget.imageloader;

import android.content.Context;

/**
 * Created by 王立强 on 2017/2/4.
 */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context ctx, T config);
}
