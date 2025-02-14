package com.example.retrofitcompose.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.retrofitcompose.R

//9. Экран ошибки


@Composable
fun ErrorScreen(retryAction: () -> Unit){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = stringResource(id = R.string.loading_failed))

        Button(onClick = {
            retryAction()
        }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}