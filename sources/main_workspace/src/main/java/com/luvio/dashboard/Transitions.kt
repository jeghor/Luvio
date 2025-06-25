package com.luvio.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

fun subtleHorizontalSlideIn(isPop: Boolean): EnterTransition {
    return fadeIn(animationSpec = tween(220, delayMillis = 90)) +
            slideInHorizontally(
                initialOffsetX = { if (isPop) -it else it / 8 },
                animationSpec = tween(220)
            )
}

fun subtleHorizontalSlideOut(isPop: Boolean): ExitTransition {
    return fadeOut(animationSpec = tween(90)) +
            slideOutHorizontally(
                targetOffsetX = { if (isPop) it else -it / 8 },
                animationSpec = tween(150)
            )
}