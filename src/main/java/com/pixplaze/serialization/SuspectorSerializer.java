package com.pixplaze.serialization;

import com.google.gson.*;
import com.pixplaze.plugin.suspicion.Suspector;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;

import static com.pixplaze.util.Common.notnull;

public class SuspectorSerializer implements JsonSerializer<Suspector> {
    @Override
    public JsonElement serialize(Suspector src, Type typeOfSrc, JsonSerializationContext context) {
        var suspector = new JsonObject();

        notnull(src.username, s -> suspector.addProperty("username", s));
        notnull(src.lastGameMode, s -> suspector.addProperty("lastGameMode", s.name()));
        //notnull(src.inventoryItems, s -> suspector.add("inventoryItems", context.serialize(s, ItemStack[].class)));
        notnull(src.info, s -> suspector.add("info", context.serialize(s)));

        return suspector;
    }
}

