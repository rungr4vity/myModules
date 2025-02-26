package il.pacolo.com.mymodules.data

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authService: AuthService): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        //retrieve the auth token from  your endpoint
        val authToken = runBlocking{
            try {

                authService.getToken().token

            }catch(e:Exception){
                null
            }
        }

        if(authToken.isNullOrEmpty()){
            return chain.proceed(originalRequest)
        }

        val modifierRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        return chain.proceed(modifierRequest)

    } // end override

}// end class