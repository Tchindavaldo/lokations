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
 * Use the [fragment_ligne5_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_ligne5_home : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_ligne5_home, container, false)
        val recyclervieww = view.findViewById<RecyclerView>(R.id.recycleViewLigne2)
        val data2 = ArrayList<ItemsLigne2Model>()

        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 1","200 000/ans","En cour de l'iberartion","3 chambre disponible","200 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 2","250 000/ans","En cour de l'iberartion","3 chambre disponible","250 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","300 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","300 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))

      //  val adapter = CustomHomeAdapterItemLigne2(data2)

        recyclervieww.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false )
            //  recyclervieww.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_ligne5_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_ligne5_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}