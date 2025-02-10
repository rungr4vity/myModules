package il.pacolo.com.mymodules.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.ListFragment
import il.pacolo.com.mymodules.MainActivity
import il.pacolo.com.mymodules.R
import il.pacolo.com.mymodules.databinding.FragmentMainBinding

class MainFragment : Fragment() {

        private var _binding:FragmentMainBinding? = null
        private val binding get() = _binding!!


    // the response from the second fragment
    private val responseLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK){
            Toast.makeText(requireContext(), "Result OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Result NOT OK", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainBinding.inflate(inflater, container, false)


        binding.btnSend.setOnClickListener {

            val intent = Intent(requireContext(), SettingsActivity::class.java)
            responseLaucher.launch(intent)
        }


        return binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Important to avoid memory leaks
    }
}