package il.pacolo.com.mymodules.data.repository

import il.pacolo.com.mymodules.data.api.SmartApi
import il.pacolo.com.mymodules.data.models.PaymentRequest
import il.pacolo.com.mymodules.data.models.PaymentResponse
import retrofit2.Response

class PaymentRepository(private val api: SmartApi) {

    suspend fun payment(paymentRequest: PaymentRequest): Response<PaymentResponse> {
        return api.payments(paymentRequest)
    }



}