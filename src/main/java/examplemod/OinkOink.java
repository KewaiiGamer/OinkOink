package examplemod;

import examplemod.examples.*;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.modifiers.ModifierValue;
import necesse.engine.registries.*;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.item.Item;
import necesse.inventory.item.placeableItem.consumableItem.food.FoodConsumableItem;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.level.maps.biomes.Biome;
import necesse.level.maps.levelData.settlementData.settler.Settler;

import static necesse.gfx.gameTexture.GameTexture.fromFile;

@ModEntry
public class OinkOink {
    public static GameTexture pig;
    public static GameTexture piglet;

    public void init() {
        System.out.println("Hello world from my example mod!");

        ItemRegistry.registerItem("rawporkchop", new FoodConsumableItem(100, Item.Rarity.NORMAL, Settler.FOOD_SIMPLE, 10, 240, new ModifierValue(BuffModifiers.MAX_HEALTH_FLAT, -10)).debuff().addGlobalIngredient("anyrawmeat").setItemCategory("consumable", "rawfood"), 4.0F, true);
        ItemRegistry.registerItem("cookedporkchop", new FoodConsumableItem(100, Item.Rarity.NORMAL, Settler.FOOD_FINE, 25, 240, new ModifierValue(BuffModifiers.ALL_DAMAGE, 0.02F)), 5.0F, true);
        // Register our mob
        MobRegistry.registerMob("pig", Pig.class, true);
    }

    public void initResources() {
        pig = fromFile("mobs/pig");
        piglet = fromFile("mobs/piglet");

    }

    public void postInit() {
        // Add recipes
        // Example item recipe, crafted in inventory for 2 iron bars
        Recipes.registerModRecipe(new Recipe(
                "cookedporkchop",
                1,
                RecipeTechRegistry.FORGE,
                new Ingredient[]{
                        new Ingredient("rawporkchop", 1)
                }
        ));

        // Add out example mob to default cave mobs.
        // Spawn tables use a ticket/weight system. In general, common mobs have about 100 tickets.
        Biome.defaultSurfaceMobs
                .add(100, "pig");
    }

}
