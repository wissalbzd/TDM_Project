package com.example.textjuridique

import com.google.gson.annotations.SerializedName

data class TexteItem(
    val NumSGG: String,
    val NumJO: String,
    @SerializedName("Type Texte FR")
    val TypeTexteFR: String,
    @SerializedName("Type Texte AR")
    val TypeTexteAR: String,
    @SerializedName("Page FR")
    val PageFR: String,
    @SerializedName("Page AR")
    val PageAR: String,
    @SerializedName("Date publication FR")
    val DatepublicationFR: String,
    @SerializedName("Date publication AR")
    val DatePublicationAR: String,
    @SerializedName("Date Signature FR")
    val DateSignatureFR: String,
    @SerializedName("Date Signature AR")
    val DateSignatureAR: String,
    @SerializedName("Sommaire FR")
    val SommaireFR: String,
    @SerializedName("Sommaire AR")
    val SommaireAR: String,
    @SerializedName("ATexte")
    val ATexte: String,
    @SerializedName("FTexte")
    val FTexte: String,
    @SerializedName("Organe FR")
    val OrganeFR: String,
    @SerializedName("Organe AR")
    val OrganeAR: String,
    @SerializedName("AnneeJO")
    val AnneeJO: String,
    @SerializedName("F_PDFFileName")
    val F_PDFFileName: String,
    @SerializedName("A_PDFFileName")
    val A_PDFFileName: String,
    @SerializedName("FR_Keywords")
    val FR_Keywords: List<FRKeyword>,
    @SerializedName("AR_Keywords")
    val AR_Keywords: List<ARKeyword>,
    @SerializedName("Secteurs")
    val Secteurs: List<Secteur>,
    //@SerializedName("AR_Liens")
    //val AR_Liens: List<String>
)