package fr.veridian.main.game.blocks;

public class Block {
	
	protected int x, y;
	protected boolean solid;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isSolid() {
		return solid;
	}
}
