package com.bright.ecommerce.Sealed

import com.bright.ecommerce.Model.Product

sealed class ProductSealed {

    class Success(val data :MutableList<Product>):ProductSealed()
    class Failure(val message:String):ProductSealed()
    object Loading :ProductSealed()

}