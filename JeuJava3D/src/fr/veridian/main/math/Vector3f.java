package fr.veridian.main.math;

public class Vector3f {
	private float x, y, z;
	
	public Vector3f() {
		this(0, 0, 0);
	}
	
	public Vector3f(Vector3f v) {
		this(v.x, v.y, v.z);
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f normalize() {
		x /= length();
		y /= length();
		z /= length();
		
		return this;
	}
	
	public Vector3f add(Vector3f vec) {
		x += vec.getX();
		y += vec.getY();
		z += vec.getZ();
		
		return this;
	}
	
	public Vector3f sub(Vector3f vec) {
		x -= vec.getX();
		y -= vec.getY();
		z -= vec.getZ();
		
		return this;
	}
	
	public Vector3f mul(Vector3f vec) {
		x *= vec.getX();
		y *= vec.getY();
		z *= vec.getZ();
		
		return this;
	}
	
	public Vector3f div(Vector3f vec) {
		x /= vec.getX();
		y /= vec.getY();
		z /= vec.getZ();
		
		return this;
	}
	
	
	public Vector3f add(float v) {
		x += v;
		y += v;
		z += v;
		
		return this;
	}
	
	public Vector3f sub(float v) {
		x -= v;
		y -= v;
		z -= v;
		
		return this;
	}
	
	public Vector3f mul(float v) {
		x *= v;
		y *= v;
		z *= v;
		
		return this;
	}
	
	public Vector3f div(float v) {
		x /= v;
		y /= v;
		z /= v;
		
		return this;
	}
	
	// ---- X
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public Vector3f addX(float v) {
		x += v;
		return this;
	}
	public Vector3f subX(float v) {
		x -= v;
		return this;
	}
	
	
	// ----- Y
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Vector3f addY(float v) {
		y += v;
		return this;
	}
	public Vector3f subY(float v) {
		y -= v;
		return this;
	}
	
	
	// ----- Z
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public Vector3f addZ(float v) {
		z += v;
		return this;
	}
	public Vector3f subZ(float v) {
		z -= v;
		return this;
	}
}
