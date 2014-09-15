package fr.veridian.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.veridian.main.math.Vector3f;
import fr.veridian.main.render.Camera;
import fr.veridian.main.render.DisplayManager;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static final float FRAME_CAP = 60;
	boolean running = false;

	Camera cam;

	public Main() {
		DisplayManager.create(720, 480, "Jeu 3D lwjgl");
		cam = new Camera(new Vector3f(0, 0, 0));
		cam.setPerspectiveProjection(70.0f, 0.1f, 100.0f);
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
			boolean rendered = false;

			if (System.nanoTime() - lastTickTime > tickTime) {
				lastTickTime += tickTime;
				update();
				ticks++;
			} else if (System.nanoTime() - lastRenderTime > renderTime) {
				lastRenderTime += renderTime;
				render();
				DisplayManager.update();
				frames++;
				rendered = true;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}

//			if (rendered && FRAME_CAP >= 60) {
//				try {
//					Thread.sleep((int) (1000.0 / FRAME_CAP));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		exit();
	}

	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
		if (Mouse.isButtonDown(0)) Mouse.setGrabbed(true);
		if (!Mouse.isGrabbed()) return;
		cam.input();
	}

	public void render() {
		DisplayManager.clearBuffers();
		cam.getPerspectiveProjection();
		cam.update();

		glBegin(GL_QUADS);
		glColor3f(0.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f, -0.5f, -1.0f);

		glColor3f(1.0f, 0.0f, 1.0f);
		glVertex3f(1.0f, -0.5f, -1.0f);

		glColor3f(1.0f, 1.0f, 0.0f);
		glVertex3f(1.0f, -0.5f, -3.0f);

		glColor3f(1.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f, -0.5f, -3.0f);
		glEnd();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
