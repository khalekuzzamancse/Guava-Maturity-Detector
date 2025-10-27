part of 'core_ui.dart';

class RandomWaveDotGridLoader extends StatefulWidget {
  final int rows;
  final int columns;
  final double dotSize;
  final double spacing;
  final int animationDuration; // in ms

  const RandomWaveDotGridLoader({
    Key? key,
    this.rows = 10,
    this.columns = 10,
    this.dotSize = 20.0,
    this.spacing = 10.0,
    this.animationDuration = 300,
  }) : super(key: key);

  @override
  _RandomWaveDotGridLoaderState createState() =>
      _RandomWaveDotGridLoaderState();
}

class _RandomWaveDotGridLoaderState extends State<RandomWaveDotGridLoader>
    with SingleTickerProviderStateMixin {
  late final AnimationController _controller;
  late final List<Color> _gridColors;
  final _random = Random();

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: widget.animationDuration),
    )..repeat(reverse: true);

    final colors = [
      Colors.blue,
      Colors.red,
      Colors.green,
    ];

    _gridColors = List.generate(
      widget.rows * widget.columns,
          (_) => colors[_random.nextInt(colors.length)],
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: List.generate(widget.rows, (row) {
        return Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: List.generate(widget.columns, (col) {
            final idx = row * widget.columns + col;
            final delay = (_random.nextInt(widget.animationDuration)).toDouble();
            return Padding(
              padding: EdgeInsets.all(widget.spacing / 2),
              child: AnimatedBuilder(
                animation: _controller,
                builder: (_, __) {
                  final alpha =
                      0.3 + 0.7 * (_controller.value + delay / widget.animationDuration) % 1;
                  return Container(
                    width: widget.dotSize,
                    height: widget.dotSize,
                    decoration: BoxDecoration(
                      color: _gridColors[idx].withOpacity(0.85 * alpha),
                      shape: BoxShape.circle,
                    ),
                  );
                },
              ),
            );
          }),
        );
      }),
    );
  }
}

// -------------------- DotGridLoader --------------------

class DotGridLoader extends StatefulWidget {
  final int rows;
  final int columns;
  final double dotSize;
  final double spacing;
  final int animationDuration; // ms

  const DotGridLoader({
    Key? key,
    this.rows = 10,
    this.columns = 10,
    this.dotSize = 20.0,
    this.spacing = 10.0,
    this.animationDuration = 200,
  }) : super(key: key);

  @override
  _DotGridLoaderState createState() => _DotGridLoaderState();
}

class _DotGridLoaderState extends State<DotGridLoader>
    with SingleTickerProviderStateMixin {
  late final AnimationController _controller;
  late final List<Color> _gridColors;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: widget.animationDuration),
    )..repeat();

    final colors = [Colors.blue, Colors.red, Colors.green];

    // Simple random color assignment
    _gridColors = List.generate(
      widget.rows * widget.columns,
          (_) => colors[Random().nextInt(colors.length)],
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  double _computeAlpha(int index) {
    final interval = (index * 40) / widget.animationDuration;
    final t = (_controller.value + interval) % 1;
    if (t < 0.5) {
      return 0.3 + t * 2 * (1 - 0.3); // 0.3 → 1
    } else {
      return 1 - (t - 0.5) * 2 * (1 - 0.3); // 1 → 0.3
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: List.generate(widget.rows, (row) {
        return Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: List.generate(widget.columns, (col) {
            final idx = row * widget.columns + col;
            return Padding(
              padding: EdgeInsets.all(widget.spacing / 2),
              child: AnimatedBuilder(
                animation: _controller,
                builder: (_, __) {
                  return Container(
                    width: widget.dotSize,
                    height: widget.dotSize,
                    decoration: BoxDecoration(
                      color: _gridColors[idx]
                          .withOpacity(0.85 * _computeAlpha(idx)),
                      shape: BoxShape.circle,
                    ),
                  );
                },
              ),
            );
          }),
        );
      }),
    );
  }
}
