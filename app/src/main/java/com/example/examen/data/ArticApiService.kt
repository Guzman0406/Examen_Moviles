package com.example.examen.data

import com.example.examen.models.Artwork
import com.example.examen.models.ArtworksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticApiService {

    // Busca obras de arte y pide solo los campos que necesitamos para optimizar la respuesta
    @GET("artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String,
        @Query("fields") fields: String = "id,title,image_id,artist_display,thumbnail"
    ): ArtworksResponse

    // Obtiene los detalles de una sola obra de arte
    @GET("artworks/{id}")
    suspend fun getArtworkDetails(
        @Path("id") artworkId: Int
    ): Artwork
}
