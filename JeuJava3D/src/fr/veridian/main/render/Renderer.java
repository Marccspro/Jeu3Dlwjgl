package fr.veridian.main.render;

import static org.lwjgl.opengl.GL11.*;
import fr.veridian.main.math.Vector3f;

public class Renderer {
	
	private static float offs = 0.0f;
	private static float envWidth = 4f;
	private static float envHeight = 4f;
	
	public static void setFloorData(float x, float z, Vector3f color, int texture) {
		float xo = texture % (int) envWidth;
		float yo = texture / (int) envHeight;
		
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glTexCoord2f((0f + xo) / envWidth + offs, (0f + yo) / envHeight + offs); glVertex3f(x + 1, 0, z);
		glTexCoord2f((1f + xo) / envWidth - offs, (0f + yo) / envHeight + offs); glVertex3f(x, 0, z);
		glTexCoord2f((1f + xo) / envWidth - offs, (1f + yo) / envHeight - offs); glVertex3f(x, 0, z + 1);
		glTexCoord2f((0f + xo) / envWidth + offs, (1f + yo) / envHeight - offs); glVertex3f(x + 1, 0, z + 1);
	}
	
	public static void setCeilingData(float x, float z, Vector3f color, int texture) {
		float xo = texture % (int) envWidth;
		float yo = texture / (int) envHeight;
		
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glTexCoord2f((0f + xo) / envWidth + offs, (0f + yo) / envHeight + offs); glVertex3f(x, 1, z);
		glTexCoord2f((1f + xo) / envWidth - offs, (0f + yo) / envHeight + offs); glVertex3f(x + 1, 1, z);
		glTexCoord2f((1f + xo) / envWidth - offs, (1f + yo) / envHeight - offs); glVertex3f(x + 1, 1, z + 1);
		glTexCoord2f((0f + xo) / envWidth + offs, (1f + yo) / envHeight - offs); glVertex3f(x, 1, z + 1);
	}
	
	public static void setWallData(float x0, float z0, float x1, float z1, Vector3f color, int texture) {
		float xo = texture % (int) envWidth;
		float yo = texture / (int) envHeight;
		
		glColor3f(color.getX(), color.getY(), color.getZ());
		
		glTexCoord2f((0f + xo) / envWidth + offs, (0f + yo) / envHeight + offs); glVertex3f(x0, 0, z0);
		glTexCoord2f((1f + xo) / envWidth - offs, (0f + yo) / envHeight + offs); glVertex3f(x1, 0, z1);
		glTexCoord2f((1f + xo) / envWidth - offs, (1f + yo) / envHeight - offs); glVertex3f(x1, 1, z1);
		glTexCoord2f((0f + xo) / envWidth + offs, (1f + yo) / envHeight - offs); glVertex3f(x0, 1, z0);
	}
	
	public static void addFog(float density, Vector3f color) {
		Shader.MAIN.setUniform("fogColor", color);
		Shader.MAIN.setUniform("fogDensity", 0.1f);
	}
}
