package kr.kro.fatcats.infinite.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import kr.kro.fatcats.infinite.R
import kr.kro.fatcats.infinite.databinding.ActivityMainBinding

class CustomMotionLayout(context: Context,attributeSet: AttributeSet?) : MotionLayout(context,attributeSet) {



    private val motionTouchStarted = false

    private val mainContainerLayout by lazy{
        findViewById<View>(R.id.mainContainerLayout)
    }



}