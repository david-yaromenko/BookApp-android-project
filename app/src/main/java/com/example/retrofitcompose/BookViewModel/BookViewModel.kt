package com.example.retrofitcompose.BookViewModel

import android.app.Application
import android.view.LayoutInflater.Factory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitcompose.Data.Book
import com.example.retrofitcompose.Data.BookApplication
import com.example.retrofitcompose.Data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException


//7.

sealed interface BooksUiState{
    data class Success(val bookSearch: List<Book>): BooksUiState
    object Error: BooksUiState
    object Loading: BooksUiState

}

class BookViewModel(
    private val booksRepository: BooksRepository
): ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set


    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState


    private val _searchTextState: MutableState<String> =
        mutableStateOf("")
    val searchTextState: State<String> = _searchTextState


    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String){
        _searchTextState.value = newValue
    }


    init {
        getBooks("book")
    }


    fun getBooks(query: String, maxResult: Int = 40){
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                BooksUiState.Success(booksRepository.getBooks(query, maxResult))
            } catch (e: IOException){
                BooksUiState.Error
            } catch (e: HttpException){
                BooksUiState.Error
            }

        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = application.container.booksRepository
                BookViewModel(booksRepository = bookRepository)
            }
        }
    }
}

enum class SearchWidgetState{
    OPENED,
    CLOSED
}