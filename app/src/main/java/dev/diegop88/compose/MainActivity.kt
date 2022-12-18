package dev.diegop88.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.ComposeNavHost
import dev.diegop88.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Scaffold {
                    ComposeNavHost(
                        Modifier
                            .padding(it)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
