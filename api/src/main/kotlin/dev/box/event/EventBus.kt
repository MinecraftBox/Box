package dev.box.event

object EventBus {
    private val events = HashMap<Class<*>, MutableList<EventSubscriber>>()

    fun register(any: Any) {
        any.javaClass.methods.filter { it.isAnnotationPresent(SubscribeEvent::class.java) }.forEach {
            if (it.parameterCount != 1) error("Function cannot contain any other parameters apart from event")
            val classes = events[it.parameters[0].type] ?: mutableListOf()
            classes.add(EventSubscriber(any, it))
            events[it.parameters[0].type] = classes
        }
    }

    fun unregister(any: Any) = events.remove(any.javaClass)

    fun post(any: Any) {
        events[any.javaClass]?.forEach {
            it.method.invoke(it.instance, any)
        }
    }
}