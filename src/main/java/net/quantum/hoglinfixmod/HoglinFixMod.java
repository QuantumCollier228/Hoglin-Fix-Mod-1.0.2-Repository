package net.quantum.hoglinfixmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.quantum.hoglinfixmod.events.HoglinEvents;
import net.quantum.hoglinfixmod.events.MobDrops;

@Mod(HoglinFixMod.MODID)
public class HoglinFixMod {
    public static final String MODID = "hoglinfixmod";

    private static final IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    public HoglinFixMod() {
        ModItems.register(MOD_BUS);
        MinecraftForge.EVENT_BUS.register(new HoglinEvents());
        MinecraftForge.EVENT_BUS.register(new MobDrops());
    }
}
