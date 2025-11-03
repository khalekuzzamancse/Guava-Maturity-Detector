import 'dart:convert';
import 'package:flutter/services.dart';
import 'dart:typed_data';

class ImageClassifier {
  static const platform = MethodChannel('kz_platform'); // Ensure this matches Android side channel

  Future<Float32List> classifyImage(Uint8List imageBytes, int numClasses) async {
    try {
      // Sending image as base64 string and numClasses to Android
      final result = await platform.invokeMethod('classifyImage', {
        'image': imageBytes,  // Convert image to base64 string
        'numClasses': numClasses,           // The number of classes for the model
      });

      // Return the result as a Float32List (list of floats)
      return Float32List.fromList(result.cast<double>());
    } on PlatformException catch (e) {
      print("Error classifying image: ${e.message}");
      return Float32List(0); // Return empty list if error occurs
    }
  }
}
