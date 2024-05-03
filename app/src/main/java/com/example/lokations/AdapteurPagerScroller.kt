package com.example.lokations

import android.content.Context
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager



class AdapteurPagerScroller(context: Context) : Scroller(context, DecelerateInterpolator()) {
    private var scrollDuration = 0

    fun setScrollDuration(scrollDuration: Int) {
        this.scrollDuration = scrollDuration
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, scrollDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, scrollDuration)
    }

    fun initViewPagerScroll(viewPager: ViewPager) {
        try {
            val scrollerField = ViewPager::class.java.getDeclaredField("mScroller")
            scrollerField.isAccessible = true
            scrollerField.set(viewPager, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}