package org.red.factory;

public abstract class MapFactory {
	protected int gridW, gridH;

	public MapFactory(int gridW, int gridH) {
		this.gridW = gridW;
		this.gridH = gridH;
	}

	public abstract Map buildMap(int width, int height);

	public int getTilesWide() {
		return gridW;
	}

	public int getTilesHigh() {
		return gridH;
	}

	public MapFactory setTiles( int w, int h ) {
		this.gridH = h;
		this.gridW = w;
		return this;
	}
}
