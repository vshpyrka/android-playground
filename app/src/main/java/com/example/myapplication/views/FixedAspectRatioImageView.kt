package com.example.myapplication.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Rational
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.R

class FixedAspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var aspectRatio: Rational

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioImageView, 0, 0)
        try {
            aspectRatio = Rational.parseRational(
                typedArray.getString(R.styleable.FixedAspectRatioImageView_aspect_ratio)
            )
        } finally {
            typedArray.recycle()
        }
        background = ColorDrawable(Color.DKGRAY)
    }

    fun setAspectRatio(ratio: Rational) {
        if (aspectRatio != ratio) {
            aspectRatio = ratio
            requestLayout()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int
        val height: Int
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY && MeasureSpec.getMode(
                heightMeasureSpec
            ) == MeasureSpec.EXACTLY
        ) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = MeasureSpec.getSize(heightMeasureSpec)
        } else if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = (width / aspectRatio.toFloat()).toInt()
        } else {
            height = MeasureSpec.getSize(widthMeasureSpec)
            width = (height / aspectRatio.toFloat()).toInt()
        }
        setMeasuredDimension(width, height)
    }
}
