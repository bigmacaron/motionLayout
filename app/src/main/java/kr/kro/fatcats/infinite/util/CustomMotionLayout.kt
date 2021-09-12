package kr.kro.fatcats.infinite.util

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import kr.kro.fatcats.infinite.R
import kr.kro.fatcats.infinite.databinding.ActivityMainBinding

class CustomMotionLayout(context: Context,attributeSet: AttributeSet?) : MotionLayout(context,attributeSet) {

    //사용될때 호출 할 변수들 지정
    private val mainContainerLayout by lazy{
        findViewById<View>(R.id.mainContainerLayout)
    }

    private val gestureListener by lazy {
        object : GestureDetector.SimpleOnGestureListener(){
            override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
                mainContainerLayout.getHitRect(hitRect) //메인(스크롤) 클릭 영역 지정
                return hitRect.contains(e1.x.toInt(),e1.y.toInt())  // 영역안이면 불린값 리턴
            }
        }
    }

    private val gestureDetector by lazy{
        GestureDetector(context, gestureListener)
    }

    private val hitRect = Rect()  //터치 영역 지정 변수
    private var motionTouchStarted = false

    init{
        setTransitionListener(object : TransitionListener{
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                motionTouchStarted = false
            }
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //원래 터치 이벤트를 리턴
        when(event.actionMasked){
            MotionEvent.ACTION_UP , MotionEvent.ACTION_CANCEL -> {
                motionTouchStarted = false
                return super.onTouchEvent(event)
            }
        }
        if(!motionTouchStarted){
            mainContainerLayout.getHitRect(hitRect) // 변수값을 영역으로 포함시킴
            motionTouchStarted = hitRect.contains(event.x.toInt(),event.y.toInt()) // 영역안에 포함 되었는지?
        }
        return super.onTouchEvent(event) && motionTouchStarted
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }



}