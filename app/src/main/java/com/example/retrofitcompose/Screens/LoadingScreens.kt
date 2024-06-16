package com.example.retrofitcompose.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.retrofitcompose.R

//8.экран загрузки


@Composable
fun LoadingScreen(){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
    {
        Image( modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.loading_img), contentDescription = stringResource(
            id = R.string.loading
        ))
    }
}