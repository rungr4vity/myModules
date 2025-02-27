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


    //@Headers("Authorization:eyJraWQiOiJpdUpFVzFmN1ZzWmRtWWpWdGRFTnhxbGNVRGM1KzFuTFk3Q1NYWFM4U0FjPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIzMjBtdjEzczcwZDI1djBkNTZrMDMyMXI0cyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoic3Bpbi1udHB5LWNsb3NlZGxvb3AtZGV2XC9xci5ub3RpZmljYXRpb24iLCJhdXRoX3RpbWUiOjE3NDA2MzkzMzIsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yX0o1Q3kyTjFDNiIsImV4cCI6MTc0MDY0MjkzMiwiaWF0IjoxNzQwNjM5MzMyLCJ2ZXJzaW9uIjoyLCJqdGkiOiI4MzQ2ZDhmOS1jNDJiLTQ3MzUtOWFkZi1mODQyZTY4NDZiN2YiLCJjbGllbnRfaWQiOiIzMjBtdjEzczcwZDI1djBkNTZrMDMyMXI0cyJ9.5WywVRguK7jtIibN-H4QH2CNKlFMNPV96N_QnFYfAWToYNRsXom3z1Wb3__TqZY26CTFKfKWCCA4ix4_PzeMScfsotwf0OTzvI5Cib1pnabas1Tx3na0ggUVwSiCWcilDJA0Em1nxPTDCrq_1I1aD6dzpZblpZQjOlvgt-pdoN2nY0V5kQ-KnB_Xgv8DgIOk0LdUiKrCz1MTpT-bYNOC7vw5cIhgxVvK1_HjQ31K-FG8ZByJUbP-QbuCPzw231AtqC5muX2AGVmqQ5G6G-es6YbAbnFVWkFeaSe7wQecsRPeqBNYHkRhJRbWT1BestcNDeqrbU_XLQ7eJODjNCIEXw")
    @POST("payment/authorization")
    suspend fun payments(@Body request: PaymentRequest): Response<PaymentResponse>
}











//    @POST("oauth2/token")
//    suspend fun token(@Body request: TokenRequest): Response<TokenResponse>

