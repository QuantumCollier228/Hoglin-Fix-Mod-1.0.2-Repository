package net.quantum.hoglinfixmod.events;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.quantum.hoglinfixmod.HoglinFixMod;
import net.quantum.hoglinfixmod.ai.CrimsonTemptGoal;

@Mod.EventBusSubscriber(modid = HoglinFixMod.MODID)
public class HoglinEvents {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Hoglin hoglin) {
            GoalSelector goalSelector = hoglin.goalSelector;
            goalSelector.addGoal(4, new CrimsonTemptGoal(hoglin, 1.2D, 16.0D));
        }
    }
}
