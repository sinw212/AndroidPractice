package com.sinw.composestudy.unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinw.composestudy.R
import com.sinw.composestudy.ui.theme.ComposeStudyTheme

class ArticleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp() {
    ArticleCard(
        painterResource(R.drawable.bg_compose_background),
        stringResource(R.string.write_article_first),
        stringResource(R.string.write_article_second),
        stringResource(R.string.write_article_third)
    )
}

@Composable
fun ArticleCard(image: Painter, first: String, second: String, third: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = first,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = second,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = third,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    ComposeStudyTheme {
        ArticleCard(
            painterResource(R.drawable.bg_compose_background),
            stringResource(R.string.write_article_first),
            stringResource(R.string.write_article_second),
            stringResource(R.string.write_article_third)
        )
    }
}