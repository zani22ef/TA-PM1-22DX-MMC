package com.uti.turtleguard.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uti.turtleguard.R
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.databinding.FragmentSignupFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFormFragment : Fragment() {
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
//        return inflater.inflate(R.layout.fragment_signup_form, container, false)
//        deklarasi sekaligus definisi variable binding
        val binding = FragmentSignupFormBinding.inflate(inflater, container, false)
//        panggil class lite
        val lite = Lite(requireContext())
//ketika btn sign up di klik
        binding.btnSignup.setOnClickListener(){
            val nama =  binding.inputNamalengkap.text.toString()
            val username = binding.inputUsername.text.toString()
            val pass = binding.inputPassword.text.toString()
            if (nama.length>1 && username.length>1 && pass.length>1){
                val pengguna = Lite.Pengguna(nama,  username, pass)
                val hasil = lite.insertPengguna(pengguna)
                if (hasil != -1L) {
                    Toast.makeText(requireContext(), "Sign Up Berhasil, Silakan Login", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Gagal memasukkan data", Toast.LENGTH_SHORT).show()
                }
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frmMain, LoginFormFragment()).commit()
            } else {
                Toast.makeText(requireContext(), "Semua form wajib diisi", Toast.LENGTH_LONG).show()
            }
        }
//        kerika text sign in di klik
        binding.txtSignin.setOnClickListener(){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frmMain, LoginFormFragment()).commit()
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
         * @return A new instance of fragment SignupFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}