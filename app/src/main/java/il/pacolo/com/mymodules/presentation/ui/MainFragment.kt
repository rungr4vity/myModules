package il.pacolo.com.mymodules.presentation.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import il.pacolo.com.mymodules.MainActivity
import il.pacolo.com.mymodules.R
import il.pacolo.com.mymodules.databinding.FragmentMainBinding
import il.pacolo.com.mymodules.presentation.viewmodels.SalesViewModel
import il.pacolo.com.mymodules.utils.ButtonUtils

class MainFragment : Fragment() {

        private var _binding:FragmentMainBinding? = null
        private val binding get() = _binding!!

        private val salesViewModel:SalesViewModel by viewModels()


    private val responseLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK){
            Toast.makeText(requireContext(), "Result OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Result NOT OK", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cancelButton.setOnClickListener {
            //salesViewModel.timeLeft.removeObservers(viewLifecycleOwner)
            //binding.textCounter.text = "0"
            salesViewModel.cancelCountdown()
        }





        salesViewModel.timeLeft.observe(viewLifecycleOwner, Observer { number ->

            binding.textCounter.text = number.toString()

            if (number < 40) {
                binding.textCounter.setTextColor(Color.parseColor("#FF0000"))
            }
            if (number == 0) {
                binding.textCounter.text = "QR expirado"
                binding.imgQrCode.setImageResource(R.drawable.anchor_24)
                binding.textCounter.setTextColor(Color.parseColor("#00FF00"))
                showPaymentDialog()
                Toast.makeText(requireContext(), "Time is up!", Toast.LENGTH_SHORT).show()
                return@Observer
            }


        })
        salesViewModel.startCountdown()




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        ButtonUtils.onClick(binding.btnPayment) {
            //val intent = Intent(requireContext(), SettingsActivity::class.java)
            //responseLaucher.launch(intent)

            salesViewModel.initiatePaymentRequests()
        }


        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Important to avoid memory leaks
    }


    // Dialog
    private fun showPaymentDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Payment Confirmation")
            .setMessage("Are you sure you want to proceed with the payment?")
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}