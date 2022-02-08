package com.pixplaze.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pixplaze.plugin.suspicion.Suspector;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;

import static com.pixplaze.util.Common.nullable;

public class SuspectorDeserializer implements JsonDeserializer<Suspector> {
    @Override
    public Suspector deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        var jsonSuspector = json.getAsJsonObject();

        var jsonUsername = jsonSuspector.get("username");
        var jsonGameMode = jsonSuspector.get("lastGameMode");
        var jsonInfo = jsonSuspector.get("info");
        var jsonInventoryItems = jsonSuspector.get("inventoryItems");

        var username = nullable(jsonUsername, JsonElement::getAsString);
        var gameMode = (GameMode) context.deserialize(jsonGameMode, GameMode.class);
        var inventoryItems = (ItemStack[]) context.deserialize(jsonInventoryItems, ItemStack[].class);
        var info = (Suspector.SuspicionInfo) context.deserialize(jsonInfo, Suspector.SuspicionInfo.class);

        return new Suspector(username, gameMode, inventoryItems, info);
    }
}
