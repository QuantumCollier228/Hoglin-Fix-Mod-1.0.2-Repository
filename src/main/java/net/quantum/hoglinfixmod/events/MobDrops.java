package net.quantum.hoglinfixmod.events;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.quantum.hoglinfixmod.ModItems;

@Mod.EventBusSubscriber(modid = "hoglinfixmod")
public class MobDrops {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity().getType() == EntityType.HOGLIN && !event.getEntity().isBaby()) {
            event.getDrops().clear();
            event.getDrops().add(new ItemEntity(
                    event.getEntity().level(),
                    event.getEntity().getX(),
                    event.getEntity().getY(),
                    event.getEntity().getZ(),
                    new ItemStack(ModItems.RAW_HOGLIN_MEAT.get())
            ));
        }
    }
}
