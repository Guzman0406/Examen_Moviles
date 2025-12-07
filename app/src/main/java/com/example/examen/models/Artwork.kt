package com.example.examen.models

import com.google.gson.annotations.SerializedName

// Modelo para la respuesta de la API que contiene una lista de obras de arte
data class ArtworksResponse(
    val data: List<Artwork>,
    val config: ApiConfig
)

// Modelo para una única obra de arte
data class Artwork(
    val id: Int,
    val title: String,
    @SerializedName("image_id") val imageId: String?,
    @SerializedName("artist_display") val artistDisplay: String?,
    val thumbnail: Thumbnail?
)

// Modelo para la miniatura de la obra, que contiene el texto alternativo
data class Thumbnail(
    @SerializedName("alt_text") val altText: String?
)

// Modelo para la configuración de la API, necesaria para construir la URL de la imagen
data class ApiConfig(
    @SerializedName("iiif_url") val iiifUrl: String
)
