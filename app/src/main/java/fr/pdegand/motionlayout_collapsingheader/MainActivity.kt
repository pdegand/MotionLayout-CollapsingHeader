package fr.pdegand.motionlayout_collapsingheader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    toggleButton.setOnClickListener {
      when(val currentState = motionLayout.currentState) {
        R.id.headerHidden -> {
          motionLayout.setTransition(R.id.headerHidden, R.id.headerExpanded)
          motionLayout.setTransitionListener(object: TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
              motionLayout.setTransitionListener(null)
              motionLayout.setTransition(R.id.headerExpanded, R.id.headerCollapsed)
            }
          })
          motionLayout.transitionToEnd()
        }
        R.id.headerCollapsed, R.id.headerExpanded -> {
          motionLayout.setTransition(currentState, R.id.headerHidden)
          motionLayout.transitionToEnd()
        }
      }
    }
  }
}
