package com.kzcse.guava_detector.feature.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kzcse.guava_detector.core.ui.SpacerVertical
import com.kzcse.guava_detector.feature._core.presentation.ScreenStrategy
import com.kzcse.guava_detector.feature._core.presentation.TextDescription
import com.kzcse.guava_detector.feature._core.presentation.TextPoint
import com.kzcse.guava_detector.feature._core.presentation.TextHeading2
import com.kzcse.guava_detector.feature._core.presentation.TextHeading1
import com.kzcse.guava_detector.feature._core.presentation.ThanksToICT
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onAppInfoRequest: () -> Unit,
    bottomBar: @Composable () -> Unit,
    navRail: @Composable () -> Unit,
    fab: @Composable () -> Unit
) {


    ScreenStrategy(
        bottomBar = bottomBar,
        fab = fab,
        navRail = navRail,
        title = {
            TextHeading1(text = "Guava Maturity Detector")
        }
    ) {
        Column(
            modifier = it
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            SpacerVertical(16)
            TextDescription(text = "Detect Guava with ease! Our AI model identifies four different Guava classes accurately.")
            SpacerVertical(24)
            FeatureList()
            SpacerVertical(16)
            Spacer(Modifier.weight(1f))
            HorizontalDivider()
            SpacerVertical(24)
            ThanksToICT(Modifier.wrapContentWidth().align(Alignment.Start))
            SpacerVertical(64)

        }
    }


}





@Composable
fun FeatureList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        FeaturesSection(
            title = "Supported Features", features = listOf(
                "Detects 4 Guava classes: Immature, Mature, Ripe, Over Ripe",
                "Capture images directly from the camera",
                "Select images from your device's gallery",
                "Use images from the app’s integrated gallery"
            )
        )
    }
}

@Composable
private fun FeaturesSection(title: String, features: List<String>) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextHeading2(
            text = title,
            modifier =  Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        features.forEach { feature ->
            TextPoint(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                text = feature
            )
        }
    }
}



