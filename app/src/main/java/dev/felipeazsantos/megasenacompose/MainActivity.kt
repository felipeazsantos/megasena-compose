package dev.felipeazsantos.megasenacompose

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.felipeazsantos.megasenacompose.ui.theme.MegaSenaComposeTheme
import java.util.Random

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
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("megasena", Context.MODE_PRIVATE)
    var result = remember { mutableStateOf(prefs.getString(PREFS_KEY, "") ?: "") }
    val bet = remember { mutableStateOf("") }

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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = bet.value,
                    label = {
                        Text("Digite um número entre 6 e 15")
                    }, onValueChange = {
                        bet.value = validateInput(it)
                    })
                Text(result.value, style = TextStyle(fontWeight = FontWeight.Bold))
            }

            Button(onClick = {
                val numbersIsValid = validateTextField(bet.value)
                if (!numbersIsValid) {
                    Toast.makeText(
                        context,
                        "Quantidade de números inválida",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                result.value = numbersGenerator( bet.value.toInt())
                saveNumberSequence(prefs, result.value)
            }) {
                Text("Gerar Números")
            }
        }
    }
}

fun saveNumberSequence(prefs: SharedPreferences, numberSequence: String) {
   prefs.edit().apply {
       putString(PREFS_KEY, numberSequence)
       apply()
   }

}

const val PREFS_KEY = "key_mega"
fun validateTextField(text: String) : Boolean {
    if (text.isEmpty()) return false

    val qtd = text.toInt()
    if (qtd !in 6..15) return false

    return true
}

fun validateInput(input: String): String {
    return input.filter { it.isDigit() }
}

fun numbersGenerator(qtd: Int): String {
    val numbers = mutableSetOf<Int>()

    while (true) {
        val n = Random().nextInt(60) + 1
        numbers.add(n)
        if (numbers.size == qtd) break
    }

    return numbers.joinToString(" - ")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MegaSenaComposeTheme {
        MainApp()
    }
}