package org.jmp.observer.listener;

import java.util.logging.Logger;

import org.jmp.observer.RollingMarbles;
import org.jmp.observer.RollingMarbles.CameraMode;

import com.jme3.input.ChaseCamera;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;

/**
 * A listener that allows the user to cycle between 
 * free-fly mode and chasing an individual marble
 * 
 * @author Jacob Pappe
 *
 */
public class CameraSwitchListener implements ActionListener {

	private static final Logger logger = Logger
			.getLogger(CameraSwitchListener.class.getName());
	private RollingMarbles app;
	private Vector3f flyCameraLocation;
	private Vector3f flyCameraDirection;
	private ChaseCamera chaseCam;

	public CameraSwitchListener(RollingMarbles app) {
		this.app = app;
		
		/**
		 * Build up a chasecam that we will use to follow marbles
		 */
		chaseCam = new ChaseCamera(app.getCamera(), app.getInputManager());
		chaseCam.setSmoothMotion(true);
		chaseCam.setDragToRotate(false);
		chaseCam.setMinDistance(20f);
		chaseCam.setMaxDistance(150f);
		chaseCam.setDefaultDistance(40f);
	}

	/**
	 * Here's our main "observe" method. When the event is triggered,
	 * this will cycle between free-fly mode and chasing individual marbles.
	 */
	public void onAction(String name, boolean isPressed, float tpf) {
		

		// only trigger when the key is released
		if (isPressed)
			return;
		
		logger.info("CameraSwitchListener: " + app.getCurrentMode() + ", " + app.getCameraChaseMarbleIndex() );

		switch (app.getCurrentMode()) {
		case CHASE:
			// first, stop following whatever marble we're following
			unFollowMarble(app.getCameraChaseMarbleIndex());
			// try to move on to the next marble
			app.setCameraChaseMarbleIndex(app.getCameraChaseMarbleIndex() + 1);
			// if we've moved past the last one, then go back to free-fly mode
			if (app.getCameraChaseMarbleIndex() == app.getMarbles().size()) {
				// we were on the last marble, so cycle back to fly mode
				app.setCameraChaseMarbleIndex(0);
				switchToFlyMode();
			} else {
				// otherwise, follow the next marble
				followMarble(app.getCameraChaseMarbleIndex());
			}
			break;
		case FLY:
			/**
			 * If we're in fly mode, try to switch to chase mode. This
			 * only works if we actually have marbles to chase!
			 */
			if (app.getMarbles().size() == 0) {
				logger.info("Can't switch to chase mode because we have nothing to chase");
				return;
			}
			switchToChaseMode();
			break;
		default:
			logger.warning("We exist beyond space and time");
			/**
			 * How did we get here? What does it all mean? Change to fly mode
			 * just to return to sanity
			 */
			switchToFlyMode();
			break;

		}

	}

	/**
	 * Change to a chase camera that follows a marble
	 */
	private void switchToChaseMode() {
		logger.info("Switching to chase mode");
		logger.info("Camera distance: " + chaseCam.getMinDistance() + ", " + chaseCam.getMaxDistance() );
		
		// save copies of the current camera position and direction to return to later
		flyCameraLocation = app.getCamera().getLocation().clone();
		flyCameraDirection = app.getCamera().getDirection().clone();

		// disable the flycamera and keep the damn mouse cursor invisible
		app.getFlyByCamera().setEnabled(false);
		app.getInputManager().setCursorVisible(false);
		// now chase a marble
		followMarble(app.getCameraChaseMarbleIndex());

		app.setCurrentMode(CameraMode.CHASE);
	}

	/**
	 * Point our chase camera control at one of the marbles
	 * @param cameraChaseMarbleIndex
	 */
	private void followMarble(int cameraChaseMarbleIndex) {
		logger.info("Following marble at index " + cameraChaseMarbleIndex );
		app.getMarbles().get(cameraChaseMarbleIndex).addControl(chaseCam);
	}

	/**
	 * Stop following a specific marble
	 * @param cameraChaseMarbleIndex
	 */
	private void unFollowMarble(int cameraChaseMarbleIndex) {
		logger.info("Unfollowing marble at index " + cameraChaseMarbleIndex );
		app.getMarbles().get(cameraChaseMarbleIndex).removeControl(chaseCam);
	}

	/**
	 * Change to fly mode
	 */
	private void switchToFlyMode() {
		logger.info("Switching to fly mode");
		// restore camera position and direction
		app.getCamera().setLocation(flyCameraLocation);
		app.getCamera().lookAtDirection(flyCameraDirection, Vector3f.UNIT_Y);
		// turn flycamera on
		app.getFlyByCamera().setEnabled(true);
		// keep the damn mouse cursor away!
		app.getInputManager().setCursorVisible(false);
		// update app state
		app.setCurrentMode(CameraMode.FLY);
	}
}
