package dev.thfrod.charliecandys.ui.screens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thfrod.charliecandys.R
import dev.thfrod.charliecandys.User
import dev.thfrod.charliecandys.data.PreferencesManager
import dev.thfrod.charliecandys.`interface`.ApiLoginService
import dev.thfrod.charliecandys.responses.ApiResponse
import dev.thfrod.charliecandys.responses.LoginResponse
import dev.thfrod.charliecandys.ui.theme.CharlieCandysTheme
import dev.thfrod.charliecandys.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun SignInScreen(onSignUpClick: () -> Unit, onLogin: () -> Unit) {
    val context = LocalContext.current
    val inputModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    val columnModifier = Modifier
        .background(color = MaterialTheme.colorScheme.secondary)
        .fillMaxHeight()
    val boxModifier = Modifier
        .padding(24.dp)
        .background(color = Color.White, shape = MaterialTheme.shapes.medium)


    Column(
        modifier = columnModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember {
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
                OutlinedTextField(value = email,
                    onValueChange = { newValue -> email = newValue },
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
                            User(email, password), context, onLogin
                        )
                    }, modifier = inputModifier, shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Entrar")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { onSignUpClick() },
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
        SignInScreen(onSignUpClick = {}, onLogin = {})
    }
}

fun onEnterClick(user: User, uiContext: Context, onLogin: () -> Unit) {
    val userReq = User(user.email, user.password)

    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService = retrofit.create(ApiLoginService::class.java)
    apiService.login(userReq).enqueue(object : Callback<ApiResponse<LoginResponse>> {
        override fun onResponse(
            call: Call<ApiResponse<LoginResponse>>, response: Response<ApiResponse<LoginResponse>>
        ) {
            if (response.isSuccessful) {
                val token = response.body()?.data?.token
                if (token != null) {
                    Toast.makeText(uiContext, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                    val preferencesManager = PreferencesManager(uiContext)
                    preferencesManager.saveToken(token)
                    onLogin()
                } else {
                    Toast.makeText(
                        uiContext,
                        "Não foi possível realizar o login, tente novamente!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(
                    uiContext,
                    "Não foi possível realizar o login, tente novamente!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<ApiResponse<LoginResponse>>, t: Throwable) {
            Toast.makeText(
                uiContext, "Não foi possível realizar o Login, tente novamente!", Toast.LENGTH_SHORT
            ).show()
        }
    })
}