package net.quantum.hoglinfixmod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HoglinFixMod.MODID);

    public static final RegistryObject<Item> RAW_HOGLIN_MEAT = ITEMS.register("raw_hoglin_meat",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.3F)
                            .meat()
                            .build()
                    )
            ));

    public static final RegistryObject<Item> COOKED_HOGLIN_MEAT = ITEMS.register("cooked_hoglin_meat",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.8F)
                            .meat()
                            .build()
                    )
            ));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}

