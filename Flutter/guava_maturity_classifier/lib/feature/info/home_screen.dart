import 'package:flutter/material.dart';
import 'package:guava_maturity_classifier/core/ui/core_ui.dart';
import 'package:guava_maturity_classifier/feature/_core/_misc.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: TextHeading1(text: "Guava Maturity Detector"),
      ),
      body: PaddingScreenHorizontal(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SpacerVertical(16),
            TextDescription(
              text:
                  "Detect Guava with ease! Our AI model identifies four different Guava classes accurately.",
            ),
            const SpacerVertical(16),
            const SpacerVertical(24),
            const FeatureList(),
            const SpacerVertical(16),
            const Spacer(),
            const Divider(),
            const SpacerVertical(16),
            const Align(
              alignment: Alignment.centerLeft,
              child: Text("Thanks to ICT", style: TextStyle(fontSize: 14)),
            ),
            const SpacerVertical(64),
          ],
        ),
      ),
    );
  }
}

class FeatureList extends StatelessWidget {
  const FeatureList({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: const [
        FeaturesSection(
          title: "Supported Features",
          features: [
            "Detects 4 Guava classes: Immature, Mature, Ripe, Over Ripe",
            "Capture images directly from the camera",
            "Select images from your device's gallery",
            "Use images from the app’s integrated gallery",
          ],
        ),
      ],
    );
  }
}

class FeaturesSection extends StatelessWidget {
  final String title;
  final List<String> features;

  const FeaturesSection({
    super.key,
    required this.title,
    required this.features,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        TextHeading2(text: title),
        SpacerVertical(16),
        ...features.map(
          (feature) => Padding(
            padding: const EdgeInsets.symmetric(vertical: 4),
            child: TextPoint(text: feature),
          ),
        ),
      ],
    );
  }
}
