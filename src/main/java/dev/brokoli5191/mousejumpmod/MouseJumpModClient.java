package dev.brokoli5191.mousejumpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;

public class MouseJumpModClient implements ClientModInitializer {

    public static boolean scrolledUp = false;
    public static boolean scrolledDown = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            if (c.player == null) return;
            KeyBinding jumpKey = c.options.jumpKey;
            if (scrolledUp || scrolledDown) {
                KeyBinding.setKeyPressed(jumpKey.getDefaultKey(), true);
                scrolledUp = false;
                scrolledDown = false;
            } else {
                KeyBinding.setKeyPressed(jumpKey.getDefaultKey(), false);
            }
        });
    }
}
