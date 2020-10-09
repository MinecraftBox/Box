package dev.minecraftbox.asm.transformers

/**
 * Interface to handle multiple implementations of the loaders
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

interface ITransformerLoader {
    // Loads a transformer
    fun loadTransformer(transformer: ITransformer)

    /**
     * Loads multiple transformers but has a default method which for each transformer invokes ITransformerLoader#loadTransformer
     */
    fun loadTransformers(transformers : List<ITransformer>) = transformers.forEach(::loadTransformer)
}