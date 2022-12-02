package com.bbc_reader.finalproject.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.bbc_reader.finalproject.model.Feed;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SessionUtils {
    private static String NAME = "store";
    public static String FEED = "feed";

    public static void set(Context context, String key, Object obj){

        SharedPreferences sp = context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(obj));
        editor.apply();
    }

    public static List<Feed> get(Context context, String key, String def){
        if(def == null) def = "[]";
        SharedPreferences sp = context.getSharedPreferences(NAME, MODE_PRIVATE);
        String val = sp.getString(key, def);
        Type type = new TypeToken<List<Feed>>(){}.getType();
        return new Gson().fromJson(val, type);
    }

    public static void remove(Context context, String key){

        SharedPreferences sp = context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }
}
