package org.axp.mvc.model;

public class MineSquare {
	private final int ypos, xpos;
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
	
	protected void setRevealed( boolean revealed ) {
		this.revealed = revealed;
	}

	public boolean hasMine() {
		return hasMine;
	}

	protected void setHasMine( boolean hasMine ) {
		this.hasMine = hasMine;
	}
}
