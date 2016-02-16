package org.txr.designpattersn.chapter4.memory;


import java.util.concurrent.ThreadLocalRandom;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.*;
import com.jme3.system.AppSettings;

public class MemoryGame extends SimpleApplication {
	private static int NUMBER_OF_SHAPES = 5;
	protected Node shapeNode;
	@Override
	public void simpleInitApp() {
		
		flyCam.setEnabled(false);
		shapeNode = new Node();
		float xStart = -7f;
		for (int i =0; i < NUMBER_OF_SHAPES; i++) {
			ShapeCreator creator = getRandomShapeCreator();
			Geometry geom = creator.createShape(getRandomColor().getColor(), assetManager);
			geom.setLocalTranslation(new Vector3f(xStart, 0f, 0f));
			xStart +=4;
			shapeNode.attachChild(geom);
			
		}
		shapeNode.setLocalScale(0.5f);
		shapeNode.addControl(new MyControl());
		rootNode.attachChild(shapeNode);
	
	}

	public static void main(String[] args) {
		AppSettings settings = new AppSettings(true);
		settings.setTitle("Memory Game");
		MemoryGame app = new MemoryGame();
		app.setSettings(settings);
		app.start();
	}

	private Color getRandomColor() {
		Color [] colors = Color.values();
		int index = ThreadLocalRandom.current().nextInt(0,colors.length);
		return colors[index];
	}
	
	private ShapeCreator getRandomShapeCreator() {
		Shape [] shapes = Shape.values();
		int index = ThreadLocalRandom.current().nextInt(0,shapes.length);
		if (shapes[index].equals(Shape.CIRCLE)) {
			return new SphereCreator();
		}
		if (shapes[index].equals(Shape.SQUARE)) {
			return new CubeCreator();
		}
		if (shapes[index].equals(Shape.PYRAMID)) {
			return new PyramidCreator();
		}
		return null;
		

	}
	
	class MyControl extends UpdateControl{
		float time = 3f;
		float timeCount;
		public MyControl() {
			
		}
		protected void controlUpdate(float tpf) {
			timeCount = timeCount + tpf;
			if (timeCount > time) {
				spatial.getParent().detachChild(spatial);
			}
		}
		
	}
	
	
	

}
