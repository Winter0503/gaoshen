package com.example.hx_mvp_kotlin.widght

import android.util.AttributeSet
import android.view.View
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import com.example.hx_mvp_kotlin.R
import org.jetbrains.anko.sp

/**
 * Create by 心跳 on 2019/8/8 21:17
 * Blog : https://mp.csdn.net/
 * escription :
 */
class SlideBar (context : Context?,attrs: AttributeSet?=null) :View(context,attrs){


    var textBaseLine = 0.0f
    var sectionHeight = 0.0f
    val paint = Paint()

    init{
        paint.apply{
            color=resources.getColor(R.color.qq_section_text_color)
            textSize=sp(12).toFloat()
            textAlign=Paint.Align.CENTER
        }
    }
    companion object{
        private val SECTIONS = arrayOf("A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        sectionHeight  = h *1.0f / SECTIONS.size
        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.descent-fontMetrics.ascent
        textBaseLine= sectionHeight / 2 +(textHeight / 2 - fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas?) {
        val x=width/2*1.0f
        var y=textBaseLine
        SECTIONS.forEach {
            canvas!!.drawText(it,x,y,paint)
            y += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                setBackgroundColor(R.drawable.bg_slide_bar)
                val index = getTouchIndex(event)
                val firstLatter = SECTIONS[index]

                onSectionChange!!.onSectionChange(firstLatter)
            }

            MotionEvent.ACTION_MOVE ->{
                setBackgroundColor(R.drawable.bg_slide_bar)
                val index = getTouchIndex(event)
                val firstLatter = SECTIONS[index]
                onSectionChange!!.onSectionChange(firstLatter)
            }
            MotionEvent.ACTION_UP ->{
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChange!!.onSlideFinish()
            }
        }
        return true
    }

    private fun getTouchIndex(event: MotionEvent): Int {
        var index=(event.y /sectionHeight).toInt()
        //越界检查
        if (index < 0){
            index=0
        }else if(index>= SECTIONS.size){
            index= SECTIONS.size-1
        }
        return index
    }

    interface OnSectionChangeListener{
        fun onSectionChange(firstLetter : String)
        fun onSlideFinish()
    }
    var onSectionChange : OnSectionChangeListener? =null
}

/**
 *   ()
 *   {}
 *   ->
 */




























