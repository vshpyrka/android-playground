package com.example.myapplication.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    init {
        paint.color = Color.BLACK

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val path1 = Path()
        path1.fillType = Path.FillType.EVEN_ODD

        path1.moveTo(6.5f, 79.99f)
        path1.lineTo(37.21f, 50.5f)
        path1.lineTo(6.5f, 19.79f)
        path1.lineTo(18.79f, 7.5f)
        path1.lineTo(49.5f, 38.21f)
        path1.lineTo(80.21f, 7.5f)
        path1.lineTo(92.5f, 19.79f)
        path1.lineTo(61.79f, 50.5f)
        path1.lineTo(92.5f, 79.99f)
        path1.lineTo(80.21f, 93.5f)
        path1.lineTo(49.5f, 62.79f)
        path1.lineTo(18.79f, 93.5f)
        path1.close()


        val path2 = Path()
        path2.fillType = Path.FillType.EVEN_ODD
        path2.addRect(0f,0f,50f, height.toFloat(),  Path.Direction.CW)
        path2.close()
        path2.op(path1, Path.Op.INTERSECT)
        canvas.drawPath(path2, paint)
    }

}