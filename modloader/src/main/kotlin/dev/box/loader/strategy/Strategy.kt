package dev.box.loader.strategy

import java.io.File

interface Strategy {
    fun load(discovered: List<File>)
}