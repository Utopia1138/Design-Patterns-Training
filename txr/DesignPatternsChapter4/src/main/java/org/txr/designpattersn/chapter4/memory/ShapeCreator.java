package org.txr.designpattersn.chapter4.memory;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;

public abstract class ShapeCreator {
	public abstract Geometry createShape(ColorRGBA c, AssetManager a);

}
