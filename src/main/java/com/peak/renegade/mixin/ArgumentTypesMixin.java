package com.peak.renegade.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import com.peak.renegade.core.utility.command.LayerArgumentType;
import com.peak.renegade.core.utility.command.LevelArgumentType;
import com.peak.renegade.core.utility.command.PlayerStateArgumentType;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AcoYT
 */
@Mixin(ArgumentTypes.class)
public abstract class ArgumentTypesMixin {
    @Shadow
    private static <A extends ArgumentType<?>, T extends ArgumentSerializer.ArgumentTypeProperties<A>> ArgumentSerializer<A, T> register(Registry<ArgumentSerializer<?, ?>> registry, String id, Class<? extends A> clazz, ArgumentSerializer<A, T> serializer) {
        throw new AssertionError();
    }

    @Inject(method = "register(Lnet/minecraft/registry/Registry;)Lnet/minecraft/command/argument/serialize/ArgumentSerializer;", at = @At("TAIL"))
    private static void renegade$addArguments(Registry<ArgumentSerializer<?, ?>> registry, CallbackInfoReturnable<ArgumentSerializer<?, ?>> cir) {
        register(registry, "renegade:layer", LayerArgumentType.class, ConstantArgumentSerializer.of(LayerArgumentType::layer));
        register(registry, "renegade:level", LevelArgumentType.class, ConstantArgumentSerializer.of(LevelArgumentType::level));

        register(registry, "renegade:playerstate", PlayerStateArgumentType.class, ConstantArgumentSerializer.of(PlayerStateArgumentType::state));
    }
}