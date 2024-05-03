package com.example.lokations


    import android.content.Context
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentActivity
    import androidx.recyclerview.widget.RecyclerView
    import androidx.viewpager2.adapter.FragmentStateAdapter

    class adapteur_image_slide_1_home_page(private val context: Context) : FragmentStateAdapter(context as FragmentActivity) {
        private val NUM_ITEMS = 4

        // Retourne le nombre total de pages
        override fun getItemCount(): Int {
            return NUM_ITEMS
        }

        // Crée le fragment à afficher pour cette page
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> fragment_image_1_home.newInstance("0", "Page # 1")
                1 -> fragment_image_1_home.newInstance("0", "Page # 2")
                2 -> fragment_image_1_home.newInstance("0", "Page # 3")
                3 -> fragment_image_1_home.newInstance("0", "Page # 4")
                else -> throw IllegalStateException("Invalid position: $position")
            }
        }
    }
