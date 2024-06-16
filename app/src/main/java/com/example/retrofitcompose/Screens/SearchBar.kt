package com.example.retrofitcompose.Screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.retrofitcompose.BookViewModel.SearchWidgetState


//13.


@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClick: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            CloseAppBar(onSearchClick = onSearchTriggered)
        }

        SearchWidgetState.OPENED -> {
            OpenAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClick = onSearchClick)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseAppBar(onSearchClick: () -> Unit) {

    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
        Text(text = "BooksStore", color = Color.White)
    },
        actions = {
            IconButton(
                onClick = { onSearchClick() }
            )
            {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "search",
                    tint = Color.White
                )
            }
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        tonalElevation = 10.dp,
        color = MaterialTheme.colorScheme.primary
    )
    {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = "Search...", color = Color.White,
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(0.5f),
                    onClick = {
                        onSearchClick(text)
                    })
                {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search",
                        tint = Color.White
                    )

                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    })
                {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(text)
                }
            ),
            colors = textFieldColors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                cursorColor = Color.White.copy(alpha = 0.5f),
                unfocusedIndicatorColor = Color.LightGray
            )
        )
    }
}

















