package com.sinw.composestudy.unit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinw.composestudy.ui.theme.ComposeStudyTheme
import com.sinw.composestudy.R

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStudyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var curStep by remember {
        mutableStateOf(1)
    }

    when(curStep) {
        1 -> {
            ChangingArt(
                artImage = R.drawable.mnlz,
                artTitle = R.string.image1_title,
                artArtist = R.string.image1_artist,
                artYear = R.string.image1_year,
                onPrevClick = { curStep = 3 },
                onNextClick = { curStep++ }
            )
        }
        2 -> {
            ChangingArt(
                artImage = R.drawable.girls,
                artTitle = R.string.image2_title,
                artArtist = R.string.image2_artist,
                artYear = R.string.image2_year,
                onPrevClick = { curStep-- },
                onNextClick = { curStep++ }
            )
        }
        else -> {
            ChangingArt(
                artImage = R.drawable.guitar,
                artTitle = R.string.image3_title,
                artArtist = R.string.image3_artist,
                artYear = R.string.image3_year,
                onPrevClick = { curStep-- },
                onNextClick = { curStep = 1 }
            )
        }
    }
}

@Composable
fun ChangingArt(
    @DrawableRes artImage: Int,
    @StringRes artTitle: Int,
    @StringRes artArtist: Int,
    @StringRes artYear: Int,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(artImage),
                contentDescription = stringResource(artTitle),
                modifier = Modifier
                    .padding(vertical = 50.dp)
                    .width(300.dp)
                    .height(400.dp)
                    .shadow(elevation = 2.5.dp)
                    .padding(40.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .background(Color(0xFFEBEAF3))
                    .padding(20.dp),
            ) {
                Text(
                    text = stringResource(artTitle),
                    fontSize = 25.sp
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append(
                                stringResource(artArtist)
                            )
                        }
                        append(" ")
                        withStyle(style = SpanStyle(color = Color.DarkGray)) {
                            append("(${stringResource(artYear)})")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onPrevClick, modifier = Modifier.width(130.dp)) {
                    Text(
                        text = stringResource(R.string.btn_previous)
                    )
                }
                Button(onClick = onNextClick, modifier = Modifier.width(130.dp)) {
                    Text(
                        text = stringResource(R.string.btn_next)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ComposeStudyTheme {
        ArtSpaceApp()
    }
}