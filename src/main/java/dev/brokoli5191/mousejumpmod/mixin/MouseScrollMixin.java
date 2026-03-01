package dev.brokoli5191.mousejumpmod.mixin;

import dev.brokoli5191.mousejumpmod.MouseJumpModClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseScrollMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"))
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (vertical > 0) MouseJumpModClient.scrolledUp = true;
        else if (vertical < 0) MouseJumpModClient.scrolledDown = true;
    }
}
