package fr.veridian.main;

import fr.veridian.main.render.DisplayManager;

public class Main {
	
	boolean running = false;
	
	public Main() {
		DisplayManager.create(720, 480, "Jeu 3D lwjgl");
	}
	
	public void start() {
		running = true;
		loop();
	}
	
	public void stop() {
		running = false;
	}
	
	public void exit() {
		DisplayManager.dispose();
		System.exit(0);
	}
	 
	public void loop() {
		while(running) {
			if (DisplayManager.isClosed()) stop();
			DisplayManager.update();
		}
		exit();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
