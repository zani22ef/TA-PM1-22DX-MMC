package com.uti.turtleguard.dashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.uti.turtleguard.R
import com.uti.turtleguard.databinding.FragmentContainerProfileBinding
import com.uti.turtleguard.form.LoginFormFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContainerProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContainerProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_container_profile, container, false)
        val binding = FragmentContainerProfileBinding.inflate(inflater, container, false)

        binding.btnLogout.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("namauser", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("logged_in_user")
            editor.apply()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frmMain, LoginFormFragment()).commit()
        }



//        val btnLogout: Button = view.findViewById(R.id.btnLogout)
//        btnLogout.setOnClickListener{
//            replaceFragment(LoginFormFragment())
//        }
        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerProfile, fragment).addToBackStack(null).commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContainerProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContainerProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}