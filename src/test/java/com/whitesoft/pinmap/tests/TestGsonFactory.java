package com.whitesoft.pinmap.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

/**
 * Created by borisbondarenko on 25.12.15.
 *
 * Factory for GSON (de)serializer. It has some specifics like
 * type adapter for UNIX-date serialization.
 *
 * @author brzzbr
 */
public final class TestGsonFactory {

    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

        gson = builder.create();
    }

    public static Gson getGson() {
        return gson;
    }
}
