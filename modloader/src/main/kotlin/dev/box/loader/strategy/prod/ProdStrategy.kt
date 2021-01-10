package dev.box.loader.strategy.prod

import dev.box.event.EventBus
import dev.box.loader.Mod
import dev.box.loader.strategy.Strategy
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*

object ProdStrategy : Strategy {
    @Suppress("UNCHECKED_CAST")
    override fun load(discovered: List<File>) {
        discovered.forEach {
            val cl = URLClassLoader(arrayOf<URL>(it.toURI().toURL()), this::class.java.classLoader)
            var clClass: Class<*> = cl.javaClass
            while (clClass != ClassLoader::class.java) {
                clClass = clClass.superclass
            }
            val classLoaderClassesField = clClass.getDeclaredField("classes")
            classLoaderClassesField.isAccessible = true
            val classes = (classLoaderClassesField[cl] as Vector<Class<*>>).toList()

            classes.filter { clazz -> clazz.isAnnotationPresent(Mod::class.java) }.forEach { clazz -> EventBus.register(clazz.newInstance()) }
        }
    }
}