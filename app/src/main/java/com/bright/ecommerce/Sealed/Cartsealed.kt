package com.bright.ecommerce.Sealed


import com.bright.ecommerce.Model.Cartitem

sealed class Cartsealed {

    class Success(val data :MutableList<Cartitem>):Cartsealed()
    class Failure(val message:String):Cartsealed()
    object Loading :Cartsealed()

}