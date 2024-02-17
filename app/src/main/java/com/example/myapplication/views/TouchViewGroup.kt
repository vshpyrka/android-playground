package com.example.myapplication.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class TouchViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        println("AAA Parent onInterceptTouchEvent $ev $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(ev)
        println("AAA Parent dispatchTouchEvent $ev $result")
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result = super.onTouchEvent(event)
        println("AAA Parent onTouch $event $result")
        return result
    }
}

class TouchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.BLACK)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(event)
        println("AAA Child dispatchTouchEvent $event $result")
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result = super.onTouchEvent(event)
        println("AAA Child onTouchEvent $event $result")
        return result
    }
}
