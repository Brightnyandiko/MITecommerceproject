package com.bright.ecommerce.ui.theme.screens.Account

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Account(navController: NavController, @DrawableRes profileImage: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.primary)//
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Profile",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = profileImage),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column {


                Text(
                    text = "Azad Kumar",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(2.dp))


                Text(
                    text = "Kumarazad2917@gmail.com",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,//changed from primaryVariant to primary and color to colorScheme
                )

            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "General",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,//changed from primaryVariant to primary and color to colorScheme
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.Lock,"Order History"){ navController.navigate("order1")}
        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.Favorite,"Favourite"){ navController.navigate("favourite1")}

        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.ShoppingCart,"Publish Product"){ navController.navigate("publish")}

        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.ShoppingCart,"Cart History"){
            navController.navigate("cart")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Personal",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.Lock,"Privacy & Policy"){

        }
        Spacer(modifier = Modifier.height(12.dp))

        View(icon = Icons.Default.Info,"Terms & Conditions"){


        }



    }
}


@Composable
fun View(icon: ImageVector, text:String, publish: () -> Unit) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(tint = MaterialTheme.colorScheme.primary,
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary).padding(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )

        }




        Icon(tint=MaterialTheme.colorScheme.primary,
            imageVector = Icons.Rounded.ArrowForward,
            contentDescription = null,
            modifier = Modifier.clickable {publish()  }
        )

    }


}