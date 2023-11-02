package com.bright.ecommerce


import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bright.ecommerce.Data.ProductRepositery
import com.bright.ecommerce.Data.ProductViewmodel
import com.bright.ecommerce.Sealed.E_commerce
import com.bright.ecommerce.ui.theme.EcommerceTheme
import com.bright.ecommerce.ui.theme.screens.Account.Account
import com.bright.ecommerce.ui.theme.screens.Account.FavouriteScreen
import com.bright.ecommerce.ui.theme.screens.Account.PublishScreen
import com.bright.ecommerce.ui.theme.screens.Cart.Cart
import com.bright.ecommerce.ui.theme.screens.Component.SearchBar
import com.bright.ecommerce.ui.theme.screens.Home.Home
import com.bright.ecommerce.ui.theme.screens.Home.ProductDetailScreen
import com.bright.ecommerce.ui.theme.screens.Order.OrderScreen
import com.bright.ecommerce.ui.theme.screens.Order.SuccessScreen
import com.bright.ecommerce.ui.theme.screens.Summary.SummaryScreen


class MainActivity : ComponentActivity() {

    private val productViewmodel: ProductViewmodel by viewModels {
        ProductViewmodel.Factory(
            ProductRepositery()


        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {

            val istheme = remember{ mutableStateOf(false) }
            EcommerceTheme(istheme.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(istheme)


                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun NavigationController(
        navController: NavHostController,
        padding: PaddingValues,
        istheme: MutableState<Boolean>
    ) {


        NavHost(
            navController = navController,
            startDestination = E_commerce.Home.route
        ) {

            composable(route = E_commerce.Home.route) {



                Home(productViewmodel, oncart = {

                    navController.navigate("cart")

                }, onclick = {

                    productViewmodel.addProduct(it)
                    navController.navigate("productdetails")
                }, isthem = istheme, onnext = {  navController.navigate("screen")})

            }

            composable(route = E_commerce.Order.route) {
                OrderScreen(productViewmodel = productViewmodel)
            }

            composable(route = "order1") {
                OrderScreen(productViewmodel = productViewmodel)
            }



            composable("screen")
            {
                SearchBar(productViewmodel = productViewmodel, onnext = {

                    productViewmodel.addProduct(it)
                    navController.navigate("productdetails")
                }, ondiscard = {

                    navController.popBackStack()
                })
            }



            composable(route = "cart") {
                Cart(productViewmodel, {
                    navController.popBackStack()
                }, {

                    productViewmodel.addProduct(it)
                    navController.navigate("productdetails")

                }, onsummary = {

                    navController.navigate("summary")

                })
            }

            composable(route = E_commerce.Account.route) {
                Account(
                    profileImage = R.drawable.pictures,
                    navController = navController
                )
            }

            composable("summary")
            {

                SummaryScreen(productViewmodel = productViewmodel, onSucess = {


                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate("order")



                }, onback = {

                    navController.popBackStack()

                })
            }

            composable(route = "productdetails") {

                ProductDetailScreen(productViewmodel, onback = {
                    navController.popBackStack()
                }, onnext = {

                    navController.navigate("cart")

                }) {

                    productViewmodel.addProduct(it)
                    navController.navigate("productdetails")

                }
            }

            composable("success")
            {
                SuccessScreen()
            }

            composable("favourite") {

                productViewmodel.readFavData()

                FavouriteScreen(
                    productViewmodel = productViewmodel,
                    onnext = {
                        productViewmodel.addProduct(it)
                        navController.navigate("productdetails")
                    }
                )

            }
            composable("favourite1") {

                productViewmodel.readFavData()

                FavouriteScreen(
                    productViewmodel = productViewmodel,
                    onnext = {
                        productViewmodel.addProduct(it)
                        navController.navigate("productdetails")
                    }
                )

            }

            composable(route = "publish") {
                PublishScreen(ondiscard = {

                    navController.popBackStack()

                }, onSubmit = {


                    productViewmodel.submitdata(it, onFailure = {

                        Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()


                    }, onSuccess = {

                        Toast.makeText(applicationContext, "Sucessfully added!", Toast.LENGTH_LONG)
                            .show()


                    })
                    navController.popBackStack()

                })
            }


        }


    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomAppBarExample() {
        val items = listOf(
            E_commerce.Home, E_commerce.Order, E_commerce.Cart, E_commerce.Account
        )
        Scaffold(
            bottomBar = {

                MaterialTheme.colorScheme.primary
                androidx.compose.material3.LocalAbsoluteTonalElevation//changed surfaceColorAtElevation to LocalAbsoluteTonalElevation

                BottomAppBar(
                    actions = {
                        val navController = rememberNavController()
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        items.forEach {
                            IconButton(onClick = {

                                if (currentRoute != it.route) {
                                    navController.graph.startDestinationRoute?.let {

                                        navController.popBackStack(it, true)
                                    }
                                    navController.navigate(it.route) {
                                        LaunchSingleTop = true

                                    }
                                }
                            })

                            {
                                Icon(Icons.Filled.Check, contentDescription = "Localized description")
                            }
                        }

                    },

                    )
            },
        ) { innerPadding ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = "Example of a scaffold with a bottom app bar."
            )
        }
    }




//    @Composable
//    fun BottomNavigationBar(navController: NavController) {
//
//        val items = listOf(
//            E_commerce.Home, E_commerce.Order, E_commerce.Cart, E_commerce.Account
//        )
//
//        BottomNavigation(
//            backgroundColor = MaterialTheme.colorScheme.primary,
//            elevation = 8.dp,
//        )
//        {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route
//
//            items.forEach {
//
//                BottomNavigationItem(selected = currentRoute == it.route, label = {
//                    Text(
//                        text = it.label,
//                        color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
//                    )
//                }, icon = {
//                    Icon(
//                        imageVector = it.icons,
//                        contentDescription = null,
//                        tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
//                    )
//                },
//
//                    onClick = {
//
//                        if (currentRoute != it.route) {
//                            navController.graph.startDestinationRoute?.let {
//
//                                navController.popBackStack(it, true)
//                            }
//                            navController.navigate(it.route) {
//                                launchSingleTop = true
//
//                            }
//                        }
//
//                    })
//            }
//
//        }
//    }


    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Navigation(istheme: MutableState<Boolean>) {

        val navController = rememberNavController()


        Scaffold(bottomBar = {

            if (navController.currentBackStackEntryAsState().value?.destination?.route == "publish" ||
                navController.currentBackStackEntryAsState().value?.destination?.route == "screen" ||
                navController.currentBackStackEntryAsState().value?.destination?.route == "order1" ||
                navController.currentBackStackEntryAsState().value?.destination?.route == "productdetails"
                || navController.currentBackStackEntryAsState().value?.destination?.route == "favourite1"
                || navController.currentBackStackEntryAsState().value?.destination?.route == "cart"
                || navController.currentBackStackEntryAsState().value?.destination?.route == "summary"
                || navController.currentBackStackEntryAsState().value?.destination?.route == "success") {

                Unit
            } else {
                BottomAppBarExample(navController = navController)
            }


        }) { padding ->


            NavigationController(navController = navController, padding = padding,istheme)


        }
    }


}