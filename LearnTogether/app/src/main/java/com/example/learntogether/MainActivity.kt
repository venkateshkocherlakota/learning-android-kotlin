package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTogetherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LearnTogether()
                }
            }
        }
    }
}

@Composable
fun LearnTogether(modifier: Modifier = Modifier) {
    Column {
        BannerImage(modifier)
        TitleText(modifier)
        FirstPara(modifier)
        SecondPara(modifier)
    }
}

@Composable
fun SecondPara(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16. dp, end = 16. dp),
        textAlign = TextAlign.Justify,
        text = stringResource(R.string.second_para)
    )
}

@Composable
fun FirstPara(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16. dp, end = 16. dp),
        textAlign = TextAlign.Justify,
        text = stringResource(R.string.first_para)
    )
}

@Composable
fun TitleText(modifier: Modifier) {
    Text(
        modifier = modifier.padding(16. dp),
        fontSize = 26. sp,
        text = stringResource(R.string.title_text)
    )
}

@Composable
fun BannerImage(modifier: Modifier) {
    Image (
        modifier = modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.bg_compose_background),
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTogetherTheme {
        LearnTogether()
    }
}