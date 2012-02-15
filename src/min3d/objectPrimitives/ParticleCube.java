package min3d.objectPrimitives;

import min3d.core.Object3dContainer;
import min3d.vos.Color4;
import min3d.vos.Number3d;

public class ParticleCube extends Object3dContainer {
	Number3d	mEmiterPosition;
	Particle[]	mParticle;
	Box[]		mBoxArray;

	public ParticleCube(Number3d _position, float _size, int _count, String _texture) {
		mBoxArray = new Box[_count];
		mParticle = new Particle[_count];
		mEmiterPosition = _position.clone();

		for (int i = 0; i < _count; i++)
		{

			Particle mTempParticle = getParticle();

			mParticle[i] = mTempParticle;

			// Box mTempBox = new Box(1, 1, 1, null, true, true, false);
			Box mTempBox = new Box(1, 1, 1, mParticle[i].getColor());
			if (_texture != null)
			{
				mTempBox.textures().addById(_texture);
			}

			mTempBox.lightingEnabled(false);
			mTempBox.scale().x = _size;
			mTempBox.scale().y = _size;
			mTempBox.scale().z = _size;
			mTempBox.position().setAllFrom(mTempParticle.getPosition());

			mBoxArray[i] = mTempBox;
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
				mParticle[i] = getParticle();
			}

			mBoxArray[i].position().setAllFrom(mParticle[i].getPosition());
			mBoxArray[i].colors().ChangeColor(mParticle[i].getColor());
		}

	}

	private Particle getParticle() {
		return new Particle(mEmiterPosition, new Number3d(0.2f, 0.2f, 0.2f), new Number3d(0.5f), new Number3d(0, 0.05f, 0), new Color4(255, 255, 255, 255), 0.9f, 0.8f, 0.9f, (int) (30 * Math.random()));
	}

}
