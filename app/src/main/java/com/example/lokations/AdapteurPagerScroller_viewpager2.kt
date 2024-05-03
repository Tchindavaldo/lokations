package com.example.lokations

import androidx.viewpager2.widget.ViewPager2
import android.view.View

    class AdapteurPagerScroller_viewpager2 : ViewPager2.PageTransformer {
        private var scrollDuration = 0

        fun setScrollDuration(duration: Int) {
            scrollDuration = duration
        }

        override fun transformPage(page: View, position: Float) {
            page.translationX = -position * page.width
            page.alpha = 1 - Math.abs(position)

            // Ajuster la durée du défilement
            val viewPager = page.parent as ViewPager2
            val scrollOffset = viewPager.width * scrollDuration
            if (viewPager.scrollState == ViewPager2.SCROLL_STATE_IDLE) {
                viewPager.post {
                    viewPager.beginFakeDrag()
                    viewPager.fakeDragBy(scrollOffset.toFloat())
                    viewPager.endFakeDrag()
                }
            }
        }
    }