package com.uti.turtleguard.form

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uti.turtleguard.R
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.dashboard.Bottom_Nav
import com.uti.turtleguard.databinding.FragmentLoginFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginFormBinding

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
//        return inflater.inflate(R.layout.fragment_login_form, container, false)
//        deklarasi sekaligus definisi variabel binding
        val binding = FragmentLoginFormBinding.inflate(inflater, container, false)
        val lite = Lite(requireContext())


        binding.btnSignin.setOnClickListener() {
            val username = binding.inputUsername.text.toString()
            val pass = binding.inputPassword.text.toString()
            val loginSukses = lite.login(username, pass)
            if (loginSukses) {
                val sharedPreferences = requireContext().getSharedPreferences("namauser", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                // Simpan nama pengguna yang login
                editor.putString("logged_in_user", username)
                editor.apply()
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frmMain, Bottom_Nav()).commit()
            } else {
                Toast.makeText(requireContext(), "Username/Password Salah", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.txtSignup.setOnClickListener() {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frmMain, SignupFormFragment()).commit()
        }

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}