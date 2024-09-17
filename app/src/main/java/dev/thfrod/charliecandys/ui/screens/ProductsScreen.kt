package dev.thfrod.charliecandys.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.thfrod.charliecandys.models.Product
import java.math.BigDecimal
import kotlin.random.Random

@Composable
fun ProductsScreen() {
    val products = sampleProducts(20)
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Olá")
        }
        LazyColumn {
            items(products) { product ->
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
                    AsyncImage(
                        product.image,
                        contentDescription = "product image",
                        Modifier
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(100.dp),
                        placeholder = ColorPainter(Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = product.name, Modifier.padding(8.dp))
                    Text(
                        text = product.price.toPlainString(), Modifier.padding(8.dp)
                    )
                }
            }
        }
    }

}


private fun sampleProducts(amount: Int) = List(amount) {
    Product(
        name = LoremIpsum(
            Random.nextInt(2, amount)
        ).values.first(),
        price = BigDecimal(it * 1.5),
        image = "https://imgur.com/a/3eo7XGn"
    )
}