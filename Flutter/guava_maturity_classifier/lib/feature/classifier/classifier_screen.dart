import 'dart:math';
import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:guava_maturity_classifier/core/ui/core_ui.dart';
import 'package:kz_platform/image_classifier.dart';

class ClassificationScreen extends StatefulWidget {
  final Uint8List imageBytes;

  const ClassificationScreen({Key? key, required this.imageBytes})
    : super(key: key);

  @override
  _ClassificationScreenState createState() => _ClassificationScreenState();
}

class _ClassificationScreenState extends State<ClassificationScreen> {
  late Uint8List resizedImage = Uint8List(
    0,
  ); // Initialize with empty byte array
  bool isLoading = false;
  String? result;
  ImageClassifier classifier = ImageClassifier();

  @override
  void initState() {
    super.initState();
    _classifyImage();
  }

  Future<void> _classifyImage() async {
    setState(() {
      isLoading = true;
    });

    try {
      resizedImage = widget.imageBytes;
      // Simulate image resizing and classification process
      await Future.delayed(Duration(seconds: 2));

      // Call the classifier to get the output array
      final outputArray = await classifier.classifyImage(widget.imageBytes, 4);

      // Log the output array (for debugging purposes)
      print("Output array: $outputArray");

      // Find the index of the maximum value in the output array
      final indexWithMaxValue = outputArray.indexOf(
        outputArray.reduce((a, b) => a > b ? a : b),
      );

      // Class labels corresponding to the indexes
      final classLabels = ["Immature", "Mature", "Over Ripe", "Ripe"];

      // Check if indexWithMaxValue is valid
      if (indexWithMaxValue != -1) {
        setState(() {
          result = classLabels[indexWithMaxValue];
        });
      } else {
        setState(() {
          result = "Prediction Failed";
        });
      }
    } catch (e) {
      setState(() {
        result = "Error: ${e.toString()}";
      });
      print("Error classifying image: $e");
    } finally {
      setState(() {
        isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Classification Result")),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              if (resizedImage.isNotEmpty) ...[
                ImageWithProgress(
                  imageBytes: resizedImage,
                  isLoading: isLoading,
                  isSuccess: result != null,
                ),
                const SizedBox(height: 24),
              ],
              DisplayResult(result: result, isLoading: isLoading),
            ],
          ),
        ),
      ),
    );
  }
}

class ImageWithProgress extends StatelessWidget {
  final Uint8List imageBytes;
  final bool isLoading;
  final bool isSuccess;

  const ImageWithProgress({
    Key? key,
    required this.imageBytes,
    required this.isLoading,
    required this.isSuccess,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final borderColor = isLoading
        ? Colors.grey
        : isSuccess
        ? Colors.green
        : Colors.red;

    return Container(
      width: 200,
      height: 200,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(12),
        color: Colors.white,
        border: Border.all(color: borderColor, width: 1),
      ),
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(11),
            child: Image.memory(
              imageBytes,
              fit: BoxFit.cover,
              width: double.infinity,
              height: double.infinity,
            ),
          ),
          if (isLoading)
            Positioned.fill(child: RandomWaveDotGridLoader()),
        ],
      ),
    );


  }
}

class DisplayResult extends StatelessWidget {
  final String? result;
  final bool isLoading;

  const DisplayResult({Key? key, required this.result, required this.isLoading})
    : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (isLoading) {
      return Text(
        "Classifying the image...",
        style: TextStyle(fontSize: 18, color: Colors.blue),
        textAlign: TextAlign.center,
      );
    }

    if (result == null) {
      return Text(
        "Prediction Failed",
        style: TextStyle(
          fontSize: 20,
          color: Colors.red,
          fontWeight: FontWeight.bold,
        ),
        textAlign: TextAlign.center,
      );
    }

    return Text(
      result!,
      style: TextStyle(
        fontSize: 22,
        fontWeight: FontWeight.bold,
        color: Colors.green,
      ),
      textAlign: TextAlign.center,
    );
  }
}
