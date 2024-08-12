package com.example.tictactoe

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.TicTacToeTheme


class MainActivity : AppCompatActivity() {
    var gameActive: Boolean = true

    // Player representation
    // 0 - X
    // 1 - O
    var activePlayer: Int = 0
    var gameState: IntArray = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    var winPositions: Array<IntArray> = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

    fun playerTap(view: View) {
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()
        if (!gameActive) {
            gameReset(view)
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer
            img.translationY = -1000f
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                val status: TextView = findViewById(R.id.status)
                status.text = "O's Turn - Tap to play"
            } else {
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                val status: TextView = findViewById(R.id.status)
                status.text = "X's Turn - Tap to play"
            }
            img.animate().translationYBy(1000f).setDuration(300)
        }
        // Check if any player has won
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                gameActive = false
                // Somebody has won! - Find out who!
                var winnerStr = if (gameState[winPosition[0]] == 0) {
                    "X has won"
                } else {
                    "O has won"
                }
                // Update the status bar for winner announcement
                val status: TextView = findViewById(R.id.status)
                status.text = winnerStr
            }
        }
    }

    fun gameReset(view: View?) {
        gameActive = true
        activePlayer = 0
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        (findViewById(R.id.c1) as ImageView).setImageResource(0)
        (findViewById(R.id.c2) as ImageView).setImageResource(0)
        (findViewById(R.id.c3) as ImageView).setImageResource(0)
        (findViewById(R.id.c4) as ImageView).setImageResource(0)
        (findViewById(R.id.c5) as ImageView).setImageResource(0)
        (findViewById(R.id.c6) as ImageView).setImageResource(0)
        (findViewById(R.id.c7) as ImageView).setImageResource(0)
        (findViewById(R.id.c8) as ImageView).setImageResource(0)
        (findViewById(R.id.c9) as ImageView).setImageResource(0)



        val status: TextView = findViewById(R.id.status)
        status.text = "X's Turn - Tap to play"
    }

    class MainActivity : ComponentActivity() {
        @OptIn(ExperimentalMaterial3Api::class)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                TicTacToeTheme {
                    // App content
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting("Android", Modifier.padding(innerPadding))
                    }
                }
            }
        }

        @Composable

        fun Greeting(name: String, modifier: Modifier = Modifier) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Text(
                    text = "Hello World",
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    @Composable
    fun DefaultPreview() {
        TicTacToeTheme {
            Greeting("Android")
        }
    }
}
}

