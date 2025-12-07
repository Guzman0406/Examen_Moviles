package com.example.examen.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.examen.ArtworkApplication
import com.example.examen.models.Artwork
import com.example.examen.repositories.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel(application: Application, private val artwork: Artwork) : AndroidViewModel(application) {

    private val repository: ArtworkRepository = (application as ArtworkApplication).artworkRepository

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        checkIfFavorite()
    }

    private fun checkIfFavorite() {
        viewModelScope.launch {
            repository.isFavorite(artwork.id).collectLatest {
                _isFavorite.value = it
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.removeFavorite(artwork.id)
            } else {
                repository.addFavorite(artwork)
            }
        }
    }
}

class DetailViewModelFactory(private val application: Application, private val artwork: Artwork) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(application, artwork) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
