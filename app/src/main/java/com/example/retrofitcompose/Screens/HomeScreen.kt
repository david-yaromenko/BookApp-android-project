package com.example.retrofitcompose.Screens

import androidx.compose.runtime.Composable
import com.example.retrofitcompose.BookViewModel.BooksUiState
import com.example.retrofitcompose.Data.Book


//11.Связывающий экран.


@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    onBookClick: (Book) -> Unit
) {
    when(booksUiState){
        is BooksUiState.Loading -> LoadingScreen()
        is BooksUiState.Error -> ErrorScreen(retryAction)
        is BooksUiState.Success -> BooksGridScreen(book = booksUiState.bookSearch, onBookClick)
    }
}