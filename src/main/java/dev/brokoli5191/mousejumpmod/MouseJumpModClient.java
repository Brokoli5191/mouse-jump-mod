package dev.brokoli5191.mousejumpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;

public class MouseJumpModClient implements ClientModInitializer {

    public static boolean scrollQueued = false;
    private boolean scrollJumpActive = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            KeyMapping jumpKey = c.options.keyJump;

            if (c.player == null || c.gui.screen() != null) {
                scrollQueued = false;
                releaseScrollJump(jumpKey);
                return;
            }

            if (scrollQueued) {
                if (c.player.onGround()) {
                    jumpKey.setDown(true);
                    scrollJumpActive = true;
                }
                scrollQueued = false;
            } else if (scrollJumpActive) {
                releaseScrollJump(jumpKey);
            }
        });
    }

    private void releaseScrollJump(KeyMapping jumpKey) {
        if (scrollJumpActive) {
            jumpKey.setDown(false);
            scrollJumpActive = false;
        }
    }
}
