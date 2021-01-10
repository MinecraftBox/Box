package dev.box.title

object TitleManager {
    private val stringBuilder = StringBuilder()

    fun addTitle(title: String) {
        stringBuilder.append("| $title ")
    }

    override fun toString() = stringBuilder.toString()
}