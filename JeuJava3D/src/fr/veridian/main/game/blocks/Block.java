package fr.veridian.main.game.blocks;

public class Block {
	public static final int FLOOR_TEXTURE = 0;
	public static final int CEILING_TEXTURE = 1;
	public static final int WALL_TEXTURE = 2;
	
	protected int x, y;
	protected boolean solid = false;
	protected boolean wall = false;
	protected int[] texture = new int[3];

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		
		texture = new int[]{1, 2, 0};
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
	
	public boolean isWall() {
		return wall;
	}
	
	public int getTexture(int tex) {
		return texture[tex];
	}
}
