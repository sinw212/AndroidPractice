package com.sinw.composestudy.unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinw.composestudy.R
import com.sinw.composestudy.ui.theme.ComposeStudyTheme

class BusinessCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD1E7D3))
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .background(Color(0xFF073042))
        )
        Text(
            text = "SoHyun Kim",
            fontSize = 40.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Android Developer",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF046E3B)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 100.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        InfomationText(
            painterResource(R.drawable.baseline_call_24),
            "010-1234-5678"
        )
        InfomationText(
            painterResource(R.drawable.baseline_share_24),
            "@AndroidDev"
        )
        InfomationText(
            painterResource(R.drawable.baseline_email_24),
            "android@android.dev"
        )
    }
}

@Composable
fun InfomationText(
    icon: Painter,
    info: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(bottom = 5.dp)
    ) {
        androidx.compose.material3.Icon(
            painter = icon,
            contentDescription = null,
            tint = Color(0xFF046E3B)
        )
        Text(
            text = info,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    ComposeStudyTheme {
        BusinessCardApp()
    }
}