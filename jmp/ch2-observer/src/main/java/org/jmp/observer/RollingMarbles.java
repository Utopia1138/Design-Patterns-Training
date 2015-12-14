package org.jmp.observer;

import java.util.ArrayList;
import java.util.List;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.geomipmap.lodcalc.DistanceLodCalculator;
import com.jme3.terrain.heightmap.HillHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class RollingMarbles extends SimpleApplication {

	public static void main(String[] args ) {
		SimpleApplication app = new RollingMarbles();
		app.start();
	}
	
	/**
	 * The terrain where everything takes place
	 */
	private TerrainQuad terrain;

	/**
	 * The material that will be applied to any marbles created
	 */
	private Material marbleMaterial;

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
		bulletAppState = buildPhysics();
		stateManager.attach(bulletAppState);

		Material terrainMaterial = buildTerrainMaterial();
		terrain = buildTerrain();
		terrain.setMaterial(terrainMaterial);

		// set the level of detail controller
		TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
		control.setLodCalculator(new DistanceLodCalculator(65, 2.7f));
		terrain.addControl(control);

		// add the terrain to the scene graph
		rootNode.attachChild(terrain);

		// give the terrain a rigid body controller and
		// make the physics engine aware of it
		terrain.addControl(new RigidBodyControl(0));
		bulletAppState.getPhysicsSpace().addAll(terrain);

		// set the camera just off the ground and point it forward
		cam.setLocation(new Vector3f(0, (terrain
				.getHeight(new Vector2f(0, -10)) + 5f), -10));
		cam.lookAtDirection(new Vector3f(0, 0, -1).normalizeLocal(), Vector3f.UNIT_Y);
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

}
