package org.txr.designpattersn.chapter4.memory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

public class SphereCreator extends ShapeCreator {

	@Override
	public Geometry createShape(ColorRGBA c, AssetManager a) {

		Sphere sphere = new Sphere(25, 25, 1f);
		Geometry geo = new Geometry("sphere", sphere);
		Material mat = new Material(a, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", c);
		geo.setMaterial(mat);
		return geo;
	}

}
