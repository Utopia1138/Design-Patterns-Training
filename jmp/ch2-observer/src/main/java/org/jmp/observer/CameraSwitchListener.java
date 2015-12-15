package org.jmp.observer;

import java.util.logging.Logger;

import com.jme3.input.controls.ActionListener;

public class CameraSwitchListener implements ActionListener {

	private static final Logger logger = Logger.getLogger(CameraSwitchListener.class.getName());
	private RollingMarbles app;
	
	public CameraSwitchListener( RollingMarbles app ) {
		this.app = app;
	}

	public void onAction(String name, boolean isPressed, float tpf) {
		logger.info("CameraSwitchListener: " + name + ", " + isPressed );
		
	}
}
