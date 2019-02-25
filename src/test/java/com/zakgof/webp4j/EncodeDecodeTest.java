package com.zakgof.webp4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class EncodeDecodeTest {
	
	@Test
	void testIntRGB() throws IOException {
	    test(BufferedImage.TYPE_INT_RGB);
	}
	
	@Test
	void testIntBGR() throws IOException {
	    test(BufferedImage.TYPE_INT_BGR);
	}
	
	@Test
	void test3BGR() throws IOException {
	    test(BufferedImage.TYPE_3BYTE_BGR);
	}
	
	@Test
	@Disabled
	void test4ABGR() throws IOException {
	    test(BufferedImage.TYPE_4BYTE_ABGR);
	}
	
	@Test
	@Disabled
	void test4ABGRPre() throws IOException {
	    test(BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}
	
	@Test
	@Disabled
	void testUShort555() throws IOException {
	    test(BufferedImage.TYPE_USHORT_555_RGB);
	}
	
	@Test
	@Disabled
	void testUShort565() throws IOException {
	    test(BufferedImage.TYPE_USHORT_565_RGB);
	}

	private void test(int type) throws IOException {
		BufferedImage original = createImage(type);
		byte[] webp = Webp4j.encode(original, 100);		
		BufferedImage restored = Webp4j.decode(webp);
		assertImagesSimilar(original, restored);
	}

	private BufferedImage createImage(int type) {
		return BITools.create(512,  512, type, (x, y, pix) -> {
			pix[0] = (int) (128.0 + 127.0 * Math.sin((x+y) * 0.01));
			pix[1] = (int) (128.0 + 127.0 * Math.cos(y * 0.031));
			pix[2] = (int) (128.0 + 127.0 * Math.cos(x * 0.013));
			return pix;
		});
	}

	private void assertImagesSimilar(BufferedImage original, BufferedImage restored) {
		for (int i=0; i<100; i++) {
			int x = (int) (Math.abs(Math.sin(i * 0.4)) * (original.getWidth()-1));
			int y = (int) (Math.abs(Math.cos(i * 0.5)) * (original.getHeight()-1));
			
			int color1 = original.getRGB(x, y);
			int color2 = restored.getRGB(x, y);
			
			int r1 = (color1 >> 16) & 0xFF;
			int r2 = (color2 >> 16) & 0xFF;
			int g1 = (color1 >> 8) & 0xFF;
			int g2 = (color2 >> 8) & 0xFF;
			int b1 = color1 & 0xFF;
			int b2 = color2 & 0xFF;
			
			double diff = Math.sqrt((r1-r2)*(r1-r2) + (g1-g2)*(g1-g2) + (b1-b2)*(b1-b2));
			assertTrue(diff < 5.0); // 5 per pixel
		}
		
	}

}
