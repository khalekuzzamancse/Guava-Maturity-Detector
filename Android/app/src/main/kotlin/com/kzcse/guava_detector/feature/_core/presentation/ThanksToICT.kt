package com.kzcse.guava_detector.feature._core.presentation
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.guava_detector.R
import com.kzcse.guava_detector.core.ui.SpacerHorizontal
import com.kzcse.guava_detector.core.ui.SpacerVertical

@Composable
fun ThanksToICT(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(IntrinsicSize.Max)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){

            ImageView(
                res = R.drawable.ict_division,
                modifier = Modifier
                    .size(80.dp)
                   // .clip(CircleShape)
                    .border(
                        width = (0.3).dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            SpacerHorizontal(16)

            Text(
                text = "A Special thanks to ICT Division, Bangladesh for their support.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Justify
            )

        }


    }
}