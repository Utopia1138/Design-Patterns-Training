package org.txr.designpattersn.chapter4.memory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class CubeCreator extends ShapeCreator{
	  @Override
	    public Geometry createShape(ColorRGBA c, AssetManager a) {
	        Box b = new Box(1, 1, 1);
	        Geometry geo = new Geometry("Cube", b);
	        Material mat = new Material(a, "Common/MatDefs/Misc/Unshaded.j3md");
	        mat.setColor("Color", c);
	        geo.setMaterial(mat);
	        return geo;
	    }
}
