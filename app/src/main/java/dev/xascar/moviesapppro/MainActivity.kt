package dev.xascar.moviesapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.ui.theme.MoviesAppProTheme
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppProTheme {
                // A surface container using the 'background' color from the theme
                val list = viewModel.popular.observe(this){

                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val moviesViewModel: MoviesViewModel = hiltViewModel()
                    PlayingNowScreen(moviesViewModel.playingNow)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //baseContext.registerReceiver()
    }

    override fun onStop() {
        super.onStop()
        //baseContext.unregisterReceiver()

    }

}

@Composable
fun PlayingNowScreen(data: StateFlow<PagingData<DomainMovie>>) {
    val movies = data.collectAsLazyPagingItems()

    LazyColumn {
        items(items = movies) {movie ->
            MovieItem(movie)
        }
    }

}

@Composable
fun MovieItem(movie: DomainMovie?) {
    TODO("Not yet implemented")
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