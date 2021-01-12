package dev.box.eventbus

abstract class Event<T> {
    private var handlers = mutableListOf<EventHandler<T>>()

    fun registerHandler(handler: EventHandler<T>) {
        handlers.add(handler)
        handlers = handlers.sortedBy { it.getPriority() }.toMutableList()
    }

    fun post(data: T) = handlers.forEach { it.post(data) }
}