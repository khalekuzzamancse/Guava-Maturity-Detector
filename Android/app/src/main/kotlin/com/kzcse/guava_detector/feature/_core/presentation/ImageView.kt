package com.kzcse.guava_detector.feature._core.presentation

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
 fun ImageView(modifier: Modifier = Modifier, res: Int) {
    Image(
        modifier = modifier,
        painter = painterResource(res),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}
