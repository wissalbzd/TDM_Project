package com.example.textjuridique

import com.google.gson.annotations.SerializedName

data class Secteur(
    val NumSecteur: String?,
    @SerializedName("Secteur FR")
    val Secteur_FR: String?,
    @SerializedName("Secteur AR")
    val Secteur_AR: String?
)