package com.peak.renegade.game.content.item;

import com.peak.renegade.core.index.RenegadeComponentTypes;
import com.peak.renegade.game.content.WeaponUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class KnifeItem extends Item {
    public KnifeItem(Settings settings) {
        super(settings.component(RenegadeComponentTypes.WEAPON_VARIATION, 0));
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            WeaponUtils.setNextVariant(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
}
