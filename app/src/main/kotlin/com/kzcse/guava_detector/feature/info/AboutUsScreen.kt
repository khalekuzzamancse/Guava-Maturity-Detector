@file:Suppress("SpellCheckingInspection")

package com.kzcse.guava_detector.feature.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.guava_detector.R
import com.kzcse.guava_detector.core.ui.SpacerVertical
import com.kzcse.guava_detector.feature._core.presentation.CopyrightNotice
import com.kzcse.guava_detector.feature._core.presentation.ImageView
import com.kzcse.guava_detector.feature._core.presentation.ScreenStrategy
import com.kzcse.guava_detector.feature._core.presentation.ThanksToICT
import com.kzcse.guava_detector.feature._core.presentation.TextHeading1
import com.kzcse.guava_detector.feature._core.presentation.TextHeading3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsPage(
    bottomBar: @Composable () -> Unit,fab: @Composable () -> Unit,
    navRail: @Composable () -> Unit
) {
    ScreenStrategy(
        bottomBar = bottomBar,
        navRail=navRail,
        fab = fab,
        title = {
            TextHeading1(text="About Us")
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
            SectionHeading()
            Spacer(modifier = Modifier.height(8.dp))

            // Supervisor Section
            SupervisorSection()
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider()

            Spacer(modifier = Modifier.height(24.dp))

            AIDeveloperSection()
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))
            DeveloperSection1()
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))
            DataCollectionAndPreparation()
            SpacerVertical(24)
            ThanksToICT()
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            CopyrightNotice()
            SpacerVertical(16)
        }
    }
}

@Composable
private fun SectionHeading() {
    TextHeading3(text = "Supervised by")
}


@Composable
private fun SupervisorSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        ImageView(
            res = R.drawable.super_vicer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Dr. Syed Md. Galib",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Professor",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        DeptAndUniversity()
    }
}

@Composable
private fun AIDeveloperSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        TextHeading3(text = "ML Developer")
        Spacer(modifier = Modifier.height(8.dp))
        ImageView(
            res = R.drawable.ai_developer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Moradul Siddque",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Research Assistant",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )

    }
}
@Composable
private fun DeveloperSection1() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        TextHeading3(text = "App Development")

        Spacer(modifier = Modifier.height(8.dp))

        ImageView(
            res = R.drawable.developer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
             //   .background(Color.White)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Yeasir Arefin Tusher",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Research Assistant",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        SpacerVertical(16)
        ImageView(
            res = R.drawable.khalekuzzaman,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                //   .background(Color.White)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Md Khalekuzzman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
//        Text(
//            text = "Student",
//            fontSize = 16.sp,
//            color = MaterialTheme.colorScheme.secondary
//        )

    }
}


@Composable
fun ColumnScope.DeptAndUniversity(modifier: Modifier = Modifier) {
        Text(
            modifier=modifier.align(Alignment.Start) ,
            text = "Department of  CSE at Jashore University of Science and Technology (JUST)",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )


}



@Composable
private fun DataCollectionAndPreparation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        TextHeading3(text = "Data Collection and Preparation")
        SpacerVertical(8)
        ImageView(
            res = R.drawable.tanvir_ah,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                //   .background(Color.White)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        SpacerVertical(8)
        Text(
            text = "Tanvir Ahamed",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerVertical(16)

        ImageView(
            res = R.drawable.suvo,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        SpacerVertical(8)
        Text(
            text = "S. M. Shadman Akber Shuvo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerVertical(16)
        ImageView(
            res = R.drawable.nafi,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                //   .background(Color.White)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        SpacerVertical(8)
        Text(
            text = "Md. Nafiur Rahman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

    }
}



