package com.example.examen.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.examen.models.Artwork

@Composable
fun DetailScreen(artwork: Artwork) {
    Scaffold {
 paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Usar el padding del Scaffold
                .padding(16.dp)
        ) {
            AsyncImage(
                model = artwork.imageId,
                contentDescription = artwork.thumbnail?.altText ?: artwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = artwork.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = artwork.artistDisplay ?: "Artista Desconocido", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = artwork.thumbnail?.altText ?: "Sin descripción.")
        }
    }
}
