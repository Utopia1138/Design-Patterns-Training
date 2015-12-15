package org.jmp.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.geomipmap.lodcalc.DistanceLodCalculator;
import com.jme3.terrain.heightmap.HillHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class RollingMarbles extends SimpleApplication {

	private static final Logger logger = Logger.getLogger(RollingMarbles.class
			.getName());

	// some constants for input events
	public static final String SWITCH_CAMERA_MODE = "SWITCH_CAMERA_MODE";
	public static final String DROP_MARBLE = "DROP_MARBLE";

	public static void main(String[] args) {
		SimpleApplication app = new RollingMarbles();
		app.start();
	}

	/**
	 * 
	 * The modes that the camera may be in: we're either in free-fly mode or
	 * we're chasing one of the marbles
	 *
	 */
	public enum CameraMode {
		FLY, CHASE
	};

	private CameraMode currentMode;
	private int cameraChaseMarbleIndex = 0;

	/**
	 * The terrain where everything takes place
	 */
	private TerrainQuad terrain;

	/**
	 * The physics appstate that makes everything work together
	 */
	private BulletAppState bulletAppState;

	/**
	 * This is how we'll keep track of the generated marbles
	 */
	private List<Geometry> marbles = new ArrayList<Geometry>();

	@Override
	public void simpleInitApp() {

		// build an instance of the physics engine and attach it to the
		// the app's state manager
		bulletAppState = buildPhysics();
		stateManager.attach(bulletAppState);

		// build a terrain and apply a material to it
		Material terrainMaterial = buildTerrainMaterial();
		terrain = buildTerrain();
		terrain.setMaterial(terrainMaterial);
		terrain.setLocalScale(new Vector3f(2, 1, 2));
		terrain.setLocked(true);

		// set the level of detail controller
		TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
		control.setLodCalculator(new DistanceLodCalculator(65, 2.7f));
		terrain.addControl(control);

		// add the terrain to the scene graph
		rootNode.attachChild(terrain);

		/**
		 * Create a directional light source, otherwise
		 * some of our fancy textures will be invisible
		 */
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(1, -5, -2).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);

		// give the terrain a rigid body controller and
		// make the physics engine aware of it
		terrain.addControl(new RigidBodyControl(0));
		bulletAppState.getPhysicsSpace().addAll(terrain);

		// start off in free-fly mode
		this.currentMode = CameraMode.FLY;
		// set the camera just off the ground and point it forward
		cam.setLocation(new Vector3f(0, (terrain
				.getHeight(new Vector2f(0, -10)) + 10f), -10));
		cam.lookAtDirection(new Vector3f(0, 0, -1).normalizeLocal(),
				Vector3f.UNIT_Y);
		// set the speed of the camera when mouse-controlled
		flyCam.setMoveSpeed(50f);

		// assign our user inputs
		setupInput();
	}

	/**
	 * Create a bunch of events and tie them to specific user input. Then
	 * register observers of those events.
	 */
	private void setupInput() {

		inputManager.addMapping(SWITCH_CAMERA_MODE, new KeyTrigger(
				KeyInput.KEY_TAB));
		inputManager.addMapping(DROP_MARBLE, new MouseButtonTrigger(
				MouseInput.BUTTON_LEFT));

		inputManager.addListener(new CameraSwitchListener(this),
				SWITCH_CAMERA_MODE);
		inputManager.addListener(new MarbleDropper(this), DROP_MARBLE);
	}

	private TerrainQuad buildTerrain() {
		HillHeightMap heightmap = null;
		HillHeightMap.NORMALIZE_RANGE = 100;
		try {
			heightmap = new HillHeightMap(513, 500, 50, 100, (byte) 3);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		terrain = new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap());
		return terrain;
	}

	private Material buildTerrainMaterial() {
		Material matRock = new Material(assetManager,
				"Common/MatDefs/Terrain/Terrain.j3md");
		matRock.setTexture("Alpha",
				assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
		Texture grass = assetManager
				.loadTexture("Textures/Terrain/splat/grass.jpg");
		grass.setWrap(WrapMode.Repeat);
		matRock.setTexture("Tex1", grass);
		matRock.setFloat("Tex1Scale", 64f);
		Texture dirt = assetManager
				.loadTexture("Textures/Terrain/splat/dirt.jpg");
		dirt.setWrap(WrapMode.Repeat);
		matRock.setTexture("Tex2", dirt);
		matRock.setFloat("Tex2Scale", 32f);
		Texture rock = assetManager
				.loadTexture("Textures/Terrain/splat/road.jpg");
		rock.setWrap(WrapMode.Repeat);
		matRock.setTexture("Tex3", rock);
		matRock.setFloat("Tex3Scale", 128f);
		return matRock;
	}

	private BulletAppState buildPhysics() {
		bulletAppState = new BulletAppState();
		bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
		return bulletAppState;
	}

	public List<Geometry> getMarbles() {
		if (marbles == null) {
			marbles = new ArrayList<Geometry>();
		}
		return marbles;
	}

	public CameraMode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(CameraMode currentMode) {
		this.currentMode = currentMode;
	}

	public int getCameraChaseMarbleIndex() {
		return cameraChaseMarbleIndex;
	}

	public void setCameraChaseMarbleIndex(int cameraChaseMarbleIndex) {
		this.cameraChaseMarbleIndex = cameraChaseMarbleIndex;
	}

	/**
	 * Expose the app settings so our listeners can use it
	 * 
	 * @return
	 */
	public AppSettings getSettings() {
		return settings;
	}

	public TerrainQuad getTerrain() {
		return terrain;
	}

	public BulletAppState getBulletAppState() {
		return bulletAppState;
	}

}
