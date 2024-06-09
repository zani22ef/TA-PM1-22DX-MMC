package com.uti.turtleguard.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uti.turtleguard.R
import com.uti.turtleguard.databinding.FragmentBottomNavBinding
import com.uti.turtleguard.databinding.FragmentLoginFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Bottom_Nav.newInstance] factory method to
 * create an instance of this fragment.
 */
class Bottom_Nav : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBottomNavBinding


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
        val view = inflater.inflate(R.layout.fragment_bottom__nav, container, false)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
          // Menambahkan transaksi dengan HomeFragment dengan memakai fungsi transaksi
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_turtle -> replaceFragment(TurtleFragment())
                R.id.navigation_report -> replaceFragment(ReportFragment())


            }
            true
        }


        return view
    }

    //    Buat fungsi untuk bertransaksi dengan fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment).addToBackStack(null).commit()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Bottom_Nav.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Bottom_Nav().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}