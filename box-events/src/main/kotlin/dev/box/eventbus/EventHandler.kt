package dev.box.eventbus

interface EventHandler<T> {
    fun getPriority() = 0

    fun post(event: T)
}