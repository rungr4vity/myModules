package il.pacolo.com.mymodules.data.api

import il.pacolo.com.mymodules.data.models.PaymentRequest
import il.pacolo.com.mymodules.data.models.PaymentResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface SmartApi {


    // MoshiConverterFactory.create())
    companion object {

        private fun provideAuthService(): AuthService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://app.api-netpay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AuthService::class.java)
        }
        private val authService = provideAuthService()
        private val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authService)) // Attach interceptor
            .build()





        val instance: SmartApi = Retrofit.Builder()
            .baseUrl("https://closed-loop.api-netpay.com/service/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(SmartApi::class.java)
    }



    @POST("payment/authorization")
    suspend fun payments(@Body request: PaymentRequest): Response<PaymentResponse>
}











//    @POST("oauth2/token")
//    suspend fun token(@Body request: TokenRequest): Response<TokenResponse>

