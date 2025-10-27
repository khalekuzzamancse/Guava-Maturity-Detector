package com.kzcse.guava_detector.core.ml

import android.content.Context

object ClassifierFactory {
    fun createImageClassifier(context: Context): ImageClassifier{
         return ImageClassifierImpl(context,modelPath = "model.tflite")
    }
}

