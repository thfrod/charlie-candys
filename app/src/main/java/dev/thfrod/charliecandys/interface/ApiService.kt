package dev.thfrod.charliecandys.`interface`

import retrofit2.Call
import retrofit2.http.GET
import dev.thfrod.charliecandys.models.Produto

interface ApiProductService {
    @GET("/api/produtos")
      fun getProdutos(): Call<List<Produto>>
}
