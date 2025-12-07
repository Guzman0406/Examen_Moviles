package com.example.examen.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.examen.models.Artwork
import com.example.examen.repositories.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {

    private val _artwork = MutableStateFlow<Artwork?>(null)
    val artwork: StateFlow<Artwork?> = _artwork.asStateFlow()

    // Usamos un State de Compose para el corazón, para que la UI se actualice al instante
    var isFavorite = mutableStateOf(false)
        private set

    fun setArtwork(artwork: Artwork) {
        _artwork.value = artwork
        isFavorite.value = FavoritesRepository.isFavorite(artwork.id)
    }

    fun toggleFavorite() {
        _artwork.value?.let { artwork ->
            if (isFavorite.value) {
                FavoritesRepository.removeFavorite(artwork.id)
            } else {
                FavoritesRepository.addFavorite(artwork)
            }
            isFavorite.value = !isFavorite.value
        }
    }
}
