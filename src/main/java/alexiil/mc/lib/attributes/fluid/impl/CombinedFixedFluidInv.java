/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.fluid.impl;

import java.util.List;

import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.fluid.FixedFluidInv;
import alexiil.mc.lib.attributes.fluid.volume.FluidVolume;

/** An {@link FixedFluidInv} that delegates to a list of them instead of storing items directly. */
public class CombinedFixedFluidInv<InvType extends FixedFluidInv> extends CombinedFixedFluidInvView<InvType>
    implements FixedFluidInv {

    public CombinedFixedFluidInv(List<? extends InvType> views) {
        super(views);
    }

    @Override
    public boolean setInvFluid(int tank, FluidVolume to, Simulation simulation) {
        return getInv(tank).setInvFluid(getSubTank(tank), to, simulation);
    }
}
