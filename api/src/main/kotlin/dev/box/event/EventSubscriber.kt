package dev.box.event

import java.lang.reflect.Method

data class EventSubscriber(val instance: Any, val method: Method)