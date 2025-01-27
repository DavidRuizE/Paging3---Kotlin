package com.david.paging3implementation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.david.paging3implementation.R
import com.david.paging3implementation.presentation.model.CharacterModel

@Composable
fun RickListScreen(rickListViewModel: RickListViewModel = hiltViewModel()) {

    val characters = rickListViewModel.characters.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    when {
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = Color.White
                )
            }
        }

        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Text(text = "None characters yet")
        }

        characters.loadState.hasError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text("Ha ocurrido un error")
            }
        }

        else -> {
            CharactersLists(characters)

            if (characters.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        color = Color.White
                    )
                }
            }
        }
    }

}


@Composable
fun CharactersLists(characters: LazyPagingItems<CharacterModel>) {
    LazyColumn {
        items(characters.itemCount) {
            characters[it]?.let { characterModel ->
                ItemList(characterModel)
            }
        }
    }
}


@Composable
fun ItemList(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Green, shape = RoundedCornerShape(24))
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(characterModel.name, color = Color.White, fontSize = 18.sp)
        }
    }
}



