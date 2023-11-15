package com.bright.ecommerce.ui.theme.screens.Register

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
import com.bright.ecommerce.Data.AuthViewmodel
import com.bright.ecommerce.R
import com.bright.ecommerce.Sealed.E_commerce
import kotlinx.coroutines.flow.internal.NoOpContinuation.context


//class RegisterActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginRegisterAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background) {
//                    RegisterScreen(this@RegisterActivity)
//                }
//            }
//        }
//    }
//}

@Composable
fun RegisterScreen(navcontroller: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(4.dp))
        HeaderText()
        Spacer(modifier = Modifier.height(80.dp))
        UsernameTextField()
        Spacer(modifier = Modifier.height(4.dp))
        EmailTextField()
        Spacer(modifier = Modifier.height(4.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.height(64.dp))
        ButtonRegister()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonFBRegister()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonGoogleRegister()
        Spacer(modifier = Modifier.height(110.dp))
        ButtonToLogin(onClick = {
            navcontroller.navigate(route = E_commerce.Login.route)
        })
    }
}

@Composable
private fun HeaderText() {
    Text(text = "Welcome", fontWeight = FontWeight.Bold, fontSize = 32.sp)
    Spacer(modifier = Modifier.height(2.dp))
    Text(text = "Sign up to create an account,", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color.Gray)
}

@Composable
private fun UsernameTextField(){
    var name by remember { mutableStateOf("") }
    OutlinedTextField(
        value = name,
        onValueChange = {newText -> name = newText},
        label = { Text(text = "Username")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24), contentDescription = "Email Icon")}
    )
}

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
 fun ButtonRegister() {
    Button(
        onClick = { val myregister= AuthViewmodel(navController,context)
            myregister.signup(email.text.trim(),pass.text.trim(),confirmpass.text.trim()) },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 1.dp),
        colors = ButtonDefaults.buttonColors(Color.Red),
    ) {
        Text("Register", color = Color.White)
    }
}

@Composable
private fun ButtonFBRegister() {
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
private fun ButtonGoogleRegister() {
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
private fun ButtonToLogin(onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("Have an account ? ")
        Text("Sign In ",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}