package org.txr.designpattersn.chapter4.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public class MemoryGame extends SimpleApplication {

	@Override
	public void simpleInitApp() {

		ShapeCreator creator1 = getRandomShapeCreator();
		ShapeCreator creator2 = getRandomShapeCreator();
		ShapeCreator creator3 = getRandomShapeCreator();
		
		
		Geometry geom1 = creator1.createShape(getRandomColor().getColor(), assetManager);
		Geometry geom2 = creator2.createShape(getRandomColor().getColor(), assetManager);
		geom2.setLocalTranslation(new Vector3f(3f, 0f, 0f));
		Geometry geom3 = creator3.createShape(getRandomColor().getColor(), assetManager);
		geom3.setLocalTranslation(new Vector3f(6f, 0f, 0f));

		Node shapeNode = new Node();
		shapeNode.attachChild(geom1);
		shapeNode.attachChild(geom2);
		shapeNode.attachChild(geom3);
		rootNode.attachChild(shapeNode);
	}

	public static void main(String[] args) {
		MemoryGame app = new MemoryGame();
		app.start();
	}

	private List<Geometry> createGeometries(int number) {
		List<Geometry> list = new ArrayList<Geometry>();

		return list;
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


}
