package fr.veridian.main.game.blocks;

public class WallBlock extends Block {

	public WallBlock(int x, int y) {
		super(x, y);
		wall = true;
		solid = true;
		texture = new int[]{1, 2, 0};
	}
}
