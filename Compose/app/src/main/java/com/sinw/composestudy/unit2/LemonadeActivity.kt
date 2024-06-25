package com.sinw.composestudy.unit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sinw.composestudy.R
import com.sinw.composestudy.ui.theme.ComposeStudyTheme

class LemonadeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStudyTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var curStep by remember { mutableStateOf(1) }
    var squeezeCnt by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (curStep) {
                1 -> {
                    LemonTextAndImage(
                        painterResource(R.drawable.lemon_tree),
                        stringResource(R.string.lemon_tree_image_des),
                        stringResource(R.string.lemon_tree_content_des),
                        onClick = {
                            curStep = 2
                            squeezeCnt = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonTextAndImage(
                        painterResource(R.drawable.lemon_squeeze),
                        stringResource(R.string.lemon_image_des),
                        stringResource(R.string.lemon_content_des),
                        onClick = {
                            squeezeCnt--
                            if (squeezeCnt == 0) {
                                curStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        painterResource(R.drawable.lemon_drink),
                        stringResource(R.string.glass_of_lemonade_image_des),
                        stringResource(R.string.glass_of_lemonade_content_des),
                        onClick = {
                            curStep = 4
                        }
                    )
                }

                4 -> {
                    LemonTextAndImage(
                        painterResource(R.drawable.lemon_restart),
                        stringResource(R.string.empty_glass_image_des),
                        stringResource(R.string.empty_glass_content_des),
                        onClick = {
                            curStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    image: Painter,
    imageDes: String,
    contentDes: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.btn_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = image,
                contentDescription = imageDes,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.btn_image_width))
                    .height(dimensionResource(R.dimen.btn_image_height))
                    .padding(dimensionResource(R.dimen.btn_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(
            text = contentDes,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun LemonadePreview() {
    ComposeStudyTheme {
        LemonadeApp()
    }
}