package com.pixplaze.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pixplaze.plugin.suspicion.Suspector;

import java.lang.reflect.Type;

import static com.pixplaze.util.Common.notnull;

public class SuspicionInfoSerializer implements JsonSerializer<Suspector.SuspicionInfo> {
    @Override
    public JsonElement serialize(Suspector.SuspicionInfo src, Type typeOfSrc, JsonSerializationContext context) {
        var info = new JsonObject();
        notnull(src.reason, s -> info.addProperty("reason", s));
        notnull(src.timestamp, s -> info.addProperty("timestamp", s));
        return info;
    }
}
