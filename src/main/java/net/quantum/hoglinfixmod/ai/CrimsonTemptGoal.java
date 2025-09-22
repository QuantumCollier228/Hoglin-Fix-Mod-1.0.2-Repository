package net.quantum.hoglinfixmod.ai;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.List;
import java.util.Comparator;

public class CrimsonTemptGoal extends Goal {
    private final PathfinderMob mob;
    private final double speed;
    private final double radius;
    private Player targetPlayer;
    private int delayTicks = 0;
    private boolean startedRunning = false;

    public CrimsonTemptGoal(PathfinderMob mob, double speed, double radius) {
        this.mob = mob;
        this.speed = speed;
        this.radius = radius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private static boolean playerHoldsCrimson(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return main.is(Items.CRIMSON_FUNGUS) || off.is(Items.CRIMSON_FUNGUS);
    }

    @Override
    public boolean canUse() {
        if (delayTicks > 0) {
            delayTicks--;
            return false;
        }

        Level world = mob.level();
        List<Player> players = world.getEntitiesOfClass(Player.class, mob.getBoundingBox().inflate(radius), CrimsonTemptGoal::playerHoldsCrimson);
        if (players.isEmpty()) {
            targetPlayer = null;
            return false;
        }

        players.sort(Comparator.comparingDouble(p -> p.distanceToSqr(mob)));
        targetPlayer = players.get(0);
        startedRunning = false;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetPlayer == null) return false;

        if (!playerHoldsCrimson(targetPlayer)) {
            targetPlayer = null;
            delayTicks = 40;
            mob.getNavigation().stop();
            startedRunning = false;
            return false;
        }

        return mob.distanceToSqr(targetPlayer) <= radius * radius;
    }

    @Override
    public void stop() {
        targetPlayer = null;
        mob.getNavigation().stop();
        startedRunning = false;
    }

    @Override
    public void tick() {
        if (targetPlayer != null) {
            mob.getLookControl().setLookAt(targetPlayer, 30.0F, 30.0F);

            if (!startedRunning) {
                mob.level().playSound(null, mob.blockPosition(), SoundEvents.PIGLIN_CELEBRATE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                startedRunning = true;
            }

            mob.getNavigation().moveTo(targetPlayer, speed);
        }
    }
}
