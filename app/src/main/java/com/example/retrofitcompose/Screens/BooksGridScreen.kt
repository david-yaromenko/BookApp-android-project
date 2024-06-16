package com.example.retrofitcompose.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.retrofitcompose.Data.Book
import com.example.retrofitcompose.R
import com.example.retrofitcompose.ui.theme.card

//10. Экран где будет отображаться список книг



@Composable
fun BooksGridScreen(book: List<Book>, onBookClick: (Book) -> Unit){

    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp))
    {
        itemsIndexed(book){_, book ->

            BoxCard(book = book, onBookClick)

        }
    }
}


@Composable
fun BoxCard(book: Book,
            onBookClick: (Book) -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(296.dp)
        .clickable { onBookClick(book) },
        elevation = CardDefaults.cardElevation(8.dp),
        )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {

            Text(text = book.title!!, textAlign = TextAlign.Center, maxLines = 1, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.imageLink?.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_book_96),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop)
        }
    }
}