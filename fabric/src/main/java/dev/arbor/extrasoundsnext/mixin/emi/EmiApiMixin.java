package dev.arbor.extrasoundsnext.mixin.emi;

import dev.arbor.extrasoundsnext.sounds.ScrollSound;
import dev.arbor.extrasoundsnext.sounds.SoundManager;
import dev.arbor.extrasoundsnext.sounds.SoundType;
import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(EmiApi.class)
@Environment(EnvType.CLIENT)
public class EmiApiMixin {
    @Unique
    private static final ScrollSound extra_sounds$scrollSound = new ScrollSound();

    @Inject(method = "setPages", at = @At("HEAD"), remap = false)
    private static void setPages(Map<EmiRecipeCategory, List<EmiRecipe>> recipes, EmiIngredient stack, CallbackInfo ci) {
        ItemStack itemStack = stack.getEmiStacks().get(0).getItemStack();
        SoundManager.playSound(itemStack, SoundType.PICKUP);
    }
}
