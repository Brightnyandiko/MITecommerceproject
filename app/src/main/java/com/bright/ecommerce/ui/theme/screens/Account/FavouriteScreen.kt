package com.bright.ecommerce.ui.theme.screens.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavouriteScreen(productViewmodel: ProductViewmodel,onnext:(Product)->Unit){

    when (val result = productViewmodel.favresponse.value ) {

        is ProductSealed.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                CircularProgressIndicator()

            }


        }

        is ProductSealed.Success -> {

            Favourite(
                productViewmodel = productViewmodel,
                favouritelist = result.data,
                onnext = {onnext(it)}
            )

        }

        else -> {


            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                Text(text = "Error Fetching data!")


            }


        }


    }



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favourite(productViewmodel: ProductViewmodel,favouritelist:MutableList<Product>,onnext: (Product) -> Unit){

    Scaffold(topBar = { FavTopBar() }, modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary).padding(horizontal = 16.dp, vertical = 8.dp)) { paddingValues ->

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary).padding(paddingValues)){

            items(favouritelist){

                Item(
                    productViewmodel = productViewmodel,
                    product = it,
                    onclick = { onnext(it) },
                    isFavourite = true,
                    iscart = true
                )

            }
        }

    }
}


@Composable
fun FavTopBar() {


    Row(
        Modifier
            .fillMaxWidth().background(MaterialTheme.colors.primary).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Favourite",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colors.primaryVariant
        )


    }

}