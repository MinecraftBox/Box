package dev.minecraftbox.utils.render.font

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import java.awt.Color

object FontRendererUtils {
    fun drawString(text: String, x: Float, y: Float, color: Color, dropShadow: Boolean = false, scale: Double = 0.0) {
        if (scale <= 0) {
            Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color.rgb, dropShadow)
        } else {
            GlStateManager.pushMatrix()
            GlStateManager.scale(scale, scale, scale)
            Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color.rgb, dropShadow)
            GlStateManager.popMatrix()
        }
    }
}