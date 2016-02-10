package org.red.factory;

import java.util.Random;

public class AlienLandscapeMapFactory extends MapFactory {
	public AlienLandscapeMapFactory(int gridW, int gridH) {
		super(gridW, gridH);
	}

	@Override
	public Map buildMap(int width, int height) {
		Random rand = new Random();

		// Who knows what we'll get!
		int tilecount = rand.nextInt(7) + 2;
		Tile[] tiles = new Tile[tilecount];

		for(int i = 0; i < tiles.length; ++i)
			tiles[i] = new Tile(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

		return new Map(width, height, gridW, gridH, tiles);
	}
}
