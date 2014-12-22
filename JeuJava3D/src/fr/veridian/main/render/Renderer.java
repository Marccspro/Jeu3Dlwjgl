package fr.veridian.main.render;

import static org.lwjgl.opengl.GL11.*;
import fr.veridian.main.math.Vector3f;

public class Renderer {
	public static void setFloorData(float x, float z, Vector3f color) {
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glVertex3f(x + 1, 0, z);
		glVertex3f(x, 0, z);
		glVertex3f(x, 0, z + 1);
		glVertex3f(x + 1, 0, z + 1);
	}
	
	public static void setCeilingData(float x, float z, Vector3f color) {
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glVertex3f(x, 1, z);
		glVertex3f(x + 1, 1, z);
		glVertex3f(x + 1, 1, z + 1);
		glVertex3f(x, 1, z + 1);
	}
	
	public static void setWallData(float x0, float z0, float x1, float z1, Vector3f color) {
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glVertex3f(x0, 0, z0);
		glVertex3f(x1, 0, z1);
		glVertex3f(x1, 1, z1);
		glVertex3f(x0, 1, z0);
	}
}
