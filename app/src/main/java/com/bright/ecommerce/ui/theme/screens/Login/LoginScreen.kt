package com.bright.ecommerce.ui.theme.screens.Login


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bright.ecommerce.R
import com.bright.ecommerce.Sealed.E_commerce
import com.bright.ecommerce.ui.theme.screens.Register.RegisterScreen


//class LoginActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginRegisterAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background) {
//                    LoginScreen(this@LoginActivity)
//                }
//            }
//        }
//    }
//}

@Composable
fun LoginScreen(navController: NavHostController) {
    // Your login screen UI code

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(4.dp))
        HeaderText()
        Spacer(modifier = Modifier.height(128.dp))
        EmailTextField()
        Spacer(modifier = Modifier.height(4.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.height(64.dp))
        ButtonLogin()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonFBLogin()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonGoogleLogin()
        Spacer(modifier = Modifier.height(128.dp))
        ButtonToRegister(onClick = {
            navController.navigate(route = E_commerce.Register.route)
        })
    }
}

@Composable
private fun HeaderText() {
    Text(text = "Welcome", fontWeight = FontWeight.Bold, fontSize = 32.sp)
    Spacer(modifier = Modifier.height(2.dp))
    Text(text = "Sign in to continue,", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color.Gray)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailTextField(){
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = {newText -> email = newText},
        label = { Text(text = "Email")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "Email Icon")}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordTextField() {
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_lock_24), contentDescription = "Password Icon")}
    )
}

@Composable
private fun ButtonLogin() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 1.dp),
        colors = ButtonDefaults.buttonColors(Color.Red),
    ) {
        Text("Login", color = Color.White)
    }
}

@Composable
private fun ButtonFBLogin() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 1.dp),
        colors = ButtonDefaults.buttonColors(Color(119, 119, 119)),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = "")
            Text("  Connect to Facebook", color = Color.White)
        }
    }
}

@Composable
private fun ButtonGoogleLogin() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 1.dp),
        colors = ButtonDefaults.buttonColors(Color(119, 119, 119)),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.icons8_google), contentDescription = "", modifier = Modifier.size(24.dp))
            Text("  Connect to Google", color = Color.White)
        }
    }
}

@Composable
private fun ButtonToRegister(onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("Haven't an account ? ")
        Text("Sign Up ",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}