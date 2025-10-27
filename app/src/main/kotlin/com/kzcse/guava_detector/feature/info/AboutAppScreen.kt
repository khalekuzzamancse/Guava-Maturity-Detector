package com.kzcse.guava_detector.feature.info

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.guava_detector.feature._core.presentation.ScreenStrategy
import  com.kzcse.guava_detector.R
import com.kzcse.guava_detector.core.ui.SpacerVertical
import com.kzcse.guava_detector.feature._core.presentation.CopyrightNotice
import com.kzcse.guava_detector.feature._core.presentation.TextDescription
import com.kzcse.guava_detector.feature._core.presentation.TextHeading1
import com.kzcse.guava_detector.feature._core.presentation.TextHeading3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    bottomBar: @Composable () -> Unit, fab: @Composable () -> Unit,
    navRail: @Composable () -> Unit
) {
    ScreenStrategy(
        bottomBar = bottomBar,
        fab = fab,
        navRail=navRail,
        title = {
            TextHeading1(text="About App")
        }
    ) {
        Column(
            modifier = it
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
          SpacerVertical(16)
            TextDescription(
                text = "This application is designed to detect various types of Guava using advanced AI models. The supported classes for detection are:",
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.height(8.dp))
            SupportedClassesSection()

            Spacer(modifier = Modifier.height(12.dp))

            TextHeading3(text = "Data Set")
            GoogleDriveLink()
            TextHeading3(text="Technical Details")

            Spacer(modifier = Modifier.height(8.dp))

            TextDescription(
                text = "Our model was trained using TensorFlow and PyTorch frameworks and later converted to TensorFlow Lite for seamless mobile integration. " +
                        "TensorFlow Lite enables efficient inference on mobile devices, ensuring faster detection without compromising accuracy.",
                modifier = Modifier.align(Alignment.Start),
            )


            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            CopyrightNotice()
            SpacerVertical(16)
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SupportedClassesSection() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextHeading3(text="Supported Guava Classes")
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {

            ClassItem(
                imageRes = R.drawable.class_immature,
                className = "Immature"
            )

            ClassItem(
                imageRes = R.drawable.class_mature,
                className = "Mature"
            )

              ClassItem(
                imageRes = R.drawable.class_ripe,
                className = "Ripe"
            )

            ClassItem(
                imageRes = R.drawable.class_overripe,
                className = "Over Ripe"
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}
@Composable
fun GoogleDriveLink() {
    val url = "https://drive.google.com/drive/folders/1GPGf3qKlMLu1dn5T9EKhqLUfQfq-tX3L"

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
          //  .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Click here to go to thee data set",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { uriHandler.openUri(url) }
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                clipboardManager.setText(AnnotatedString(url))
                Toast.makeText(context, "Link copied!", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = "Copy Link",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
fun ClassItem(imageRes: Int, className: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = className,
            modifier = Modifier
                .size(100.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)) //Since regardless of theme the image back are always white(we ensured)

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = className,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
