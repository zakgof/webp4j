package com.zakgof.webp4j;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class Webp4j {
	
	/**
	 * Encode a BufferedImage into wepb format
	 * @param image source image
	 * @param quality quality factor, 0..100
	 * @return output array
	 * @throws IOException
	 */
	public static byte[] encode(BufferedImage image, float quality) throws IOException {
		int[] size = new int[1];
		if (image.getType() == BufferedImage.TYPE_3BYTE_BGR) {
			byte[] bytes = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
			byte[] webp = WebpNative.wrap_WebPEncodeBGR(bytes, 0, 0, size, image.getWidth(), image.getHeight(), image.getWidth() * 3, quality);
			return webp;
		} else {
			byte[] raw = new byte[image.getWidth() * image.getHeight() * 3]; 
			int stride = image.getWidth() * 3;
			BITools.scan(image, (x, y, pixel) -> {
				raw[stride * y + x*3] = (byte)(pixel[2] & 0xFF);
				raw[stride * y + x*3 + 1] = (byte)(pixel[1] & 0xFF);
				raw[stride * y + x*3 + 2] = (byte)(pixel[0] & 0xFF);
			});
			byte[] webp = WebpNative.wrap_WebPEncodeBGR(raw, 0, 0, size, image.getWidth(), image.getHeight(), image.getWidth() * 3, quality);
			return webp;
		}
	}
	
	public static BufferedImage decode(byte[] webp) {
		int[] width = new int[1];
		int[] height = new int[1];
		byte[] raw = WebpNative.WebPDecodeBGR(webp, webp.length, width, height);
		int stride = width[0] * 3;
		BufferedImage bi = BITools.create(width[0], height[0], BufferedImage.TYPE_3BYTE_BGR, (x, y, pixel) -> {
			pixel[2] = (int)raw[stride * y + x*3];
			pixel[1] = (int)raw[stride * y + x*3 + 1];
			pixel[0] = (int)raw[stride * y + x*3 + 2];
			return pixel;
		});
		return bi;
	}

}
