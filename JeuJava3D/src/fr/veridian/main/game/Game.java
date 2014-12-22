package fr.veridian.main.game;

public class Game {
	
	Level level;
	
	public Game() {
		level = new Level();
	}
	
	public void update() {
		level.update();
	}
	
	public void render() {
		level.render();
	}
}
