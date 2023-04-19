package dev.xascar.moviesapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.xascar.moviesapppro.ui.theme.MoviesAppProTheme
import dev.xascar.moviesapppro.utils.DataState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppProTheme {
                // A surface container using the 'background' color from the theme
                val list = viewModel.moviesUIState
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(list){
                        is DataState.LOADING ->{

                        }
                        is DataState.SUCCESS ->{

                            for (item in list.response!!){
                                Greeting("${item?.title}")
                            }
                        }
                        is DataState.ERROR ->{

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesAppProTheme {
        Greeting("Android")
    }
}