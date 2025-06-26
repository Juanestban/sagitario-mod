package com.sagitario_zoomod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SagitarioZoomMod implements ClientModInitializer {
  private static final MinecraftClient client = MinecraftClient.getInstance();
  private static KeyBinding zoomKey;
  private static int originalFov = 0;

  @Override
  public void onInitializeClient() {
    zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.sagitariozoomod.zoom",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_Z,
        "category.sagitario.zoom"));

    originalFov = client.options.getFov().getValue();
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (zoomKey.isPressed()) {
        client.options.getFov().setValue(30);
      } else {
        client.options.getFov().setValue(originalFov);
      }
    });
  }
}
