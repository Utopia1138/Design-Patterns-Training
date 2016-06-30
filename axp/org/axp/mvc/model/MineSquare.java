package org.axp.mvc.model;

public class MineSquare {
	private int ypos, xpos;
	private boolean revealed;
	private boolean hasMine;
	
	public MineSquare( int ypos, int xpos ) {
		this.ypos = ypos;
		this.xpos = xpos;
	}
	
	public int getY() {
		return ypos;
	}
	
	public int getX() {
		return xpos;
	}
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public void setRevealed( boolean revealed ) {
		this.revealed = revealed;
	}

	public boolean hasMine() {
		return hasMine;
	}

	public void setHasMine( boolean hasMine ) {
		this.hasMine = hasMine;
	}
}
