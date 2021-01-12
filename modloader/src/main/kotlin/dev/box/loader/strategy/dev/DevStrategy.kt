package dev.box.loader.strategy.dev

import dev.box.loader.Mod
import dev.box.loader.strategy.Strategy
import java.io.File
import java.util.*


object DevStrategy : Strategy {
    @Suppress("UNCHECKED_CAST")
    override fun load(discovered: List<File>) {
        val cl = Thread.currentThread().contextClassLoader

        var clClass: Class<*> = cl.javaClass
        while (clClass != ClassLoader::class.java) {
            clClass = clClass.superclass
        }
        val classLoaderClassesField = clClass.getDeclaredField("classes")
        classLoaderClassesField.isAccessible = true
        val classes = (classLoaderClassesField[cl] as Vector<Class<*>>).toList().iterator()

        while (classes.hasNext()) {
            val next = classes.next()
            if (next.isAnnotationPresent(Mod::class.java)) {
                next.newInstance()
            }
        }
    }
}