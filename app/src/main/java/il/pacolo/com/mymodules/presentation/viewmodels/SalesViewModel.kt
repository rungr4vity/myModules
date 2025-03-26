package il.pacolo.com.mymodules.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.viewModelScope

import il.pacolo.com.mymodules.data.api.SmartApi
import il.pacolo.com.mymodules.data.models.PaymentRequest
import il.pacolo.com.mymodules.data.repository.PaymentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SalesViewModel(
    private val paymentRepository: PaymentRepository = PaymentRepository(SmartApi.instance)
): ViewModel()  {


    private val _timeLeft = MutableLiveData<Int>()
    val timeLeft: LiveData<Int> get() = _timeLeft

    private val handler = Handler(Looper.getMainLooper())
    private var time = 120  // Countdown time in seconds
    private var isRunning = false  // Flag to track if the timer is running

    private var executionCount = 0
    private val maxExecutions = 3

    private val runnable = object : Runnable {
        override fun run() {
            if (time > 0) {
                time--  // Decrease time
                _timeLeft.value = time  // Update UI
                handler.postDelayed(this, 1000)  // Repeat after 1 second
            } else {
                //isRunning = false  // Mark as stopped
                cancelCountdown()
            }
        }
    }
    // Resets the countdown to the initial time
    fun initializeCountdown() {
        cancelCountdown()  // Stop existing countdown
        time = 10  // Reset time
        _timeLeft.value = time  // Update UI
    }
    // Cancels the countdown
    fun cancelCountdown() {
        handler.removeCallbacks(runnable)  // Stop handler
        isRunning = false  // Mark as stopped
    }
    // Starts or resumes the countdown
    fun startCountdown() {

        if (!isRunning) {  // Prevent multiple instances
            isRunning = true
            _timeLeft.value = time  // Set initial time
            handler.postDelayed(runnable, 1000)  // Start countdown
        }
    }
    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(runnable)  // Prevent memory leaks
    }


    // payment
    fun initiatePaymentRequests() {
        executionCount = 0
        Log.d("SalesViewModel_payment", "Payment process started...")
        //statusText.text = "Payment process started..."
        scheduleRequests()
    }

    private fun scheduleRequests() {
        val intervalMillis = (2 * 60 * 1000) / maxExecutions // 40 sec per request
        repeat(maxExecutions) { index ->
            handler.postDelayed({
                executePaymentRequest(index + 1)
            }, (index * intervalMillis).toLong())
        }
    }

    private fun executePaymentRequest(requestNumber: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = processPayment(requestNumber) // Simulated API call
                withContext(Dispatchers.Main) {
                    Log.d("SalesViewModel", "Request $requestNumber: $response")
                    //statusText.text = "Request $requestNumber: $response"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("SalesViewModel", "Request $requestNumber failed", e)
                    //statusText.text = "Request $requestNumber failed: ${e.message}"
                }
            }
        }
    }

    private suspend fun processPayment(requestNumber: Int): String {
        delay(1000) // Simulating network delay

        var retorna = ""
        if(requestNumber > 1) {
            viewModelScope.launch {
                val response =
                    paymentRepository.payment(
                        PaymentRequest(
                            "e87cdbb2-a245-4da4-b5df-3a452f613dc8",
                            "250228001078",
                            "502RI",
                            "12789121"
                        )
                    )

                if (response.isSuccessful) {
                    val paymentResponse = response.body()
                    Log.d("SalesViewModel_payment", "Payment Response: $paymentResponse")
                    Log.d("SalesViewModel_payment", "Payment Successful")
                    cancelCountdown()
                    retorna = "Payment Successful"
                } else {
                    Log.d("SalesViewModel_payment", "Failed")
                    retorna = "Payment Failed"
                }
            }
        } else
        {
            Log.d("SalesViewModel_payment", "primer intento,no ejecuta el payment")
        }


        return retorna


    }



    init {
        initiatePaymentRequests()
    }

    init {
//        viewModelScope.launch {
//            val response =
//            paymentRepository.payment(PaymentRequest("23a1a6a3-2060-4ef8-be76-42f522024130","250225001001", "502RI", "12789121"))
//
//            if (response.isSuccessful) {
//                val paymentResponse = response.body()
//                Log.d("SalesViewModel", "Payment Response: $paymentResponse")
//            }
//        }

    }





}