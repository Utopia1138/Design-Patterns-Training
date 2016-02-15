package org.txr.designpattersn.chapter4.memory;

import com.jme3.math.ColorRGBA;

public enum Color {
	 YELLOW("yellow", ColorRGBA.Yellow), RED ("red", ColorRGBA.Red), BLUE("blue",ColorRGBA.Blue),
	    ORANGE("Orange", ColorRGBA.Orange), WHITE ("white", ColorRGBA.White);
	    
	    private String name;
	    private ColorRGBA color;
	    Color (String name, ColorRGBA c) {
	        this.name = name;
	        this.color = c;
	    }
	    
	    public ColorRGBA getColor() {
	        return color;
	    }
	    
	    public String getName() {
	        return this.name;
	    }
}
