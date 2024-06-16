package com.example.retrofitcompose.Data

import com.example.retrofitcompose.Network.BookService


//5.
interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): List<Book>
}

class NetworkBooksRepository(private val booksRepostiry: BookService): BooksRepository {

    override suspend fun getBooks(query: String, maxResults: Int): List<Book> = booksRepostiry.bookSearch(query, maxResults).items.map { items -> //переопределяем функцию интерфейса, которая получает ответ от сервера и передает его в класс Book в виде списка. После берем переменную booksRepostiry, через нее добераемся до функции bookSearch, в параметры передаем аргументы из suspend fun getBooks. items - поле в классе Books, с списком елементов из класса Item и через map обращаемся к каждому елементу списка
        Book(
            title = items.volumeInfo?.title,
            previewLink = items.volumeInfo?.previewLink,
            imageLink = items.volumeInfo?.imageLinks?.thumbnail
        )
    }
}