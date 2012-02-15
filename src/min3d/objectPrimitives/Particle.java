package min3d.objectPrimitives;

import min3d.vos.Color4;
import min3d.vos.Number3d;

public class Particle {
	private Number3d	mPosition;
	private Number3d	mSize;
	private Number3d	mSpeed;
	private Number3d	mGravity;

	private Color4		mColor;

	private float		mFriction;
	private float		mAlpha;
	private float		mScale;

	private int			mTimeLive;
	private boolean		mLife;

	public Particle(Number3d _position, Number3d _size, Number3d _speed, Number3d _gravity, Color4 _color, float _friction, float _alpha, float _scale, int _time_live) {
		this.mPosition = _position.clone();
		this.mSize = _size.clone();
		this.mSpeed = _speed.clone();
		this.mGravity = _gravity.clone();
		this.mColor = _color;
		this.mFriction = _friction;
		this.mAlpha = _alpha;
		this.mScale = _scale;

		this.mLife = true;
		this.mTimeLive = _time_live;
		// Log.w("-", "Init live " + mTimeLive);
	}

	public void update() {
		if (mLife)
		{
			// Log.w("-", "-" + mTimeLive);
			if (mTimeLive > 0)
			{
				mTimeLive--;
				// Log.w("-", "-" + mTimeLive);
			}
			//
			mSpeed.multiply(mFriction);
			mPosition.add(mSpeed);
			mPosition.subtract(mGravity);
			mSize.multiply(mScale);
			mColor.a = (short) ((float) mColor.a * mAlpha);
		}
	}

	public synchronized boolean ismLife() {
		return mTimeLive > 0;
	}

	public Number3d getPosition() {
		// Log.w("-", "-" + mPosition)
		return mPosition;
	}

	public Number3d getScale() {
		return mSize;
	}

	public Color4 getColor() {
		return mColor;
	}

}
