package dev.minecraftbox.asm.transformer

import com.google.common.collect.ArrayListMultimap
import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import dev.minecraftbox.loader.ModLoader
import net.minecraft.launchwrapper.IClassTransformer
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.ClassNode

class ClassTransformer : IClassTransformer {
    val transformers: ArrayListMultimap<String, ITransformer> = ArrayListMultimap.create()

    init {
        collectModTransformers().forEach {
            loadTransformer(it)
        }
    }

    override fun transform(name: String?, transformedName: String?, bytes: ByteArray?): ByteArray? {
        if (bytes == null) return null

        val classTransformers = transformers[transformedName] ?: return null

        if (classTransformers.isEmpty()) return bytes

        val node = ClassNode()
        ClassReader(bytes).also {
            it.accept(node, ClassReader.EXPAND_FRAMES)
        }

        classTransformers.forEach {
            it.transform(node)
        }


        return ClassWriter(ClassWriter.COMPUTE_FRAMES).also {
            node.accept(it)
        }.toByteArray()
    }


    fun loadTransformer(transformer: ITransformer) {
        transformer.getClassNames().forEach {
            transformers.put(it, transformer)
        }
    }


    fun collectModTransformers() = ModLoader.loader.getTransformers()
}

fun <K, V> MutableMap<K, MutableList<V>>.put(key: K, value: V) {
    val list = get(key) ?: return
    list.add(value)
    set(key, list)
}