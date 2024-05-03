package com.example.lokations

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.NestedScrollingParent
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2


class adapteur_scroll_inParent(val frg: Fragment){

    fun tru(){

        frg.view?.findViewById<ViewPager2>(R.id.page_boutique)?.requestDisallowInterceptTouchEvent(true)

    }

    fun fal(){
        frg.view?.findViewById<ViewPager2>(R.id.page_boutique)?.requestDisallowInterceptTouchEvent(false)

    }



}
/*
class adapteur_scroll_inParent(context: Context) : NestedScrollView(context), NestedScrollingParent {
    private var childViewPager: ViewPager2? = null

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(child, target, axes, type)
        childViewPager?.let { viewPager ->
            viewPager.isUserInputEnabled = false
        }
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        super.onStopNestedScroll(target, type)
        childViewPager?.let { viewPager ->
            viewPager.isUserInputEnabled = true
        }
    }

    fun setChildViewPager(viewPager: ViewPager2) {
        childViewPager = viewPager
    }
}*/