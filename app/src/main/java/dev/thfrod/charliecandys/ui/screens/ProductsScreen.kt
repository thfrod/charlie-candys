package dev.thfrod.charliecandys.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.thfrod.charliecandys.R
import dev.thfrod.charliecandys.`interface`.ApiProductService
import dev.thfrod.charliecandys.models.Produto
import dev.thfrod.charliecandys.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductsScreen() {
    // Usa um estado que será automaticamente observado pela UI
    var produtosList by remember { mutableStateOf<List<Produto>>(emptyList()) }

    // Chama a função de buscar produtos e atualiza o estado
    LaunchedEffect(Unit) {
        getProdutos { produtos ->
            produtosList = produtos // Atualiza o estado da lista de produtos
        }
    }
    Column {

        LazyColumn {
            items(produtosList) { product ->
                Column(
                    Modifier
                        .padding(8.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    if (product.imagem.isNotEmpty()) {
                        AsyncImage(
                            product.imagem[0].url,
                            contentDescription = "product image",
                            Modifier
                                .padding(8.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .height(100.dp),
                            placeholder = painterResource(id = R.drawable.product_not_found),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.product_not_found),
                            contentDescription = "Product not found",
                            Modifier
                                .padding(8.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .height(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(text = product.nome, Modifier.padding(8.dp))
                    Text(
                        text = product.preco.toPlainString(), Modifier.padding(8.dp)
                    )
                }
            }
        }
    }

}

private fun getProdutos(onResult: (List<Produto>) -> Unit) {
    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService = retrofit.create(ApiProductService::class.java)

    apiService.getProdutos().enqueue(object : Callback<List<Produto>> {
        override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
            if (response.isSuccessful) {
                val produtos = response.body() ?: emptyList()
                onResult(produtos) // Passa os produtos para o callback
            } else {
                Log.e("API Error", "Response not successful. Code: ${response.code()}")
                onResult(emptyList()) // Retorna uma lista vazia em caso de erro
            }
        }

        override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
            Log.e("API Failure", "Error fetching products")
            onResult(emptyList()) // Retorna uma lista vazia em caso de falha
        }
    })
}
