package dev.box.tweaker.noop

import net.minecraft.launchwrapper.IClassTransformer

class NoopTransformer : IClassTransformer {
    override fun transform(name: String?, transformedName: String?, bytes: ByteArray?) = bytes
}