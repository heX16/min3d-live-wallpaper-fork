package min3d.wallpaper;

import min3d.Shared;
import min3d.core.Renderer;
import min3d.core.Scene;
import min3d.interfaces.ISceneController;
import android.content.Context;
import android.os.Handler;

public class CommonRender implements ISceneController {
	public Scene		scene;
	public Renderer		renderer;

	protected Handler	_initSceneHander;
	protected Handler	_updateSceneHander;

	final Runnable		_initSceneRunnable		= new Runnable() {
													public void run() {
														onInitScene();
													}
												};

	final Runnable		_updateSceneRunnable	= new Runnable() {
													public void run() {
														onUpdateScene();
													}
												};

	public CommonRender(Context context) {
		_initSceneHander = new Handler();
		_updateSceneHander = new Handler();

		Shared.context(context);
		scene = new Scene(this);
		renderer = new Renderer(scene);
		Shared.renderer(renderer);

		renderer.setOnTouchInterfaceListener(new Renderer.OnTouchInterface() {
			@Override
			public void onTouchInterface(int x, int y) {
				// Log.w("CommonRender", "On TOUCH UP");
				onTouchUp(x, y);
			}
		});

		renderer.setOffsetsChangedInterfaceListener(new Renderer.OnOffsetsChangedInterface() {
			@Override
			public void onOffsetsChangedInterface(float xOffset, float yOffset, float xStep, float yStep, int xPixels, int yPixels) {
				onOffsetsChanged(xOffset, yOffset, xStep, yStep, xPixels, yPixels);
			}
		});
	}

	@Override
	public void initScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScene() {
		// TODO Auto-generated method stub

	}

	public boolean isPreview() {
		if (renderer == null)
		{
			return false;
		}
		return renderer.ismIsPreview();
	}

	/**
	 * Called _after_ scene init (ie, after initScene). Unlike initScene(), gets called from the UI thread.
	 */
	public void onInitScene() {
	}

	/**
	 * Called _after_ updateScene() Unlike initScene(), gets called from the UI thread.
	 */
	public void onUpdateScene() {
	}

	public Handler getInitSceneHandler() {
		return _initSceneHander;
	}

	public Handler getUpdateSceneHandler() {
		return _updateSceneHander;
	}

	public Runnable getInitSceneRunnable() {
		return _initSceneRunnable;
	}

	public Runnable getUpdateSceneRunnable() {
		return _updateSceneRunnable;
	}

	public void onOffsetsChanged(float xOffset, float yOffset, float xStep, float yStep, int xPixels, int yPixels) {
	}

	public void onTouchUp(int x, int y) {
	}

}
