package fr.veridian.main.game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.veridian.main.game.blocks.Block;
import fr.veridian.main.game.blocks.WallBlock;
import fr.veridian.main.math.Vector3f;
import fr.veridian.main.render.Renderer;
import fr.veridian.main.render.Texture;

public class Level {
	int renderingList;

	Block[] blocks;
	int width, height;

	public Level() {
		compile();
	}
	
	public void compile() {
		BufferedImage map = null;
		try {
			map = ImageIO.read(Game.class.getResource("/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = map.getWidth();
		height = map.getHeight();

		int[] pixels = new int[width * height];
		map.getRGB(0, 0, width, height, pixels, 0, width);
		
		blocks = new Block[pixels.length];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				int i = x + y * width;
				
				if (pixels[i] == 0xFF000000)
					blocks[i] = new Block(x, y);
				
				if (pixels[i] == 0xFFffffff)
					blocks[i] = new WallBlock(x, y);
				
			}
		}
		
		compileRendering();
	}

	public void compileRendering() {
		renderingList = glGenLists(1);

		glNewList(renderingList, GL_COMPILE);
		glBegin(GL_QUADS);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Block block = getBlock(x, y);
				
				if (!block.isSolid()) {
					Renderer.setFloorData(x, y, new Vector3f(1, 1, 1), block.getTexture(Block.FLOOR_TEXTURE));
					Renderer.setCeilingData(x, y, new Vector3f(1, 1, 1), block.getTexture(Block.CEILING_TEXTURE));
				}

				Block left = getBlock(x + 1, y);
				Block down = getBlock(x, y + 1);
				if (block.isWall()) {
					if (!left.isWall())
						Renderer.setWallData(x + 1, y + 1, x + 1, y, new Vector3f(0.9f, 0.9f, 0.9f), block.getTexture(Block.WALL_TEXTURE));
					
					if (!down.isWall())
						Renderer.setWallData(x, y + 1, x + 1, y + 1, new Vector3f(0.8f, 0.8f, 0.8f), block.getTexture(Block.WALL_TEXTURE));
				} else {
					if (left.isWall())
						Renderer.setWallData(x + 1, y, x + 1, y + 1, new Vector3f(0.9f, 0.9f, 0.9f), block.getTexture(Block.WALL_TEXTURE));
					
					if (down.isWall())
						Renderer.setWallData(x + 1, y + 1, x, y + 1, new Vector3f(0.8f, 0.8f, 0.8f), block.getTexture(Block.WALL_TEXTURE));
				}

			}
		}
		glEnd();
		glEndList();
	}
	
	public Block getBlock(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return new WallBlock(x, y);
		}
		return blocks[x + y * width];
	}
	
	public void update() {

	}

	public void render() {
		Texture.env.bind();
		glCallList(renderingList);
	}
}
