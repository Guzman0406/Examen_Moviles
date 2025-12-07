package com.example.examen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.example.examen.viewmodels.FavoritesViewModel
import com.example.examen.viewmodels.SharedViewModel

@Composable
fun FavoritesScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val viewModel: FavoritesViewModel = viewModel()

    val favoriteArtworks by viewModel.favoriteArtworks.collectAsState()

    if (favoriteArtworks.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No tienes obras de arte favoritas.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteArtworks, key = { it.id }) { artwork ->
                Box {
                    ArtworkListItem(artwork = artwork) {
                        sharedViewModel.selectArtwork(artwork)
                        navController.navigate(AppScreen.DetailScreen.route)
                    }
                    IconButton(
                        onClick = { viewModel.removeFavorite(artwork.id) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar de favoritos")
                    }
                }
            }
        }
    }
}
