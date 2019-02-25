package com.zakgof.webp4j;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class BITools {
	public static BufferedImage create(int width, int height, int type, IPixelVisitor visitor) {
		BufferedImage image = new BufferedImage(width, height, type);
		int[] pixel = new int[4];
		WritableRaster raster = image.getRaster();
		for (int x = 0; x < image.getWidth(); x++)
			for (int y = 0; y < image.getHeight(); y++) {
				int[] res = visitor.visit(x, y, pixel);
				raster.setPixel(x, y, res);
			}
		return image;
	}
	
	public static void scan(BufferedImage image, IPixelScanner visitor) {
		int[] pixel = new int[3];
		WritableRaster raster = image.getRaster();
		for (int x=0; x<image.getWidth();x++)
			for (int y=0; y<image.getHeight();y++) {
				raster.getPixel(x, y, pixel);
				visitor.visit(x, y, pixel);
			}
		
	}

	public interface IPixelVisitor {
		int[] visit(int x, int y, int[] pixel);
	}
	
	public interface IPixelScanner {
		void visit(int x, int y, int[] pixel);
	}
	

	public static BufferedImage rgb(BufferedImage image) {
		if (image.getType() == BufferedImage.TYPE_INT_RGB)
			return image;
		int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, image.getWidth(), image.getHeight(), rgb, 0, image.getWidth());
		return bi;
	}

}
