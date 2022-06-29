package com.example.textjuridique

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type



class ConfigPref {
    companion object {
        val List_KEY = "list_key"
        public fun writeDataInPref(context: Context, list: List<TexteItem>) {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            var editor = pref.edit()
            var gson = Gson()
            var json = gson.toJson(list)
            editor.putString(List_KEY, json)
            editor.apply()
            editor.commit()
        }

        public fun readDataFromPref(context: Context): ArrayList<TexteItem> {
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = sharedPrefs.getString(List_KEY, "")
            val type: Type = object : TypeToken<List<TexteItem?>?>() {}.type
            var arrayList: List<TexteItem> = gson.fromJson<List<TexteItem>>(json, type)
            return arrayList as ArrayList<TexteItem>
        }

    }
}
class ConfigPrefItem{
    companion object {
        val List_KEY = "item_key"
        public fun writeDataInPref(context: Context, item: TexteItem) {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            var editor = pref.edit()
            var gson = Gson()
            var json = gson.toJson(item)
            editor.putString(List_KEY, json)
            editor.apply()
            editor.commit()
        }

        public fun readDataFromPref(context: Context): TexteItem {
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = sharedPrefs.getString(List_KEY, "")
            val type: Type = object : TypeToken<List<TexteItem?>?>() {}.type
            val item: TexteItem= gson.fromJson<TexteItem>(json, type)
            return item
        }

    }
}
/*
class ConfigPrefFavorie {
    companion object {
        val List_KEY = "listFAV_key"
        public fun writeDataInPref(context: Context, list: List<TexteItem>) {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            var editor = pref.edit()
            var gson = Gson()
            var json = gson.toJson(list)
            editor.putString(List_KEY, json)
            editor.apply()
            editor.commit()
        }

        public fun readDataFromPref(context: Context): ArrayList<TexteItem> {
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = sharedPrefs.getString(List_KEY, "")
            val type: Type = object : TypeToken<List<TexteItem?>?>() {}.type
            val arrayList: List<TexteItem> = gson.fromJson<List<TexteItem>>(json, type)
            return arrayList as ArrayList<TexteItem>
        }

    }
}*/