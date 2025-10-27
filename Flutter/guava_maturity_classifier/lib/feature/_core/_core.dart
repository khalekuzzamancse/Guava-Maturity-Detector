import 'dart:ui';

class Constants {
  static const int expectedImageWidth = 224;
  static const int expectedImageHeight = 224;

  static bool isImageSizeMatched(Image image) {
    return image.width == expectedImageWidth &&
        image.height == expectedImageHeight;
  }
}

