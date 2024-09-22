package dev.thfrod.charliecandys.`interface`

import dev.thfrod.charliecandys.User
import retrofit2.Call
import retrofit2.http.GET
import dev.thfrod.charliecandys.models.Produto
import dev.thfrod.charliecandys.responses.ApiResponse
import dev.thfrod.charliecandys.responses.LoginResponse
import kotlinx.serialization.json.JsonElement
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiProductService {
    @GET("/api/produtos")
    fun getProdutos(): Call<List<Produto>>
}

interface ApiLoginService {
    @POST("/api/login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: User): Call<ApiResponse<LoginResponse>>
}
