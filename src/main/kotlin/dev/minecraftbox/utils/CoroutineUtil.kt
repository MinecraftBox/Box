package dev.minecraftbox.utils

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//val modLoadingCoroutine = CoroutineScope(Dispatchers.IO + CoroutineName("Mod Launching Coroutine"))

fun launchCoroutine(name: String, block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Dispatchers.IO + CoroutineName(name)).launch(block = block)