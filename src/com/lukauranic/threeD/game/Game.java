package com.lukauranic.threeD.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.lukauranic.threeD.ThreeD;
import com.lukauranic.threeD.graphics.Renderer;
import com.lukauranic.threeD.input.Keyboard;

public class Game {	

	Cube cube;	
	int counter = 0, speed = 10;
	double rotSpeed = 0.04, rotX = 0.0, rotY = 0.0, rotZ = 0.0;
	
	public Game() {
		init();
	}
	
	public void init() {
		cube = new Cube(ThreeD.WIDTH / 2, ThreeD.HEIGHT / 2, 512,  100);
	}
	
	public void update() {	
		if(Keyboard.key(KeyEvent.VK_RIGHT)) {
			rotZ = rotSpeed;
		}
		if(Keyboard.key(KeyEvent.VK_LEFT)) {
			rotZ = -rotSpeed;			
		}
		if(Keyboard.key(KeyEvent.VK_RIGHT) && Keyboard.key(KeyEvent.VK_LEFT) ||
		!Keyboard.key(KeyEvent.VK_RIGHT) && !Keyboard.key(KeyEvent.VK_LEFT)) {
			rotZ = 0.0;
		}			
		if(Keyboard.key(KeyEvent.VK_J)) {
			rotY = rotSpeed;
		}
		if(Keyboard.key(KeyEvent.VK_L)) {
			rotY = -rotSpeed;			
		}
		if(Keyboard.key(KeyEvent.VK_J) && Keyboard.key(KeyEvent.VK_L) ||
		!Keyboard.key(KeyEvent.VK_J) && !Keyboard.key(KeyEvent.VK_L)) {
			rotY = 0.0;
		}
		if(Keyboard.key(KeyEvent.VK_I)) {
			rotX = rotSpeed;
		}
		if(Keyboard.key(KeyEvent.VK_K)) {
			rotX = -rotSpeed;			
		}
		if(Keyboard.key(KeyEvent.VK_I) && Keyboard.key(KeyEvent.VK_K) ||
		!Keyboard.key(KeyEvent.VK_I) && !Keyboard.key(KeyEvent.VK_K)) {
			rotX = 0.0;
		}

		cube.moveToOrigin();
		Point3d p;
		for(int i = 0; i < cube.points.length; i++) {			
			p = Renderer.rotateX(cube.points[i], rotX);
			cube.points[i].x = p.x;
			cube.points[i].y = p.y;
			cube.points[i].z = p.z;
			p = Renderer.rotateY(cube.points[i], rotY);
			cube.points[i].x = p.x;
			cube.points[i].y = p.y;
			cube.points[i].z = p.z;
			p = Renderer.rotateZ(cube.points[i], rotZ);
			cube.points[i].x = p.x;
			cube.points[i].y = p.y;
			cube.points[i].z = p.z;	
		}
		cube.moveToPosition();
		cube.rx += rotX;
		cube.ry += rotY;
		cube.rz += rotZ;


		cube.moveToOrigin();
		if(Keyboard.key(KeyEvent.VK_A)) {
			cube.x -= speed;
		}
		if(Keyboard.key(KeyEvent.VK_D)) {
			cube.x += speed;			
		}
		if(Keyboard.key(KeyEvent.VK_W)) {
			cube.y -= speed;
		}
		if(Keyboard.key(KeyEvent.VK_S)) {
			cube.y += speed;		
		}
		if(Keyboard.key(KeyEvent.VK_UP)) {
			cube.z += speed;
		}
		if(Keyboard.key(KeyEvent.VK_DOWN)) {
			cube.z -= speed;		
		}
		cube.moveToPosition();
		
		
		counter++;
	}
	
	public void render() {
		Renderer.renderBackground();
		
		for(int i = 0; i < cube.connections.length; i++) {
			Point3d p = cube.points[cube.connections[i][0]];
			Point3d p2 = cube.points[cube.connections[i][1]];
//			to render it only when they are infront of camera (should have been fixed if only one is in front to draw it as well)
			if(p.z <= 0 || p2.z <= 0) continue;			
//			renders a line between two points
			Renderer.renderLine(p, p2);	
		}
		List<Point3d> rect = new ArrayList<Point3d>();
		rect.add(Renderer.perspective(cube.points[0]));
		rect.add(Renderer.perspective(cube.points[1]));
		rect.add(Renderer.perspective(cube.points[2]));
		rect.add(Renderer.perspective(cube.points[3]));
		Renderer.renderRectangle(rect, 0xffffff00);
		
//		Renderer.renderSprite(Sprite.logo, ThreeD.WIDTH / 2, ThreeD.HEIGHT / 2, 500, cube.rx, cube.ry, cube.rz);
		
		for(int i = 0; i < Renderer.pixels.length; i++) {
			ThreeD.pixels[i] = Renderer.pixels[i];
		}
	}
	
	public void postRender(Graphics2D g) {
//		g.setColor(Color.WHITE);
//		g.setStroke(new BasicStroke());
//		for(int i = 0; i < cube.connections.length; i++) {
//			Point3d p = cube.points[cube.connections[i][0]];
//			Point3d p2 = cube.points[cube.connections[i][1]];
//			
//			if(p.z <= 0 || p2.z <= 0) continue;
//			Point3d pp = perspective(p);
//			Point3d pp2 = perspective(p2);
//			g.drawLine((int) pp.x, (int) pp.y, (int) pp2.x, (int) pp2.y);
//		}	
	}
}
