package dev.xascar.network_sdk.utils

enum class MovieCategory {
    POPULAR,
    NOW_PLAYING,
    UPCOMING
}

/**
 * This extension function allows to extend new functionality of the MovieCategory class
 * without having to inherit from the class or use design patterns such as Decorator
 *
 *
 */
fun MovieCategory.toLowerCase() = this.name.lowercase()


/**
 *  Inline function:
 *  An Inline function is a kind of function that is declared with the keyword inline just before the function declaration.
 *  Once a function is declared inline, the compiler does not allocate any memory for this function,
 *  instead the compiler copies the piece of code virtually at the calling place at runtime.
 */