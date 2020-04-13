package com.lukauranic.threeD.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprite {
	
	public int width, height;
	public int[] pixels;

	public static Sprite logo = new Sprite("/logo.png");

	
	public Sprite(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = image.getRGB(0, 0, width, height, null, 0, width);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
