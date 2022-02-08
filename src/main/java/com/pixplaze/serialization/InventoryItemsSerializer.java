package com.pixplaze.serialization;

import com.google.gson.*;
import com.pixplaze.util.Players;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;

public class InventoryItemsSerializer implements JsonSerializer<ItemStack[]> {
    @Override
    public JsonElement serialize(ItemStack[] src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Players.itemStackArrayToBase64(src));
    }
}
