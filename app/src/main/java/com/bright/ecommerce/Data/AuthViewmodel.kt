package com.bright.ecommerce.Data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.bright.ecommerce.Model.User
import com.bright.ecommerce.Sealed.E_commerce
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewmodel(var navController: NavHostController, var context: Context) {

    var mAuth: FirebaseAuth    // this comes after connected to stk firebase in gradle
    val progress: ProgressDialog  // this is like a loader

    // first thing is to initiate
    init {
        mAuth= FirebaseAuth.getInstance()
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please Wait.....")
    }

    // then the functions
    fun signup(email:String,pass:String,confpass:String){
        progress.show()

        if (email.isBlank() || pass.isBlank() ||confpass.isBlank()){
            progress.dismiss()
            Toast.makeText(context,"Email and password cannot be blank", Toast.LENGTH_LONG).show() // the show needs to be there to show
            return
        }else if (pass != confpass){ // var pass and confpass not equal
            Toast.makeText(context,"Password do not match", Toast.LENGTH_LONG).show()
            return
        }else{ // this allows create in database
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener { // this complete listener is what confirms if its successful
                if (it.isSuccessful){
                    val userdata= User(email,pass,mAuth.currentUser!!.uid) // User model is accessed from here
                    val regRef= FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid) //userid is created here automatically
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully", Toast.LENGTH_LONG).show()
                            progress.dismiss()  // added to stop dialog box from loading on the login screen
                            navController.navigate(route = E_commerce.Login.route) // route to login after successful registration

                        }else{
                            Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                            navController.navigate(route = E_commerce.Login.route)
                        }
                    }
                }else{
                    navController.navigate(route = E_commerce.Register.route)
                }

            } }

    }
    fun login(email: String,pass: String){
        progress.show()

        // inbuilt function below
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Succeffully Logged in", Toast.LENGTH_LONG).show()
                navController.navigate(route = E_commerce.Home.route)
//                navController.navigate(ROUTE_REGISTER)TO TAKE YOU TO A DIIFFERNT PAGE
            }else{
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(route = E_commerce.Login.route)
            }
        }

    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(route = E_commerce.Login.route)
    }
    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }

}