package com.example.examen.views

import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.examen.ArtworkApplication
import com.example.examen.components.ArtworkListItem
import com.example.examen.models.Artwork
import com.example.examen.models.Thumbnail
import com.example.examen.navigation.AppScreen
import com.example.examen.viewmodels.FavoritesViewModel
import com.example.examen.viewmodels.SharedViewModel

@Composable
fun FavoritesScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.FavoritesViewModelFactory(application))

    val favoriteArtworks by viewModel.favoriteArtworks.collectAsState(initial = emptyList())

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
            items(favoriteArtworks, key = { it.id }) { artworkEntity ->
                Box {
                    val artwork = Artwork(
                        id = artworkEntity.id,
                        title = artworkEntity.title,
                        imageId = artworkEntity.imageId,
                        artistDisplay = artworkEntity.artistDisplay,
                        thumbnail = Thumbnail(altText = artworkEntity.thumbnailAltText)
                    )
                    ArtworkListItem(artwork = artwork) {
                        sharedViewModel.selectArtwork(artwork)
                        navController.navigate(AppScreen.DetailScreen.route)
                    }
                    IconButton(
                        onClick = { viewModel.removeFavorite(artworkEntity.id) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar de favoritos")
                    }
                }
            }
        }
    }
}
