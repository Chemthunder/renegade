package com.peak.renegade.game.content;

import com.peak.renegade.core.index.RenegadeComponentTypes;
import net.minecraft.item.ItemStack;

/**
 * @author Chemthunder
 */
public class WeaponUtils {

    public static void setNextVariant(ItemStack stack) {
        int current = stack.getOrDefault(RenegadeComponentTypes.WEAPON_VARIATION, 0);
        if (current > 2) {
            stack.set(RenegadeComponentTypes.WEAPON_VARIATION, current++);
        } else {
            stack.set(RenegadeComponentTypes.WEAPON_VARIATION, 0);
        }
    }
}
