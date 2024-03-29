package app.extensions

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils
import kotlin.math.sqrt

inline fun View.afterLayoutChanged(crossinline doAnimation: View.() -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            v.removeOnLayoutChangeListener(this)
            doAnimation()
        }
    })
}

operator fun AnimatorSet.plus(animator: Animator): AnimatorSet {
    play(animator)
    return this
}

operator fun AnimatorSet.plusAssign(animator: Animator){
    play(animator)
}

inline fun Animator.onAnimationEnd(crossinline onEnd: (Animator?) -> Unit) {
    val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            onEnd(animation)
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    }
    addListener(animationListener)
}