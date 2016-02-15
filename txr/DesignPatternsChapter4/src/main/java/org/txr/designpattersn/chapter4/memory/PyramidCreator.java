package org.txr.designpattersn.chapter4.memory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Dome;

public class PyramidCreator extends ShapeCreator{
	  @Override
	    public Geometry createShape(ColorRGBA c, AssetManager a) {
	        Dome dome = new Dome(2,4,1.66f);
	          Geometry geo = new Geometry("pyramid", dome);
	        Material mat = new Material(a, "Common/MatDefs/Misc/Unshaded.j3md");
	        mat.setColor("Color", c);
	        geo.setMaterial(mat);
	        return geo;
	    }

	
}
