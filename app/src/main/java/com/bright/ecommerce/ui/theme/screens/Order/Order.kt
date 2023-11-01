package com.bright.ecommerce.ui.theme.screens.Order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.bright.ecommerce.Data.ProductViewmodel
import com.bright.ecommerce.Model.Order
import com.bright.ecommerce.Sealed.OrderSealed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(productViewmodel: ProductViewmodel) {


    when (val result = productViewmodel.readorder.value) {

        is OrderSealed.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                CircularProgressIndicator()

            }

        }

        is OrderSealed.Success -> {


            if (result.data.isNotEmpty()) {
                Order(productViewmodel, result.data)
            } else {

                Scaffold(
                    topBar = { OrderTopBar() }, modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                ) {

                    Box(
                        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary).padding(it),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(text = "There is no Order!", fontSize = 18.sp, color = MaterialTheme.colorScheme.primaryContainer)
                    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Order(productViewmodel: ProductViewmodel, data: MutableList<Order>) {


    Scaffold(
        topBar = { OrderTopBar() }, modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary).padding(it)
        )
        {

            item {
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(data) {

                OrderItem(order = it, productViewmodel = productViewmodel)


            }

        }
    }


}

@Composable
fun OrderTopBar() {

    Row(
        Modifier
            .fillMaxWidth().background(MaterialTheme.colorScheme.primary).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(
            text = "Order",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primaryContainer//changed color to colorScheme and primaryVariant to primaryContainer
        )


    }
}


@Composable
fun OrderItem(order: Order, productViewmodel: ProductViewmodel) {

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = order.productname,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primaryContainer//changed color to colorScheme and primaryVariant to primaryContainer
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "$${order.price} * ${order.quantity} = $${order.price * order.quantity}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = { productViewmodel.removeToOrder(order) },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primaryContainer
                    )
                ) {

                    Text(text = "Remove", color = MaterialTheme.colorScheme.primary)

                }

                Text(
                    text = order.date.uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))


            }


            Spacer(modifier = Modifier.width(8.dp))

            AsyncImage(
                model = order.imageUrl.toUri(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}