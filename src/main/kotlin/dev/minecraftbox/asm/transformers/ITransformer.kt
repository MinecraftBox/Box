package dev.minecraftbox.asm.transformers

import org.objectweb.asm.tree.ClassNode

/**
 * Handles implementation for transformers
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */
interface ITransformer {

    // Classes to be transformed
    val classNames : Array<String>

    // Transformation process
    fun transform(classNode: ClassNode)
}