/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.fluid.render;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;

import alexiil.mc.lib.attributes.fluid.volume.FluidVolume;
import alexiil.mc.lib.attributes.fluid.volume.NormalFluidVolume;

public class ImplicitVanillaFluidVolumeRenderer extends FluidVolumeRenderer {

    public static final ImplicitVanillaFluidVolumeRenderer INSTANCE = new ImplicitVanillaFluidVolumeRenderer();

    @Override
    public void render(FluidVolume fluid, List<FluidRenderFace> faces, double x, double y, double z) {
        int colour = fluid.getRenderColor();
        final Sprite sprite;

        if (fluid instanceof NormalFluidVolume) {
            Fluid fl = ((NormalFluidVolume) fluid).getRawFluid();
            BlockState state = fl.getDefaultState().getBlockState();
            sprite = MinecraftClient.getInstance().getBlockRenderManager().getModel(state).getSprite();
        } else {
            sprite = MinecraftClient.getInstance().getSpriteAtlas().getSprite(fluid.getSprite());
        }

        renderSimpleFluid(faces, x, y, z, sprite, colour);
    }
}
