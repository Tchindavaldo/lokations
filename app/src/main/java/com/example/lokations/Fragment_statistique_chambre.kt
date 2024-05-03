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
 * Use the [Fragment_statistique_chambre.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_statistique_chambre : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_statistique_chambre, container, false)
        val recyclervieww = view.findViewById<RecyclerView>(R.id.recyclerviewHomme)
        val data_periodique_statistique_chambre = ArrayList<data_periodique_statistique_chambre>()

        data_periodique_statistique_chambre.add(data_periodique_statistique_chambre("jour 1","03-06-2024",
            13,11,12,19,11,1,
            3,1,51,11,
            11,4,11,1,65,4931))
        data_periodique_statistique_chambre.add(data_periodique_statistique_chambre("jour 2","04-06-2024",
            23,21,22,29,21,12,
            2,2,21,12,
            21,24,211,2,85,2931))

        data_periodique_statistique_chambre.add(data_periodique_statistique_chambre("jour 3","05-06-2024",
            13,11,12,19,11,1,
            3,1,51,11,
            11,4,11,1,65,4931))
        recyclervieww.layoutManager = LinearLayoutManager(requireContext())


        val adapter = adapteur_recycleView_statistique_chambre(data_periodique_statistique_chambre, requireContext())


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
         * @return A new instance of fragment Fragment_statistique_chambre.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_statistique_chambre().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}