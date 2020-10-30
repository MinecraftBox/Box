package dev.minecraftbox.asm.transformer.dsl

import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import org.objectweb.asm.tree.ClassNode

class TransformerBuilder {
    var classNames = emptyList<String>()

    private var transform: ((classNode: ClassNode) -> Unit)? = null

    fun transform(transformerBuilder: ClassNode.() -> Unit) {
        transform = transformerBuilder
    }

    internal fun build() = object : ITransformer {
        override fun getClassNames() = classNames.toMutableList()

        override fun transform(classNode: ClassNode) {
            transform?.invoke(classNode)
        }
    }
}