package ru.er.utils

import java.util.concurrent.atomic.AtomicBoolean

open class Consumable<out T>(
    open val data: T? = null,
) {

    private val consumed = AtomicBoolean()

    fun consume(): Boolean = consumed.compareAndSet(false, true)
}