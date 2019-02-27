# webp4j
Simple Java webp image format encoder and decoder

WebP images are claimed to be 25-34% smaller than comparable JPEG images at equivalent SSIM quality index.
https://developers.google.com/speed/webp/

**webp4j** uses JNI to link to native libwebp libraries (included into distribution jar).

Supported platforms:
- Linux 32/64 bit
- Windows 32/64 bit

### Webp4j is on Bintray
![Travis CI](https://travis-ci.org/zakgof/webp4j.svg?branch=release)
![https://bintray.com/zakgof/maven/webp4j](https://api.bintray.com/packages/zakgof/maven/webp4j/images/download.svg)

### Gradle
```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.zakgof:webp4j:0.0.2'
}
```
### Usage

```java
// Encode
BufferedImage bi = ...
float quality = 80f;
byte[] bytes = Webp4j.encode(bi, quality);

// Decode
BufferedImage restored = Webp4j.decode(bytes);
```
