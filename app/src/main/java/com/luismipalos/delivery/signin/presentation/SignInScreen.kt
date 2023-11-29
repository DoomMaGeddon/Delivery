package com.luismipalos.delivery.signin.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luismipalos.delivery.R
import com.luismipalos.delivery.ui.theme.OrangeDelivery
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(viewModel: SignInViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val enabledSignin: Boolean by viewModel.enableSignin.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val signInFailed: Boolean by viewModel.failedSignIn.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Loading()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeDelivery)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .background(Color(0xFFFAC898)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Logo()
                Spacer(modifier = Modifier.height(20.dp))
                EmailTextField(email) {viewModel.onSigninChanged(it, pass)}
                Spacer(modifier = Modifier.height(5.dp))
                PasswordTextField(pass) {viewModel.onSigninChanged(email, it)}
                Spacer(modifier = Modifier.height(25.dp))
                SignInButton(enabledSignin)
                    {coroutineScope.launch{viewModel.onSigninSelected(navController)}}
                Spacer(modifier = Modifier.height(5.dp))
                SignUpButton()
                    {coroutineScope.launch {viewModel.onSignupSelected(navController)}}

                if (signInFailed) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Inicio de sesión fallido")},
                        text = {Text("Las credenciales no son correctas.")},
                        confirmButton = {
                            Button(
                                onClick = {viewModel.dismissErrorDialog()}
                            ) {
                                Text("Ok")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Logo() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DeliverEat",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 70.sp,
                fontFamily = FontFamily.Cursive
            ),
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun EmailTextField(email: String, onEmailChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {onEmailChanged(it)},
        placeholder = {Text(text = "Correo electrónico")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun PasswordTextField(pass: String, onPassChanged: (String) -> Unit) {
    TextField(
        value = pass,
        onValueChange = {onPassChanged(it)},
        placeholder = {Text(text = "Contraseña")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun SignInButton(enabledSignin: Boolean, onSigninSelected: () -> Unit) {
    Button(
        onClick = {onSigninSelected()},
        shape = MaterialTheme.shapes.small.copy(CornerSize(5.dp)),
        enabled = enabledSignin
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun SignUpButton(onSignupSelected: () -> Unit) {
    Button(
        onClick = onSignupSelected,
        shape = MaterialTheme.shapes.small.copy(CornerSize(5.dp))
    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAC898))
            .padding(bottom = 150.dp),
        contentAlignment = Alignment.Center
    ) {
        Logo()
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.BottomCenter)
            .size(60.dp))
    }
}