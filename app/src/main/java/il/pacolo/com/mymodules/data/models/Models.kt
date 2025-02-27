package il.pacolo.com.mymodules.data.models



import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class PaymentRequest(
   val paymentToken: String,
   val orderId: String,
   val storeId: String,
   val serialNumber: String)




data class PaymentResponse(
  val responseCode:String = "",
  val orderId:String = "",
  val paymentToken:String = "",
  val systemDate: String = "",
  val adminDate:String= ""
)

//    "paymentToken": "23a1a6a3-2060-4ef8-be76-42f522024130",
//    "orderId":"250225001001",
//    "storeId": "502RI",
//    "serialNumber":"12789121"



//@JsonClass(generateAdapter = true)
//data class TokenResponse(
//    @Json(name = "token") val token: String
//)





