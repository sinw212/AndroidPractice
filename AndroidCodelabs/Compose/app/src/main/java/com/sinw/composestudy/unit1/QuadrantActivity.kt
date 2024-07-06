package com.sinw.composestudy.unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sinw.composestudy.R
import com.sinw.composestudy.ui.theme.ComposeStudyTheme

class QuadrantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadrantApp()
                }
            }
        }
    }
}

@Composable
fun QuadrantApp() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            QuadrantCard(
                Color(0xFFEADDFF),
                stringResource(R.string.quadrant_first_title),
                stringResource(R.string.quadrant_first_content),
                modifier = Modifier.weight(1f)
            )
            QuadrantCard(
                Color(0xFFD0BCFF),
                stringResource(R.string.quadrant_second_title),
                stringResource(R.string.quadrant_second_content),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            QuadrantCard(
                Color(0xFFB69DF8),
                stringResource(R.string.quadrant_third_title),
                stringResource(R.string.quadrant_third_content),
                modifier = Modifier.weight(1f)
            )
            QuadrantCard(
                Color(0xFFF6EDFF),
                stringResource(R.string.quadrant_fourth_title),
                stringResource(R.string.quadrant_fourth_content),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun QuadrantCard(
    color: Color,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantCardPreview() {
    ComposeStudyTheme {
        QuadrantApp()
    }
}