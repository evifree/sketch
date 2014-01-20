/*
 * Copyright 2013 Peng fei Pan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.imageloader.sample;

import me.xiaoapn.android.imageloader.R;
import me.xiaopan.android.imageloader.ImageLoader;
import me.xiaopan.android.imageloader.display.ZoomInBitmapDisplayer;
import me.xiaopan.android.imageloader.display.ZoomOutBitmapDisplayer;
import me.xiaopan.android.imageloader.process.CircleBitmapProcessor;
import me.xiaopan.android.imageloader.process.ReflectionBitmapProcessor;
import me.xiaopan.android.imageloader.task.Options;
import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		ImageLoader.getInstance().init(getBaseContext());
		ImageLoader.getInstance().getConfiguration().setDebugMode(true);
		
		Options defaultOptions = ImageLoader.getInstance().getConfiguration().getDefaultOptions();
		defaultOptions.setLoadingDrawable(getResources(), R.drawable.image_loading);	//设置加载中显示的图片
		defaultOptions.setFailureDrawable(getResources(), R.drawable.image_load_failure); 	//设置加载失败时显示的图片
		defaultOptions.setBitmapDisplayer(new ZoomOutBitmapDisplayer());
		defaultOptions.setBitmapProcessor(new ReflectionBitmapProcessor());
		
		ImageLoader.getInstance().getConfiguration().putOptions(OptionsType.VIEW_PAGER, defaultOptions.copy().setLoadingDrawable(null));
		ImageLoader.getInstance().getConfiguration().putOptions(OptionsType.LIST_VIEW, defaultOptions.copy().setBitmapProcessor(new CircleBitmapProcessor()).setBitmapDisplayer(new ZoomInBitmapDisplayer()));
		ImageLoader.getInstance().getConfiguration().putOptions(OptionsType.GALLERY, defaultOptions.copy());
		ImageLoader.getInstance().getConfiguration().putOptions(OptionsType.SIMPLE, defaultOptions.copy().setEnableMenoryCache(false));
	}
}