package com.bright.ecommerce.Sealed

import com.bright.ecommerce.Model.Order

sealed class OrderSealed {

    class Success(val data :MutableList<Order>):OrderSealed()
    class Failure(val message:String):OrderSealed()
    object Loading :OrderSealed()

}