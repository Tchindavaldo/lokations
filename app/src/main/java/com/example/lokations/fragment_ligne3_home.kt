package com.example.lokations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_ligne3_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_ligne3_home : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_ligne3_home, container, false)
        val recyclervieww = view.findViewById<RecyclerView>(R.id.recycleViewLigne3)
        val data3 = ArrayList<ItemsLigne3Model>()

        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo2","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo3","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo3","Maison","Maison","Note","300 000/ans"))

        val adapter = CustomHomeAdapterItemLigne3(data3)

        recyclervieww.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false )
        recyclervieww.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_ligne3_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_ligne3_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}