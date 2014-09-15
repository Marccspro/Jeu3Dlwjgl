package fr.veridian.main.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import fr.veridian.main.math.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Camera {
	public static float mouseSpeed = 0.5f;
	public static float moveSpeed = 0.05f;
	
	float fov;
	float zNear;
	float zFar;
	
	Vector3f position;
	Vector3f rotation = new Vector3f();
	
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

	public void update() {
		glPushAttrib(GL_TRANSFORM_BIT);
			glRotatef(rotation.getX(), 1, 0, 0);
			glRotatef(rotation.getY(), 0, 1, 0);
			glRotatef(rotation.getZ(), 0, 0, 1);
			glTranslatef(position.getX(), position.getY(), position.getZ());
		glPopMatrix();
	}
	
	public void input() {
		rotation.addX(-Mouse.getDY() * mouseSpeed);
		rotation.addY(Mouse.getDX() * mouseSpeed);
		
		if (rotation.getX() > 90)rotation.setX(90);
		if (rotation.getX() < -90)rotation.setX(-90);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			position.add(getForward().mul(moveSpeed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.add(getBack().mul(moveSpeed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			position.add(getLeft().mul(moveSpeed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.add(getRight().mul(moveSpeed));
		}
		
		//TODO: temp code
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.add(new Vector3f(0, -1, 0).mul(moveSpeed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.add(new Vector3f(0, 1, 0).mul(moveSpeed));
		}
	}
	
	public Vector3f getForward() {
		Vector3f rot = new Vector3f(rotation);
		
		Vector3f r = new Vector3f();
		r.setX((float) Math.cos(Math.toRadians(rot.getY() + 90)));
		r.setZ((float) Math.sin(Math.toRadians(rot.getY() + 90)));
		r.normalize();
		
		return new Vector3f(r);
	}
	
	public Vector3f getBack() {
		return new Vector3f(getForward().mul(-1));
	}
	
	public Vector3f getLeft() {
		Vector3f rot = new Vector3f(rotation);
		
		Vector3f r = new Vector3f();
		r.setX((float) Math.cos(Math.toRadians(rot.getY())));
		r.setZ((float) Math.sin(Math.toRadians(rot.getY())));
		r.normalize();
		
		return new Vector3f(r);
	}
	
	public Vector3f getRight() {
		return new Vector3f(getLeft().mul(-1));
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
}
