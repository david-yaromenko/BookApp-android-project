package com.example.retrofitcompose.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitcompose.BookViewModel.BookViewModel
import com.example.retrofitcompose.BookViewModel.SearchWidgetState
import com.example.retrofitcompose.Data.Book


//12.


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BooksApp(onBookClick: (Book) -> Unit) {
    val booksViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)


    val searchWidgetState = booksViewModel.searchWidgetState
    val searchTextState = booksViewModel.searchTextState

    Scaffold(
        topBar = { 
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = {
                               booksViewModel.updateSearchTextState(it)
                },
                onCloseClicked = {
                                 booksViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                },
                onSearchClick = {
                    booksViewModel.getBooks(it)
                },
                onSearchTriggered = {
                    booksViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                }
            )
        }
    )
    {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it), color = MaterialTheme.colorScheme.primary)
        {
            HomeScreen(booksUiState = booksViewModel.booksUiState, retryAction = {booksViewModel.getBooks("book")}, onBookClick)
        }
    }
}