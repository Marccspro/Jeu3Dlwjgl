package fr.veridian.main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.veridian.main.game.Game;
import fr.veridian.main.math.Vector3f;
import fr.veridian.main.render.Camera;
import fr.veridian.main.render.DisplayManager;
import fr.veridian.main.render.Renderer;
import fr.veridian.main.render.Shader;

public class Main {

	public static final float FRAME_CAP = 9000;
	boolean running = false;

	Camera cam;
	Game game;
	
	public Main() {
		DisplayManager.create(720, 480, "Jeu 3D lwjgl");
		cam = new Camera(new Vector3f(0, 0, 0));
		cam.setPerspectiveProjection(70.0f, 0.1f, 100.0f);
		
		game = new Game();
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

		long lastTickTime = System.nanoTime();
		long lastRenderTime = System.nanoTime();

		double tickTime = 1000000000.0 / 60.0;
		double renderTime = 1000000000.0 / FRAME_CAP;

		int ticks = 0;
		int frames = 0;

		long timer = System.currentTimeMillis();

		while (running) {
			if (DisplayManager.isClosed()) stop();

			if (System.nanoTime() - lastTickTime > tickTime) {
				lastTickTime += tickTime;
				update();
				ticks++;
			} else if (System.nanoTime() - lastRenderTime > renderTime) {
				lastRenderTime += renderTime;
				render();
				DisplayManager.update();
				frames++;
			}else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
		exit();
	}

	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) Mouse.setGrabbed(true);
		if (!Mouse.isGrabbed()) return;
		
		cam.input();
		game.update();
	}

	public void render() {
		if (Display.wasResized()) {
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}
		DisplayManager.clearBuffers();
		cam.getPerspectiveProjection();
		cam.update();
		
		Renderer.addFog(0.1f, new Vector3f(0.5f, 0, 0.5f));
		
		Shader.MAIN.bind();
		
		game.render();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
