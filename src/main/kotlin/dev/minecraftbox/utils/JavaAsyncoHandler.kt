package dev.minecraftbox.utils

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger


/**
 * Allows multithreading for java code
 *
 * from https://github.com/MediaModMC/MediaMod/blob/master/src/main/java/org/mediamod/mediamod/util/Multithreading.java
 *
 * @author Decobr
 */

object JavaAsyncoHandler {
    private val threadCounter = AtomicInteger(0)

    private val SERVICE =
        Executors.newCachedThreadPool { task -> Thread(task, "Box Thread " + threadCounter.getAndIncrement()) }

    fun runAsync(task: Runnable) {
        SERVICE.execute(task)
    }
}