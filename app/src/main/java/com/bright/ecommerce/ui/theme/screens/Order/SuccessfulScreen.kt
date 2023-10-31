package com.bright.ecommerce.ui.theme.screens.Order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SuccessScreen(){


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        Column {

            Image(imageVector = Icons.Default.Done , contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Successful", fontSize = 16.sp, color = Color.DarkGray)
        }


    }


}