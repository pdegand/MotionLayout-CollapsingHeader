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
      when (val currentState = motionLayout.currentState) {
        R.id.headerHidden -> {
          motionLayout.setTransition(R.id.headerHidden, R.id.headerExpanded)
          motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
              motionLayout.setTransitionListener(null)
              motionLayout.progress = 0f
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

  private fun logState(state: Int): String {
    return when (state) {
      R.id.headerHidden -> "HIDDEN"
      R.id.headerExpanded -> "EXPANDED"
      R.id.headerCollapsed -> "COLLAPSED"
      else -> "UNKNOWN"
    }
  }
}
