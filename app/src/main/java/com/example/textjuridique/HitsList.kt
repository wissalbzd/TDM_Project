package com.example.textjuridique

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class HitsList {
    @SerializedName("hits")
    @Expose
    var postIndex: List<TextSource>? = null
}