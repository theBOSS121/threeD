package com.lukauranic.threeD.game;

public class Cube {

	public double x, y, z, rx, ry, rz;
	public int size;
	
	public Point3d[] points = {
			new Point3d(-1, -1, -1),
			new Point3d(1, -1, -1),
			new Point3d(1, 1, -1),
			new Point3d(-1, 1, -1),
			new Point3d(-1, -1, 1),
			new Point3d(1, -1, 1),
			new Point3d(1, 1, 1),
			new Point3d(-1, 1, 1),			
	};
	
	public int[][] connections = {
		{0, 1},
		{1, 2},
		{2, 3},
		{3, 0},

		{4, 5},
		{5, 6},
		{6, 7},
		{7, 4},

		{0, 4},
		{1, 5},
		{2, 6},
		{3, 7},
	};
	
	public Cube(double x, double y, double z, int size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		
		for(int i = 0; i < points.length; i++) {
			points[i].x *= size;
			points[i].y *= size;
			points[i].z *= size;

			points[i].x += x;
			points[i].y += y;
			points[i].z += z;
		}
	}
	
	public void moveToOrigin() {
		for(int i = 0; i < points.length; i++) {
			points[i].x -= this.x;
			points[i].y -= this.y;
			points[i].z -= this.z;
		}
	}
	
	public void moveToPosition() {
		for(int i = 0; i < points.length; i++) {
			points[i].x += this.x;
			points[i].y += this.y;
			points[i].z += this.z;
		}
	}
	
}
