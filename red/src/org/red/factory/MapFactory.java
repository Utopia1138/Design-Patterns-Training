package org.red.factory;

public abstract class MapFactory {
	protected final int gridW, gridH;

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
}
