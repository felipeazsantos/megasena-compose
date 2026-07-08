package dev.felipeazsantos.megasenacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.felipeazsantos.megasenacompose.ui.theme.MegaSenaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaSenaComposeTheme {
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Boa Sorte!", modifier = Modifier.padding(bottom = 20.dp), style = TextStyle(
                    color = Color(0xFF50C878), fontSize = 36.sp, fontWeight = FontWeight.Black
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = "",
                    label = {
                    Text("Digite um número entre 6 e 15")
                }, onValueChange = {})
                Text(" Resultado APARECE aqui", style = TextStyle(fontWeight = FontWeight.Bold))
            }

            Button(onClick = {}) {
                Text("Gerar Números")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MegaSenaComposeTheme {
        MainApp()
    }
}