package org.jmp.observer.listener;

import java.util.logging.Logger;

import org.jmp.observer.RollingMarbles;
import org.jmp.observer.RollingMarbles.CameraMode;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.util.TangentBinormalGenerator;

public class MarbleDropper implements ActionListener {

	private static final Logger logger = Logger.getLogger(MarbleDropper.class
			.getName());

	// this controls how big the marbles are
	private final static float MARBLE_RADIUS = 5f;

	// determines how high above the terrain we drop marbles from
	private final static float DROP_HEIGHT = 100f;

	private final static float MARBLE_MASS = 10f;

	private Material marbleMaterial;

	private RollingMarbles app;

	public MarbleDropper(RollingMarbles app) {
		this.app = app;

		/**
		 * Build up the material we'll apply to any generated marbles
		 * 
		 * TODO: make fancier
		 */
		marbleMaterial = new Material(app.getAssetManager(),
				"Common/MatDefs/Light/Lighting.j3md");
		marbleMaterial.setTexture("DiffuseMap", 
		        app.getAssetManager().loadTexture("Textures/Terrain/Pond/Pond.jpg"));
		marbleMaterial.setTexture("NormalMap", 
				app.getAssetManager().loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
		marbleMaterial.setBoolean("UseMaterialColors", true);
		marbleMaterial.setColor("Diffuse", ColorRGBA.White);
		marbleMaterial.setColor("Specular", ColorRGBA.White);
		marbleMaterial.setFloat("Shininess", 64f);
		
		
		/*marbleMaterial = new Material(app.getAssetManager(), "Common/MatDefs/Misc/ColoredTextured.j3md");
		marbleMaterial.getAdditionalRenderState().setWireframe(true);
		marbleMaterial.setColor("Color", ColorRGBA.Green);*/

	}

	public void onAction(String name, boolean isPressed, float tpf) {
		logger.info("MarbleDropper: " + name + ", " + isPressed);
		// only trigger when the key is released
		if (isPressed)
			return;

		// only allow this action when we're in free-flying mode
		if (app.getCurrentMode() != CameraMode.FLY)
			return;

		Camera cam = app.getCamera();
		AppSettings settings = app.getSettings();

		/**
		 * Draw a Ray from the center of the camera position to the terrain
		 */
		Vector3f source = cam
				.getWorldCoordinates(new Vector2f(settings.getWidth() / 2,
						settings.getHeight() / 2), 0.0f);
		Vector3f direction = cam
				.getWorldCoordinates(new Vector2f(settings.getWidth() / 2,
						settings.getHeight() / 2), 0.3f);
		direction.subtractLocal(source).normalizeLocal();
		Ray ray = new Ray(source, direction);
		CollisionResults results = new CollisionResults();
		int numCollisions = app.getTerrain().collideWith(ray, results);

		/**
		 * If we hit the terrain with that Ray, then create a new marble and
		 * drop it onto those coordinates
		 */
		if (numCollisions > 0) {
			CollisionResult hit = results.getClosestCollision();
			Geometry marble = createNewMarble();
			placeMarble(marble, hit);
			marble.addControl(new RigidBodyControl(new SphereCollisionShape(
					MARBLE_RADIUS), MARBLE_MASS));

			app.getRootNode().attachChild(marble);
			app.getBulletAppState().getPhysicsSpace().add(marble);
			app.getMarbles().add(marble);
			logger.info("Marble dropped!");
		} else {
			logger.info("Can not drop marble outside of the terrain");
		}

	}

	private void placeMarble(Geometry marble, CollisionResult hit) {
		Vector2f xz = new Vector2f(hit.getContactPoint().x,
				hit.getContactPoint().z);
		float height = app.getTerrain().getHeight(xz) + DROP_HEIGHT;
		marble.setLocalTranslation(new Vector3f(xz.x, height, xz.y));
	}

	private Geometry createNewMarble() {
		Sphere mesh = new Sphere(10, 10, MARBLE_RADIUS);
		Geometry sphere = new Geometry("cannonball", mesh);
		mesh.setTextureMode(Sphere.TextureMode.Projected);
		TangentBinormalGenerator.generate(mesh);

		sphere.setMaterial(marbleMaterial);
		return sphere;
	}
}
