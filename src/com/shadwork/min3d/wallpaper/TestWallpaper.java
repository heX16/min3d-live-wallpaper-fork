package com.shadwork.min3d.wallpaper;

import min3d.wallpaper.WallpaperTemplate;

public class TestWallpaper extends WallpaperTemplate {

	@Override
	public Engine onCreateEngine() {

		TestRenderer mRenderer = new TestRenderer(this);

		return new WallpaperEngine(getBaseContext(), mRenderer.renderer);
	}

	public void onDestroy() {
		super.onDestroy();
	}

}
