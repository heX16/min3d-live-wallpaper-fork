package min3d.objectPrimitives;

import min3d.core.Object3dContainer;
import min3d.vos.Color4;
import min3d.vos.Number3d;

public class ParticleSprite extends Object3dContainer {
	Number3d	mEmiterPosition;
	Particle[]	mParticle;
	Rectangle[]	mBoxArray;

	public ParticleSprite(Number3d _position, float _size, int _count, String _texture) {
		mBoxArray = new Rectangle[_count];
		mParticle = new Particle[_count];
		mEmiterPosition = _position.clone();

		for (int i = 0; i < _count; i++)
		{

			Particle mTempParticle = getParticle(new Number3d(mEmiterPosition.x, mEmiterPosition.y, mEmiterPosition.z + i * 0.05f));

			mParticle[i] = mTempParticle;

			Rectangle mTempRectangle = new Rectangle(1, 1, 1, 1, mParticle[i].getColor());
			mTempRectangle.doubleSidedEnabled(true);
			if (_texture != null)
			{
				mTempRectangle.textures().addById(_texture);
			}

			mTempRectangle.lightingEnabled(false);
			mTempRectangle.scale().x = _size;
			mTempRectangle.scale().y = _size;
			mTempRectangle.scale().z = _size;
			mTempRectangle.position().setAllFrom(mTempParticle.getPosition());

			mBoxArray[i] = mTempRectangle;
			mBoxArray[i].position().setAllFrom(mParticle[i].getPosition());
			this.addChild(mBoxArray[i]);
		}
	}

	public void update() {
		for (int i = 0; i < mParticle.length; i++)
		{
			mParticle[i].update();
			if (!mParticle[i].ismLife())
			{
				// Log.w("--", "Die " + i);
				mParticle[i] = getParticle(mParticle[i].getPosition());
			}

			mBoxArray[i].position().setAllFrom(mParticle[i].getPosition());
			mBoxArray[i].colors().ChangeColor(mParticle[i].getColor());
		}

	}

	private Particle getParticle(Number3d _old) {

		return new Particle(//
				new Number3d(mEmiterPosition.x, mEmiterPosition.y, _old.z), // _position
				new Number3d(0.2f, 0.2f, 0.2f),// _size
				new Number3d(rndSign(0.5f), rndSign(0.5f), 0f),// _speed
				new Number3d(0, 0.05f, 0),// _gravity
				new Color4(255, 255, 255, 255),// _color
				0.9f,// _friction
				0.8f,// _alpha
				0.9f,// _scale
				(int) (30 * Math.random())// _time_live
		);
	}

	private float rnd(float _value) {
		return (float) (Math.random() * _value);
	}

	private float rndSign(float _value) {
		return (float) (_value / 2 - (Math.random() * _value));
	}

	private int rndI(int _value) {
		return (int) (Math.random() * (float) _value);
	}

	private short rndS(int _value) {
		return (short) (Math.random() * (float) _value);
	}

}
