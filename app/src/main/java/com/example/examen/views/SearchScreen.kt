package com.example.examen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.examen.components.ArtworkListItem
import com.example.examen.navigation.AppScreen
import com.example.examen.viewmodels.SearchViewModel
import com.example.examen.viewmodels.SharedViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    searchViewModel: SearchViewModel = viewModel()
) {
    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val artworks by searchViewModel.artworks.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchViewModel.onSearchQueryChange(it) },
            label = { Text("Buscar obras de arte...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(artworks) { artwork ->
                    ArtworkListItem(artwork = artwork) {
                        sharedViewModel.selectArtwork(artwork)
                        navController.navigate(AppScreen.DetailScreen.route)
                    }
                }
            }
        }
    }
}
