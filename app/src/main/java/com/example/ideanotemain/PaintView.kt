package com.example.ideanotemain

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import com.example.ideanotemain.PaintActivity.Companion.paintBrush
import com.example.ideanotemain.PaintActivity.Companion.path
import com.google.android.material.color.MaterialColors.getColor

class PaintView : View {

    var params : ViewGroup.LayoutParams? = null

    companion object{
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
        lateinit var bmp : Bitmap
    }

    constructor(context: Context) : this(context, null){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                path.lineTo(x, y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {
        var w = canvas.width
        var h = canvas.height
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565)
        val can: Canvas = Canvas(bmp)
        var fill = Paint()
        fill.style = Paint.Style.FILL
        fill.setColor(Color.WHITE)
        can.drawRect(0F, 0F, width.toFloat(), height.toFloat(), fill)
        for(i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            can.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }
}