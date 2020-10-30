package dev.minecraftbox.asm.transformer.interfaces

import org.objectweb.asm.tree.ClassNode

interface ITransformer {
    fun getClassNames(): MutableList<String>

    fun transform(classNode: ClassNode)
}