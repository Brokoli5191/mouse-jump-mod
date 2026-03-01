package dev.brokoli5191.mousejumpmod.mixin;

import dev.brokoli5191.mousejumpmod.MouseJumpModClient;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseScrollMixin {

    @Inject(method = "onScroll", at = @At("HEAD"))
    private void onScroll(long window, double xOffset, double yOffset, CallbackInfo ci) {
        if (yOffset > 0) MouseJumpModClient.scrolledUp = true;
        else if (yOffset < 0) MouseJumpModClient.scrolledDown = true;
    }
}
