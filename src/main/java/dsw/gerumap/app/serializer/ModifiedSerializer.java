package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.repository.composite.MapNode;

import java.lang.reflect.Type;

public class ModifiedSerializer implements JsonSerializer<MapNode>, JsonDeserializer<MapNode> {

    @Override
    public JsonElement serialize(MapNode src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public MapNode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("dsw.gerumap.app.repository.implementation." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

}
