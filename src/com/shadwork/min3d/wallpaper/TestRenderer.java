package com.shadwork.min3d.wallpaper;

import min3d.Shared;
import min3d.Utils;
import min3d.objectPrimitives.ParticleSprite;
import min3d.vos.Number3d;
import min3d.wallpaper.CommonRender;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class TestRenderer extends CommonRender {

	private Context			mContext;
	private float			mCameraPosition;

	private ParticleSprite	mEmitter;

	public TestRenderer(Context _context) {
		super(_context);
		this.mContext = _context;
		mCameraPosition = 0.5f;

	}

	@Override
	public void initScene() {
		Bitmap b = Utils.makeBitmapFromResourceId(R.drawable.particle);
		Shared.textureManager().addTextureId(b, "particle", false);
		b.recycle();
		mEmitter = new ParticleSprite(new Number3d(0, 0, 0), 0.5f, 64, "particle");
		scene.addChild(mEmitter);
	}

	@Override
	public void updateScene() {
		mEmitter.update();
		scene.camera().position.x = 0.5f - mCameraPosition;

	}

	@Override
	public void onTouchUp(int x, int y) {
		Log.i("TAG", "Touch");
	}

	@Override
	public void onOffsetsChanged(float xOffset, float yOffset, float xStep, float yStep, int xPixels, int yPixels) {
		mCameraPosition = xOffset;
	}

}
