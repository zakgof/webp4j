package com.zakgof.webp4j;

import java.io.IOException;

import org.scijava.nativelib.NativeLoader;

public class WebpNative {
	
	static{
		try {
			NativeLoader.loadLibrary("webp");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	  public final static native int WebPGetDecoderVersion();
	  public final static native int WebPGetInfo(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native byte[] WebPDecodeRGB(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native byte[] WebPDecodeRGBA(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native byte[] WebPDecodeARGB(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native byte[] WebPDecodeBGR(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native byte[] WebPDecodeBGRA(byte[] jarg1, long jarg2, int[] jarg3, int[] jarg4);
	  public final static native int WebPGetEncoderVersion();
	  public final static native byte[] wrap_WebPEncodeRGB(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7, float jarg8);
	  public final static native byte[] wrap_WebPEncodeBGR(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7, float jarg8);
	  public final static native byte[] wrap_WebPEncodeRGBA(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7, float jarg8);
	  public final static native byte[] wrap_WebPEncodeBGRA(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7, float jarg8);
	  public final static native byte[] wrap_WebPEncodeLosslessRGB(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7);
	  public final static native byte[] wrap_WebPEncodeLosslessBGR(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7);
	  public final static native byte[] wrap_WebPEncodeLosslessRGBA(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7);
	  public final static native byte[] wrap_WebPEncodeLosslessBGRA(byte[] jarg1, int jarg2, int jarg3, int[] jarg4, int jarg5, int jarg6, int jarg7);

}
