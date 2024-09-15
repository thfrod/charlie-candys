package dev.thfrod.charliecandys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thfrod.charliecandys.ui.theme.CharlieCandysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharlieCandysTheme {
                Form()
            }
        }
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(24.0.dp)) {
            var username by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }

            Text(text = "Entrar", modifier = modifier)

            Text(text = "Faça login ou crie uma conta", modifier = modifier)

            TextField(value = username, onValueChange = { newValue -> username = newValue })

            TextField(value = password, onValueChange = { newValue -> password = newValue })

            Button(onClick = {}) {
                Text(text = "Entrar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    CharlieCandysTheme {
        Form()
    }
}