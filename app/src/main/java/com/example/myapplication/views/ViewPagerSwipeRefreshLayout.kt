package com.example.myapplication.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs


/**
 * SwipeRefreshLayout might intercept horizontal scroll of RecyclerView or ViewPager.
 * Use this class to prevent such behaviour. It checks the touch gesture in onInterceptTouchEvent().
 */

class ViewPagerSwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {

    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var previousX = 0F
    private var declined: Boolean = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                previousX = event.x
                declined = false
            }

            MotionEvent.ACTION_MOVE -> {
                val eventX = event.x
                val xDiff = abs(eventX - previousX)

                if (declined || xDiff > touchSlop) {
                    declined = true
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}
