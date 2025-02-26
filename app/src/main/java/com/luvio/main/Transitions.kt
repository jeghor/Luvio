package com.luvio.main

import androidx.compose.animation.*
import androidx.compose.animation.core.*

fun customSlideInHorizontally(isPop: Boolean): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { if (isPop) -it else it },
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )
}

fun customSlideOutHorizontally(isPop: Boolean): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { if (isPop) it else -it },
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )
}