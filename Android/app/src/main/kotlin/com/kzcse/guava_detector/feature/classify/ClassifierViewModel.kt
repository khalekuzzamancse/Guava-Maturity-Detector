package com.kzcse.guava_detector.feature.classify

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.kzcse.guava_detector.core.ml.ClassifierFactory
import com.kzcse.guava_detector.core.platfrom.BitmapUtils
import com.kzcse.guava_detector.feature._core.logic.Constants
import com.kzcse.guava_detector.feature._core.logic.CustomException
import com.kzcse.guava_detector.feature._core.logic.Logger
import com.kzcse.guava_detector.feature._core.presentation.GlobalMessenger
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @param context safe for memory, not store the context, instead use as one time for initialization
 */
class ClassifierViewModel(context: Context) : ViewModel() {
    private val tag="ClassifierViewModel"
    private val classifier = ClassifierFactory.createImageClassifier(context)
    private val _result = MutableStateFlow<String?>(null)
    val result = _result.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    private val _resizedImage = MutableStateFlow<Bitmap?>(null)
    val resizedImage = _resizedImage.asStateFlow()
    val isLoading = _isLoading.asStateFlow()
    private fun setLoading(value: Boolean) = _isLoading.update { value }
    private fun setResult(result: String?) {
        _result.update { result }
    }
    fun resize(original: Bitmap): Bitmap {
        val resized = BitmapUtils.scaleAndCenter(
            original,
            Constants.EXPECTED_IMAGE_WIDTH,
            Constants.EXPECTED_IMAGE_HEIGHT
        )
        _resizedImage.value = resized
        return resized
    }

   suspend fun classify(bitmap: Bitmap) {
        try {
            setLoading(true)
            delay(2_000)// Simulate loading,
            val image=resize(bitmap)
            val outputArray = classifier.classifyOrThrow(image = image, numClasses = 4)
            Logger.on(tag,"output-array:$outputArray")
            val indexWithMaxValue = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
            val classLabels = arrayOf("Immature", "Mature", "Over Ripe", "Ripe")
//            // Convert each value to percentage string
//            val resultString = classLabels.mapIndexed { index, label ->
//                val percentage = outputArray[index] * 100   // assuming outputArray has float probabilities 0..1
//                "${label}: %.2f%%".format(percentage)
//            }.joinToString(separator = "\n")
//            setResult(resultString)

            if (indexWithMaxValue != -1) {

                setResult(classLabels[indexWithMaxValue])
            } else {
                setResult(null)
            }
        } catch (e: Throwable) {
            if (e is CustomException)
                GlobalMessenger.updateMessage(e)
        } finally {
            setLoading(false)
        }
    }


}