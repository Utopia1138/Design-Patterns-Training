package org.red.factory;

public class OverworldMapFactory implements MapFactory {
	private final int gridW, gridH;
	
	public OverworldMapFactory(int gridW, int gridH) {
		this.gridW = gridW;
		this.gridH = gridH;
	}

	@Override
	public Map buildMap(int width, int height) {
		Map map = new Map(width, height, gridW, gridH,
				new Tile(0.f, 0.3f, 1.0f), // Sea!
				new Tile(0.8f, 0.8f, 0.0f), // Sand!
				new Tile(0.f, 0.6f, 0.3f), // Grass!
				new Tile(1.0f, 1.0f, 1.0f) // Snow!
				);

		return map;
	}

	
}
