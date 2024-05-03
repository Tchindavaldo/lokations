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
 * Use the [fragment_ligne6_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_ligne6_home : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_ligne6_home, container, false)
        val recyclervieww = view.findViewById<RecyclerView>(R.id.recycleViewLigne5)
        val data5 = ArrayList<ItemsLigne5Model>()

        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre1","bangante","150 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre4","bangante","180 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre6","bangante","100 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre7","bangante", "200 000/ans"))

        val adapter = CustomHomeAdapterItemLigne5(data5)

        recyclervieww.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.HORIZONTAL,false )
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
         * @return A new instance of fragment fragment_ligne6_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_ligne6_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}