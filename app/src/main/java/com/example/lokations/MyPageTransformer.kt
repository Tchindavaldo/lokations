package com.example.lokations

import android.view.View
import androidx.viewpager.widget.ViewPager

class MyPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val scaleFactor = 0.25f
        val absPosition = Math.abs(position)
        if (absPosition > 1) {
            page.alpha = 0f
        } else {
            page.alpha = 1f - absPosition
            page.scaleX = scaleFactor + (1 - scaleFactor) * (1 - absPosition)
            page.scaleY = scaleFactor + (1 - scaleFactor) * (1 - absPosition)
        }
    }
}