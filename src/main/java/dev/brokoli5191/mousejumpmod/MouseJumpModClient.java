package dev.brokoli5191.mousejumpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;

public class MouseJumpModClient implements ClientModInitializer {

    public static boolean scrolledUp = false;
    public static boolean scrolledDown = false;
    private boolean scrollJumpActive = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            if (c.player == null) return;
            KeyBinding jumpKey = c.options.jumpKey;

            if (scrolledUp || scrolledDown) {
                if (c.player.isOnGround()) {
                    KeyBinding.setKeyPressed(jumpKey.getDefaultKey(), true);
                    scrollJumpActive = true;
                }
                scrolledUp = false;
                scrolledDown = false;
            } else if (scrollJumpActive) {
                // Release for exactly one tick after scroll jump.
                // setKeyPressed(false) only runs once (not every tick),
                // so Space held down is only briefly interrupted if at all.
                KeyBinding.setKeyPressed(jumpKey.getDefaultKey(), false);
                scrollJumpActive = false;
            }
        });
    }
}
