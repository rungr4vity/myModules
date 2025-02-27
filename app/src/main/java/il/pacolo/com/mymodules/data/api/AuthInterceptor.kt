package il.pacolo.com.mymodules.data.api

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authService: AuthService): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        //retrieve the auth token from  your endpoint
        val authToken = runBlocking{
            try {

                authService.getAuthToken("client_credentials","320mv13s70d25v0d56k0321r4s",
                    "894k0ij5ruv0qt9cubivqnk9pc527tlec802j98qf3lal2at2rp","spin-ntpy-closedloop-dev/qr.notification").access_token

            }catch(e:Exception){
                null
            }
        }

        if(authToken.isNullOrEmpty()){
            return chain.proceed(originalRequest)
        }

        val modifierRequest = originalRequest.newBuilder()
            .header("Authorization", "$authToken")
            .build()

        return chain.proceed(modifierRequest)

    } // end override

}// end class