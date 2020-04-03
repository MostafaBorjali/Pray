package com.borjali.mostafa.pray.utils

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.borjali.mostafa.pray.R

class TabBarMotion : View {

    //    val mHeight: Float = MainActivity.height - MainActivity.height / 8f
    private var parentWidth: Int = 0
    private var parentHeight: Int = 0
    private val paint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    private var leftX = 0f
    private var centerX = 0f
    private var rightX = 0f
    private var cubeStartX = 0f
    private var cubeEndX = 0f
    private val mDuration = 250L


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {

        invalidate()
        paint.apply {
            reset()
//            flags = Paint.ANTI_ALIAS_FLAG
            style = Paint.Style.FILL_AND_STROKE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = context.resources.getColor(R.color.colorPrimary, null)
            } else {
                color = context.resources.getColor(R.color.colorPrimary)
            }
        }

    }

    val bezierPath = Path()
    private var values = 0f
    private var raduse = 0f

    fun left() {
        if (values == 0f) {
            return
        }


        val moove = values - leftX
        val curvanimator = ValueAnimator.ofFloat(values, values - moove)
        curvanimator.apply {
            duration = mDuration
            start()
        }
        curvanimator.addUpdateListener { valueAnimator ->
            values = valueAnimator.animatedValue as Float
            invalidate()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        parentWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        parentHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        this.setMeasuredDimension(parentWidth, parentHeight)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

//        prepare()
    }

    fun right() {
        if (values == 0f) {
            return
        }
        val moove = rightX - values

        val curvanimator = ValueAnimator.ofFloat(values, moove + values)
        curvanimator.apply {
            duration = mDuration
            start()
        }
        curvanimator.addUpdateListener { valueAnimator ->
            values = valueAnimator.animatedValue as Float
            invalidate()
        }
    }

    fun center() {
        val moove = centerX - values
        if (values == 0f) {
            return
        }


        val curvanimator = ValueAnimator.ofFloat(values, values + moove)
        curvanimator.apply {
            duration = mDuration
            start()
        }
        curvanimator.addUpdateListener { valueAnimator ->
            values = valueAnimator.animatedValue as Float
            invalidate()
        }
    }

    private var padding = context.resources.getDimension(R.dimen._10sdp)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        raduse = context.resources.getDimension(R.dimen._50sdp) / 2
        values = width / 2f
        padding = height / 6f
        leftX = width / 6f
        centerX = width / 2f
        rightX = width / 6f * 5
//        invalidate()
        Log.e("widthththht", values.toString() + "width: " + rightX)

    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)


        // val rectF = RectF()
        //Log.e("heiiiiigh", values.toString() + "raduse" + raduse)
        // rectF.set(0f, 0f, 0f, 0f)

        bezierPath.reset()
        bezierPath.apply {
            moveTo(0f, 0f)
            lineTo(0f, height.toFloat())
            lineTo(width.toFloat(), height.toFloat())
            lineTo(width.toFloat(), 0f)
            cubeStartX = values + raduse
            cubeEndX = values - raduse
            lineTo(values + raduse  , 0f)
            cubicTo(
                values + 3 * padding,
                raduse + padding ,
                values - 3 * padding,
                raduse + padding ,
                values - raduse  ,
                0f
            )
            lineTo(0f, 0f)

        }






        canvas?.drawPath(bezierPath, paint)
    }


}