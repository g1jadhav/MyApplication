package com.temporary.myapplication.customClasses

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class PageIndicator : View {
    private val mPaintFill =
        Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintStroke =
        Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPosition = 0
    private var mWidth = 0
    private var mHeight = 0
    private var mDotCount = 0
    private var mOrientation = 0
    private var mRadius = 0f
    private var mDiff = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    fun setRadius(radius: Float) {
        mRadius = radius
    }

    fun setColor(circleFillColor: Int, strokeColor: Int) {
        mPaintFill.style = Paint.Style.FILL
        mPaintFill.color = circleFillColor
        mPaintStroke.color = strokeColor
    }

    fun setStrokeWidthWithoutFillColor(setStrokeWidth: Float) {
        mPaintStroke.isAntiAlias = true
        mPaintStroke.strokeWidth = setStrokeWidth
        mPaintStroke.style = Paint.Style.FILL //STROKE
        mPaintStroke.strokeJoin = Paint.Join.ROUND
        mPaintStroke.strokeCap = Paint.Cap.ROUND
    }

    fun setDotsCount(count: Int) {
        mDotCount = count
    }

    fun setOrientation(orientation: Int) {
        when (orientation) {
            LinearLayout.HORIZONTAL, LinearLayout.VERTICAL -> {
                mOrientation = orientation
                requestLayout()
            }
        }
    }

    fun setDiff(diff: Int) {
        mDiff = diff
    }

    fun invalidateDots(position: Int) {
        mPosition = position
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val sizeOfWidthOrHeight: Int
        val longPaddingLeft: Int
        val longPaddingRight: Int
        val shortPaddingBefore: Int
        if (mOrientation == LinearLayout.HORIZONTAL) {
            sizeOfWidthOrHeight = width
            longPaddingLeft = paddingLeft
            longPaddingRight = paddingRight
            shortPaddingBefore = paddingTop
        } else {
            sizeOfWidthOrHeight = height
            longPaddingLeft = paddingTop
            longPaddingRight = paddingBottom
            shortPaddingBefore = paddingLeft
        }
        val radius = mRadius * mDiff
        val shortOffset = shortPaddingBefore + mRadius
        var longOffset = longPaddingLeft + mRadius
        longOffset += (sizeOfWidthOrHeight - longPaddingLeft - longPaddingRight) / 2.0f - mDotCount * radius / 2.0f
        var dX: Float
        var dY: Float
        for (i in 0 until mDotCount) {
            val drawLong = longOffset + i * radius
            if (mOrientation == LinearLayout.HORIZONTAL) {
                dX = drawLong
                dY = shortOffset
            } else {
                dX = shortOffset
                dY = drawLong
            }
            if (mPosition == i) {
                canvas.drawCircle(dX, dY, mRadius, mPaintFill)
            } else {
                canvas.drawCircle(dX, dY, mRadius, mPaintStroke)
            }
        }
    }
}