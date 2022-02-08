package com.pixplaze.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pixplaze.plugin.suspicion.Suspector;
import java.lang.reflect.Type;
import static com.pixplaze.util.Common.nullable;

public class SuspicionInfoDeserializer implements JsonDeserializer<Suspector.SuspicionInfo> {
    @Override
    public Suspector.SuspicionInfo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        var jsonInfo = json.getAsJsonObject();
        var reason = nullable(jsonInfo.get("reason"), JsonElement::getAsString);
        var timestamp = nullable(jsonInfo.get("timestamp"), JsonElement::getAsLong);
        return new Suspector.SuspicionInfo(reason, timestamp);
    }
}