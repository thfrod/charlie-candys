package dev.thfrod.charliecandys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thfrod.charliecandys.models.Navegacao
import dev.thfrod.charliecandys.ui.screens.SignInScreen
import dev.thfrod.charliecandys.ui.screens.ProductsScreen
import dev.thfrod.charliecandys.ui.screens.SignUpScreen
import dev.thfrod.charliecandys.ui.theme.CharlieCandysTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharlieCandysTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navItems = remember {
                        mutableListOf(
                            Navegacao(
                                rota = "products",
                                titulo = "Home",
                                icone = Icons.Default.Home,
                                selecionado = false
                            ), Navegacao(
                                rota = "products",
                                titulo = "Produtos",
                                icone = Icons.Default.Store,
                                selecionado = false
                            ), Navegacao(
                                rota = "products",
                                titulo = "Pedidos",
                                icone = Icons.Default.Inventory,
                                selecionado = false
                            ), Navegacao(
                                rota = "products",
                                titulo = "Carrinho",
                                icone = Icons.Default.ShoppingCart,
                                selecionado = false
                            ), Navegacao(
                                rota = "signIn",
                                titulo = "Eu",
                                icone = Icons.Default.AccountCircle,
                                selecionado = false
                            )
                        )
                    }
                    val currentRoute by navController.currentBackStackEntryAsState()

                    Column(modifier = Modifier.fillMaxSize()) {
                        if (currentRoute?.destination?.route != "signIn" && currentRoute?.destination?.route != "signUp") {
                            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ), navigationIcon = {
                                IconButton(onClick = { /* Do something */ }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }, title = {
                                Image(
                                    painter = painterResource(id = R.drawable.charlie_logo),
                                    contentDescription = "Descrição da Imagem",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }, actions = {
                                IconButton(onClick = { /* Do something */ }) {
                                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                                }
                            })
                        }

                        NavHost(
                            navController = navController,
                            startDestination = "signIn",
                            modifier = Modifier.weight(1f)
                        ) {
                            composable("products") {
                                ProductsScreen()
                            }

                            composable("signIn") {
                                SignInScreen(onSignUpClick = { navController.navigate("signUp") },
                                    onLogin = { navController.navigate("products") })
                            }

                            composable("signUp") {
                                SignUpScreen()
                            }
                        }
                        if (currentRoute?.destination?.route != "signIn" && currentRoute?.destination?.route != "signUp") {
                            BottomAppBar(actions = {
                                navItems.forEach { item ->

                                    NavigationBarItem(selected = item.selecionado, onClick = {
                                        if (!item.selecionado) {
                                            navController.navigate(item.rota,
                                                navOptions = navOptions {
                                                    launchSingleTop = true
                                                    popUpTo(navController.graph.startDestinationId)
                                                })
                                            navItems.forEach { it.selecionado = false }
                                            item.selecionado = true
                                        }
                                    }, icon = {
                                        Icon(
                                            item.icone, contentDescription = item.titulo
                                        )
                                    }, label = {
                                        Text(text = item.titulo)
                                    })
                                }

                            })
                        }
                    }
                }
            }
        }
    }
}