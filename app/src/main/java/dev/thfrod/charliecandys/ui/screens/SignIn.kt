package dev.thfrod.charliecandys.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thfrod.charliecandys.R
import dev.thfrod.charliecandys.User
import dev.thfrod.charliecandys.ui.theme.CharlieCandysTheme

@Composable
fun SignInScreen(OnSignUpClick:()->Unit,) {
    val inputModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    val columnModifier = Modifier.background(color = MaterialTheme.colorScheme.secondary).fillMaxHeight()
    val boxModifier = Modifier
        .padding(24.dp)
        .background(color = Color.White, shape = MaterialTheme.shapes.medium)


    Column(
        modifier = columnModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.charlie_logo),
            contentDescription = "Descrição da Imagem",
            modifier = Modifier.size(180.dp)
        )

        Box(modifier = boxModifier) {
            Column(modifier = Modifier.padding(12.dp)) {
                OutlinedTextField(value = username,
                    onValueChange = { newValue -> username = newValue },
                    label = { Text(text = "Email") },
                    modifier = inputModifier,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                    })
                OutlinedTextField(value = password,
                    onValueChange = { newValue -> password = newValue },
                    label = { Text(text = "Senha") },
                    modifier = inputModifier,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Password,
                            contentDescription = "Password Lock"
                        )
                    })

                Button(
                    onClick = {
                        onEnterClick(
                            User(username, password)
                        )
                    }, modifier = inputModifier, shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Entrar")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {OnSignUpClick()},
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Cadastrar")
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FormPreview() {
    CharlieCandysTheme {
        SignInScreen(OnSignUpClick = {})
    }
}

fun onEnterClick(user: User) {
    println("User: ${user.username}, Password: ${user.password}")
}