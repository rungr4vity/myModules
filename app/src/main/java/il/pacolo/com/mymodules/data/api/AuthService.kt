package il.pacolo.com.mymodules.data.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @POST("oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded") // Ensuring form-encoded data
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field("grant_type") grant_type: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("scope") scope: String
    ): TokenResponse
}


    data class TokenResponse(val access_token: String)
