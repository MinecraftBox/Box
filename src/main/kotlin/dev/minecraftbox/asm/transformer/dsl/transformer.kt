package dev.minecraftbox.asm.transformer.dsl

fun transformer(transformer: TransformerBuilder.() -> Unit) = TransformerBuilder().apply(transformer).build()