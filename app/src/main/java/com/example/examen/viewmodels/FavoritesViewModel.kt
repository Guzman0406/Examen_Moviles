package com.example.examen.viewmodels

import androidx.lifecycle.ViewModel
import com.example.examen.models.Artwork
import com.example.examen.repositories.FavoritesRepository
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel() {

    val favoriteArtworks: StateFlow<List<Artwork>> = FavoritesRepository.favoriteArtworks

    fun removeFavorite(artworkId: Int) {
        FavoritesRepository.removeFavorite(artworkId)
    }
}
