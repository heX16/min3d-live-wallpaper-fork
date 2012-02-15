package min3d.objectPrimitives;

import min3d.core.Object3dContainer;
import min3d.vos.Color4;
import min3d.vos.Number3d;

public class ParticleCommon extends Object3dContainer {
	Particle[]			mParticle;
	Object3dContainer[]	mObjectArray;
	Object3dContainer	mBaseObject;

	public ParticleCommon(Object3dContainer _object, int _count) {
		mObjectArray = new Object3dContainer[_count];
		mParticle = new Particle[_count];
		mBaseObject = _object.clone();

		for (int i = 0; i < _count; i++)
		{
			mParticle[i] = getParticle();

			Object3dContainer mTempObject = mBaseObject.clone();
			mBaseObject.position().setAllFrom(mParticle[i].getPosition());
			mBaseObject.scale().setAllFrom(mParticle[i].getScale());

			mObjectArray[i] = mTempObject;
			this.addChild(mObjectArray[i]);

		}
	}

	public void update() {
		for (int i = 0; i < mParticle.length; i++)
		{
			mParticle[i].update();
			if (!mParticle[i].ismLife())
			{
				mParticle[i] = getParticle();
			}
			mObjectArray[i].position().setAllFrom(mParticle[i].getPosition());
			mObjectArray[i].scale().setAllFrom(mParticle[i].getScale());
			mObjectArray[i].colors().ChangeColor(mParticle[i].getColor());
		}

	}

	private Particle getParticle() {
		return new Particle(mBaseObject.position(), new Number3d(0.2f, 0.2f, 0.2f), new Number3d(0.2f), new Number3d(0, 0.05f, 0), new Color4(255, 255, 255, 255), 0.9f, 0.8f, 0.9f, (int) (30 * Math.random()));
	}

}
