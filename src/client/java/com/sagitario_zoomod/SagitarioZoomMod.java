package com.sagitario_zoomod;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

import org.lwjgl.glfw.GLFW;

public class SagitarioZoomMod implements ClientModInitializer {
  private static KeyBinding zoomKey;
  private static int originalFov = 70;
  private static boolean isFirstRender = true;

  @Override
  public void onInitializeClient() {
    zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.sagitariozoomod.zoom",
        GLFW.GLFW_KEY_Z,
        KeyBinding.Category.create(Identifier.ofVanilla("category.sagitario.zoom"))));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (isFirstRender) {
        originalFov = client.options.getFov().getValue();
        isFirstRender = false;
      }

      if (zoomKey.isPressed()) {
        client.options.getFov().setValue(30);
      } else {
        client.options.getFov().setValue(originalFov);
      }
    });
  }
}
