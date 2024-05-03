package com.example.lokations

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_boutique_boutique.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_boutique_boutique : Fragment() {
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

        val db = Firebase.firestore
        val view = inflater.inflate(R.layout.fragmentbtiqueboutique, container, false)
        val ajout_produit = view.findViewById<ConstraintLayout>(R.id.ajout_produit)
        val modifier_produit = view.findViewById<ConstraintLayout>(R.id.modifier_produit)
        val recyclervieww = view.findViewById<RecyclerView>(R.id.recyclerviewHomme)
        var list_data_fragment_infos_chambre = ArrayList<data_fragment_infos_chambre>()


        ajout_produit.setOnClickListener {
            val bit2 =  Intent(requireContext(), activity_ajout_produit::class.java)
            startActivity(bit2)

        }
        modifier_produit.setOnClickListener {
            val bit2 =  Intent(requireContext(), activity_update_produit::class.java)
            startActivity(bit2)

        }

        val docRef = db.collection("users").document("user")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {

                    Toast.makeText(requireContext(),"recuperation de donné" , Toast.LENGTH_SHORT).show()
                    val users = document.get("listOfUsers") as List<HashMap<String, Any>>


                    // Ajouter le nouvel utilisateur à la liste
                    val updatedUsers = ArrayList(users)
                    for(data in updatedUsers){
                        val item = data["item"] as String
                        val prix = data["prix"] as String

                        val data = data_fragment_infos_chambre(item)
                        list_data_fragment_infos_chambre.add(data)
                    }

                    recyclervieww.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = adapteur_recycleView_chambre_info(recyclervieww,childFragmentManager,list_data_fragment_infos_chambre, requireContext())
                    recyclervieww.adapter = adapter


                } else {
                    Toast.makeText(requireContext(),"pas de document USER ", Toast.LENGTH_SHORT).show()

                    list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 1"))
                    list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 2"))


                    recyclervieww.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = adapteur_recycleView_chambre_info(recyclervieww,childFragmentManager,list_data_fragment_infos_chambre, requireContext())
                    recyclervieww.adapter = adapter
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(),"erreur lors du get" , Toast.LENGTH_SHORT).show()

                list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 1"))
                list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 2"))
                list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 2"))
                list_data_fragment_infos_chambre.add(data_fragment_infos_chambre("chambre 2"))


                recyclervieww.layoutManager = LinearLayoutManager(requireContext())
                val adapter = adapteur_recycleView_chambre_info(recyclervieww,childFragmentManager,list_data_fragment_infos_chambre, requireContext())
                recyclervieww.adapter = adapter
            }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_boutique_boutique.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_boutique_boutique().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}