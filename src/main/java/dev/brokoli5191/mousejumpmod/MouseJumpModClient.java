package dev.brokoli5191.mousejumpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

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
                // Only release if Space is not physically held
                boolean spaceHeld = InputUtil.isKeyPressed(
                    c.getWindow().getHandle(),
                    jumpKey.getDefaultKey().getCode()
                );
                if (!spaceHeld) {
                    KeyBinding.setKeyPressed(jumpKey.getDefaultKey(), false);
                }
                scrollJumpActive = false;
            }
        });
    }
}
