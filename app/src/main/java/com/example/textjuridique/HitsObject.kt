package com.example.textjuridique

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



class HitsObject {
    @SerializedName("hits")
    @Expose
    var hits: HitsList? = null
}