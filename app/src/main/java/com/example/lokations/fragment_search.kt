package com.example.lokations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_search.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_search : Fragment() {
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
        val view=  inflater.inflate(R.layout.fragment_search, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        tabLayout.addTab(tabLayout.newTab().setText("prix"))
        tabLayout.addTab(tabLayout.newTab().setText("klity"))
        tabLayout.addTab(tabLayout.newTab().setText("comment"))
        tabLayout.addTab(tabLayout.newTab().setText("ville"))
        tabLayout.addTab(tabLayout.newTab().setText("position"))
        tabLayout.addTab(tabLayout.newTab().setText("like"))
        tabLayout.addTab(tabLayout.newTab().setText("visite"))
        tabLayout.addTab(tabLayout.newTab().setText("prix"))
        tabLayout.addTab(tabLayout.newTab().setText("klity"))
        tabLayout.addTab(tabLayout.newTab().setText("comment"))

        val data = ArrayList<ItemsViewModel>()
        val dataa = ArrayList<ItemsViewModell>()
        val data2Search = ArrayList<ItemsViewModel2>()
        val data3Search = ArrayList<ItemsViewModel3>()

        data2Search.add(
            ItemsViewModel2(
                R.drawable.m88,
                "cité hypocratea",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )
        data2Search.add(
            ItemsViewModel2(
                R.drawable.m88,
                "cité hypocrateb",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )
        data2Search.add(
            ItemsViewModel2(
                R.drawable.m88,
                "cité hypocrate",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )


        data3Search.add(
            ItemsViewModel3(
                R.drawable.m88,
                "cité rose",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )
        data3Search.add(
            ItemsViewModel3(
                R.drawable.m88,
                "cité rose",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )
        data3Search.add(
            ItemsViewModel3(
                R.drawable.m88,
                "cité rose",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )
        data3Search.add(
            ItemsViewModel3(
                R.drawable.m88,
                "cité rose",
                "24 chambre",
                "20 chambre libre",
                "4 en cours de l'iberation",
                "fv  lv  ds",
                "fv  lv"
            )
        )


        data.add(ItemsViewModel(data2Search))
        data.add(ItemsViewModel(data2Search))
        data.add(ItemsViewModel(data2Search))
        data.add(ItemsViewModel(data2Search))
        data.add(ItemsViewModel(data2Search))

        dataa.add(ItemsViewModell(data3Search))
        dataa.add(ItemsViewModell(data3Search))
        dataa.add(ItemsViewModell(data3Search))
        dataa.add(ItemsViewModell(data3Search))
        dataa.add(ItemsViewModell(data3Search))


        val intentSearch: Intent = Intent(requireContext(), FrameLayoutActivity::class.java)
        val adapterSearch = CustomAdapter(data, dataa, requireContext(), "detail_cite", intentSearch)
        recyclerview.adapter = adapterSearch

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_search.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_search().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector =
                GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val child = recyclerView.findChildViewUnder(e.x, e.y)
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                        }
                    }
                })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}