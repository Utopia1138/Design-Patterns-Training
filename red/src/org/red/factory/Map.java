package org.red.factory;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class Map implements Renderable {
	private final byte[][] map;
	private final Tile[] tiles;
	int sw, sh;
	
	public Map(int screenW, int screenH, int w, int h, Tile... tiles) {
		map = new byte[h][w];
		this.tiles = tiles;
		this.sw = screenW;
		this.sh = screenH;
	}

	public byte[][] getMap() {
		return map;
	}

	public int getTileCount() {
		return tiles.length;
	}

	public int getTilesWide() {
		return map[0].length;
	}

	public int getTilesHigh() {
		return map.length;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		Arrays.stream(map).forEach(ba -> {
			for( byte b : ba ) str.append("[" + b + "] ");
			str.append("\n");
		});
		return str.toString();
	}

	@Override
	public void render() {
		float scaleH = sh / map.length;

		for( int h = 0; h < map.length; ++h ) {
			byte[] row = map[h];
			float scaleW = sw / row.length;

			for( int w = 0; w < row.length; ++w ) {
				GL11.glPushMatrix();
				GL11.glTranslatef(scaleW * w, scaleH * h, 0.f);
				GL11.glScalef(scaleW, scaleH, 1.f);
				tiles[row[w]].render();
				GL11.glPopMatrix();
			}
		}
	}
}
