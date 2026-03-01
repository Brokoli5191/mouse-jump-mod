package dev.brokoli5191.mousejumpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;

public class MouseJumpModClient implements ClientModInitializer {

    public static boolean scrolledUp = false;
    public static boolean scrolledDown = false;
    private boolean scrollJumpActive = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            if (c.player == null) return;
            KeyMapping jumpKey = c.options.keyJump;

            if (scrolledUp || scrolledDown) {
                if (c.player.isOnGround()) {
                    KeyMapping.set(jumpKey.getDefaultKey(), true);
                    scrollJumpActive = true;
                }
                scrolledUp = false;
                scrolledDown = false;
            } else if (scrollJumpActive) {
                KeyMapping.set(jumpKey.getDefaultKey(), false);
                scrollJumpActive = false;
            }
        });
    }
}
