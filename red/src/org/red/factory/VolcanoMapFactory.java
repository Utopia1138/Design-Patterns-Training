package org.red.factory;

public class VolcanoMapFactory implements MapFactory {
	private final int gridW, gridH;
	
	public VolcanoMapFactory(int gridW, int gridH) {
		this.gridW = gridW;
		this.gridH = gridH;
	}

	@Override
	public Map buildMap(int width, int height) {
		Map map = new Map(width, height, gridW, gridH,
				new Tile(0.f, 0.f, 0.f), // Unlit!
				new Tile(0.3f, 0.3f, 0.3f), // Rock!
				new Tile(0.7f, 0.1f, 0.1f) // Lava!
				);

		return map;
	}
}
