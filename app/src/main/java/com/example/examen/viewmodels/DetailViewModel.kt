package com.example.examen.viewmodels

import androidx.lifecycle.ViewModel
import com.example.examen.models.Artwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {

    private val _artwork = MutableStateFlow<Artwork?>(null)
    val artwork: StateFlow<Artwork?> = _artwork.asStateFlow()

    // Esta función la usamos para que el SharedViewModel le pase la obra de arte seleccionada
    fun setArtwork(artwork: Artwork) {
        _artwork.value = artwork
    }
}
