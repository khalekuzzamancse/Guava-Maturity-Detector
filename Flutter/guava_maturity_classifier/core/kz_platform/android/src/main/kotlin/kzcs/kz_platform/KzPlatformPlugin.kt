package kzcs.kz_platform
import android.content.Context
import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kzcs.kz_platform.ml.ImageAdapter
import kzcs.kz_platform.ml.ImageClassifier
import kzcs.kz_platform.ml.ImageClassifierImpl
import  java.lang.Math

class KzPlatformPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "kz_platform")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(call: MethodCall, result: Result) {

        if (call.method == "classifyImage") {
            val imageBase64 = call.argument<ByteArray?>("image")
            val numClasses = call.argument<Int?>("numClasses")?:0
           val bitmap=ImageAdapter().byteArrayToBitmap(imageBase64!!)
         val output= ImageClassifierImpl(context,"model.tflite").classifyOrThrow(bitmap,numClasses!!)
            result.success(output)
        }
        else if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
