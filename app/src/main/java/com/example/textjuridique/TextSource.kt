package com.example.textjuridique

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class TextSource {
    @SerializedName("_source")
    @Expose
    var text: TexteItem? = null
}