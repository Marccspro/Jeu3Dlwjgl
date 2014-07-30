package fr.veridian.main;

import org.lwjgl.util.vector.Vector3f;

import fr.veridian.main.render.Camera;
import fr.veridian.main.render.DisplayManager;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	
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
		while(running) {
			if (DisplayManager.isClosed()) stop();
			DisplayManager.update();
			render();
		}
		exit();
	}
	
	public void render() {
		cam.getPerspectiveProjection();
		
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
