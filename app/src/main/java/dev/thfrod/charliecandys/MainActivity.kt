package dev.thfrod.charliecandys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thfrod.charliecandys.ui.screens.SignInScreen
import dev.thfrod.charliecandys.ui.screens.ProductsScreen
import dev.thfrod.charliecandys.ui.screens.SignUpScreen
import dev.thfrod.charliecandys.ui.theme.CharlieCandysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharlieCandysTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "signIn") {
                        composable("products") {
                            ProductsScreen()
                        }

                        composable("signIn") {
                            SignInScreen(onSignUpClick= {navController.navigate("signUp")}, onLogin = {navController.navigate("products")})
                        }

                        composable("signUp") {
                            SignUpScreen()
                        }
                    }
                }
            }
        }
    }
}