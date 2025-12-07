package com.example.examen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.data.RetrofitClient
import com.example.examen.models.Artwork
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _artworks = MutableStateFlow<List<Artwork>>(emptyList())
    val artworks: StateFlow<List<Artwork>> = _artworks.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        _searchQuery
            .debounce(500)
            .onEach { query ->
                if (query.isNotBlank()) {
                    searchArtworks(query)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun searchArtworks(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.instance.searchArtworks(query)
                val iiifUrl = response.config.iiifUrl
                _artworks.value = response.data.map { artwork ->
                    artwork.copy(imageId = "${iiifUrl}/${artwork.imageId}/full/843,/0/default.jpg")
                }
            } catch (e: Exception) {
                _artworks.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}
