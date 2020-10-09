package dev.minecraftbox.asm.transformers.impl

import dev.minecraftbox.asm.transformers.ITransformer
import dev.minecraftbox.asm.transformers.ITransformerLoader
import dev.minecraftbox.utils.launchCoroutine
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode

/**
 * Create an implementation of ITransformerLoader to load transformers.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

object TransformerLoader : ITransformerLoader {
    override fun loadTransformer(transformer: ITransformer) {
        launchCoroutine("Transformer Loader") {
            transformer.classNames.forEach {
                val classNode = ClassNode()
                ClassReader(it).also { reader -> reader.accept(classNode, ClassReader.EXPAND_FRAMES) }
                transformer.transform(classNode)
            }
        }
    }
}