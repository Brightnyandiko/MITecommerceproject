package com.bright.ecommerce.ui.theme.screens.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import com.bright.ecommerce.Data.ProductViewmodel
import com.bright.ecommerce.Model.Cartitem
import com.bright.ecommerce.Model.Product
import com.bright.ecommerce.Sealed.Cartsealed
import com.bright.ecommerce.Sealed.ProductSealed
import com.bright.ecommerce.ui.theme.screens.Home.SimilarHomeProduct
import com.bright.ecommerce.ui.theme.screens.Home.iscartcontainslist

@Composable
fun SearchBar(productViewmodel: ProductViewmodel,ondiscard:()->Unit,onnext: (Product) -> Unit) {

    when (val result = productViewmodel.response.value) {

        is ProductSealed.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                CircularProgressIndicator(color = MaterialTheme.colors.secondary)

            }


        }

        is ProductSealed.Success -> {


            when (val favresponse = productViewmodel.favresponse.value) {

                is ProductSealed.Loading -> {

                    CircularProgressIndicator()
                }

                is ProductSealed.Success -> {


                    when (val cartdata = productViewmodel.readcard.value) {

                        is Cartsealed.Loading -> {

                        }

                        is Cartsealed.Success -> {


                            SeacrhScreen(productViewmodel = productViewmodel, list = result.data ,favresponse.data,cartdata.data,ondiscard,onnext,productViewmodel.searchlist.value)

                        }
                        else -> {


                        }
                    }

                }
                else -> {

                    Text(text = "Error Fetching data!")
                }
            }

        }

        else -> {


            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                Text(text = "Error Fetching data!")


            }


        }


    }

}

@Composable
fun Search(productViewmodel: ProductViewmodel, list: MutableList<Product>, ondiscard: () -> Unit) {


    var query: String by rememberSaveable { mutableStateOf("") }




    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    if (query.isEmpty()) {
        showClearIcon = false
        productViewmodel.filter("",list)
    } else if (query.isNotEmpty()) {
        showClearIcon = true
    }


    Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.clickable { ondiscard() }, tint = MaterialTheme.colors.primaryVariant)

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = query,
            onValueChange = { onQueryChanged ->
                query = onQueryChanged

                if (onQueryChanged.isNotEmpty()) {

                    productViewmodel.filter(query,list)

                } else {

                    productViewmodel.filter(query,list)
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {

                if (showClearIcon) {
                    IconButton(onClick = {
                        query = ""

                        if (query.isEmpty()) {

                            productViewmodel.filter(query,list)
                        }



                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            tint = MaterialTheme.colors.primaryVariant,
                            contentDescription = "Clear icon"
                        )
                    }
                }
            },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colors.primaryVariant,
                cursorColor = MaterialTheme.colors.primaryVariant,
                focusedIndicatorColor = MaterialTheme.colors.primaryVariant,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Search") },
            textStyle = MaterialTheme.typography.body2,
            singleLine = true,

            keyboardOptions = KeyboardOptions(keyboardType = Text),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .border(width = 1.dp, color = MaterialTheme.colors.primaryVariant, shape = CircleShape)


        )
    }


}




@Composable
fun SeacrhScreen(
    productViewmodel: ProductViewmodel,
    list: MutableList<Product>,
    favouritelist: MutableList<Product>,
    cartlist: MutableList<Cartitem>,
    ondiscard: () -> Unit,
    onnext: (Product) -> Unit,
    value: List<Product>
) {

    Scaffold(
        topBar = { Search(productViewmodel,list,ondiscard) },
        modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colors.primary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {


        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary).padding(it)){



            items(value){product->

                val isFav: Boolean = favouritelist.contains(product)
                val iscart :Boolean = iscartcontainslist(cartlist,product)

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)) {

                    SimilarHomeProduct(product = product, isFav = isFav, iscart = iscart, productViewmodel = productViewmodel) {

                        onnext(product)

                    }
                }
            }




        }

    }
}








@Composable
fun TextSeacrhBar(onnext:()->Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(color = MaterialTheme.colors.primaryVariant, width = 1.dp, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onnext() }
        , verticalAlignment = Alignment.CenterVertically
    ) {


        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.primaryVariant)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Search",
            fontStyle = MaterialTheme.typography.body2.fontStyle,
            color= MaterialTheme.colors.primaryVariant
        )


    }

}