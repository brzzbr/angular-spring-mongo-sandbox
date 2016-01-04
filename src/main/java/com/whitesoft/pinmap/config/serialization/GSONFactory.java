package com.whitesoft.pinmap.config.serialization;

import com.google.gson.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by borisbondarenko on 27.12.15.
 * <p/>
 * Factory for GSON (de)serializer. It has some specifics like
 * type adapter for UNIX-date serialization and GeoJsonPoint serialization.
 *
 * @author brzzbr
 */
public class GSONFactory {

    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();

        builder
                .setPrettyPrinting()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .registerTypeAdapter(GeoJsonPoint.class, new GeoJsonPointTypeAdapter());

        gson = builder.create();
    }

    public static Gson getGson() {
        return gson;
    }

    /**
     * class for custom serialization of Date class. I prefer a date to be transfered in UNIX timestamp
     */
    private static class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            return new Date(json.getAsJsonPrimitive().getAsLong());
        }

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

            return new JsonPrimitive(src.getTime());
        }
    }

    /**
     * custom type adapter for {@link GeoJsonPoint}. It has some issues due to the fact it has no default ctor.
     */
    private static class GeoJsonPointTypeAdapter implements JsonDeserializer<GeoJsonPoint> {

        @Override
        public GeoJsonPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            double x = json.getAsJsonObject().get("x").getAsDouble();
            double y = json.getAsJsonObject().get("y").getAsDouble();

            return new GeoJsonPoint(x , y);
        }
    }
}