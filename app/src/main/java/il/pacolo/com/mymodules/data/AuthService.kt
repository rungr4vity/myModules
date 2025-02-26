package il.pacolo.com.mymodules.data

import retrofit2.http.GET

interface AuthService {
    @GET("auth/token")
    suspend fun getToken(): TokenResponse
}

    data class TokenResponse(val token: String)
