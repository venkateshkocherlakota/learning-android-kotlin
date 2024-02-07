package com.venki.simplytap

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.venki.simplytap.ui.theme.SimplyTapTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LockScreenOrientation()
            SimplyTapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TapCounter()
                }
            }
        }
    }
}

fun calculateFontSize(value: Long = 0): Int {
    if (value < 10) return 200
    if (value in 10..99) return 150
    if (value in 100 .. 999) return 100
    if (value in 1000 .. 10000) return 75
    return 50
}

@Composable
fun TapCounter(modifier: Modifier = Modifier) {
    var countValue by remember { mutableLongStateOf(0) }
    Surface(
        modifier = modifier.fillMaxSize(),
        onClick = {
            if (countValue == Long.MAX_VALUE)
                countValue = 0
            countValue++
        }
        ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = countValue.toString(),
                textAlign = TextAlign.Center,
                fontSize = calculateFontSize(countValue). sp,
                modifier = modifier.fillMaxWidth(),
            )
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.tap_anywhere_to_increment),
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 28. sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = modifier,
                    onClick = { countValue = 0 },
                ) {
                    Text(stringResource(R.string.reset))
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.made_by_text),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimplyTapTheme {
        TapCounter()
    }
}

/**
 * Lock the current screen orientation while composed. Release the lock when the composition is left.
 */
@Composable
fun LockScreenOrientation() {
    val context = LocalContext.current
    DisposableEffect(context) {
        // Lock the screen orientation.
        context.requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        onDispose {
            // Release the the screen orientation lock.
            context.requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}

/**
 * Find the activity context. If one isn't present, an exception is thrown.
 */
fun Context.requireActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No activity was present but it is required.")
}