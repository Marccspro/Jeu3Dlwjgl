package fr.veridian.main.render;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Camera {
	float fov;
	float zNear;
	float zFar;
	
	Vector3f position;
	
	public Camera(Vector3f position) {
		this.position = position;
	}
	
	public Camera setPerspectiveProjection(float fov, float zNear, float zFar) {
		this.fov = fov;
		this.zNear = zNear;
		this.zFar = zFar;
		
		return this;
	}
	
	public void getPerspectiveProjection() {
		glEnable(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float)Display.getWidth() / (float)Display.getHeight(), zNear, zFar);
		glEnable(GL_MODELVIEW);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
}
