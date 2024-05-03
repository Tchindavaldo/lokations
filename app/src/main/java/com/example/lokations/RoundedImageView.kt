package com.example.lokations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet?=null,
    defStyleAttr:Int=0
):AppCompatImageView(context,attrs,defStyleAttr){
    private  val path = Path()
    override fun onDraw(canvas: Canvas?) {
        val radius = width.coerceAtMost(height)/2f
        path.addCircle(width/2f, height/2F, radius,Path.Direction.CCW)
        if (canvas != null) {
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }
}