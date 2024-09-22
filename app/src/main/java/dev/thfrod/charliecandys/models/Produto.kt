package dev.thfrod.charliecandys.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Produto(
    @SerializedName("PRODUTO_ID")
    val id: Int,

    @SerializedName("PRODUTO_NOME")
    val nome: String,

    @SerializedName("PRODUTO_DESC")
    val descricao: String,

    @SerializedName("PRODUTO_PRECO")
    val preco: BigDecimal,

    @SerializedName("PRODUTO_DESCONTO")
    val desconto: BigDecimal,

    @SerializedName("CATEGORIA_ID")
    val categoriaId: Int,

    @SerializedName("PRODUTO_ATIVO")
    val ativo: Int,

    val imagem: List<ProdutoImagem>
)

data class ProdutoImagem(
    @SerializedName("IMAGEM_ID")
    val id: Int,

    @SerializedName("IMAGEM_ORDEM")
    val ordem: Int,

    @SerializedName("PRODUTO_ID")
    val produtoId: Int,

    @SerializedName("IMAGEM_URL")
    val url: String
)
