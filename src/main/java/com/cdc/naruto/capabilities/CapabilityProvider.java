package com.cdc.naruto.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProvider<C extends ICapability> implements ICapabilitySerializable<NBTTagCompound> {

    protected C capI;
    protected Capability<? extends ICapability> capability;

    public CapabilityProvider(Capability<C> capability){
        this.capI = capability.getDefaultInstance();
        this.capability = capability;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == this.capability;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? (T)capI : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return capI.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        capI.deserializeNBT(nbt);
    }
}
