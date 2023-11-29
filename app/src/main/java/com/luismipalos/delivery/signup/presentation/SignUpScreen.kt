package com.luismipalos.delivery.signup.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
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
import androidx.compose.ui.graphics.vector.ImageVector
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
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val enabledSignup: Boolean by viewModel.enableSignup.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val signUpFailed: Boolean by viewModel.failedSignUp.observeAsState(initial = false)
    val successfulSignUp: Boolean by viewModel.successfulSignUp.observeAsState(initial = false)
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
                DataTextField("Nombre", Icons.Default.Face, name)
                    {viewModel.onSignupChanged(it, email, pass)}
                Spacer(modifier = Modifier.height(5.dp))
                DataTextField("Correo electrónico", Icons.Default.Mail, email)
                    {viewModel.onSignupChanged(name, it, pass)}
                Spacer(modifier = Modifier.height(5.dp))
                PasswordTextField(pass) {viewModel.onSignupChanged(name, email, it)}
                Spacer(modifier = Modifier.height(25.dp))
                SignUpButton(enabledSignup)
                    {coroutineScope.launch {viewModel.onSignupSelected()}}
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "¿Ya tienes una cuenta?")
                Spacer(modifier = Modifier.height(5.dp))
                SignInButton {coroutineScope.launch {viewModel.onSigninSelected(navController)}}

                if (signUpFailed) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Registro fallido")},
                        text = {Text("No se ha podido registrar.")},
                        confirmButton = {
                            Button(
                                onClick = {viewModel.dismissErrorDialog()}
                            ) {
                                Text("Ok")
                            }
                        }
                    )
                }

                if (successfulSignUp) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Registro exitoso")},
                        text = {Text("Se ha enviado un correo de confirmación.")},
                        confirmButton = {
                            Button(
                                onClick = {coroutineScope.launch {
                                    viewModel.dismissSuccessDialog(navController)}
                                }
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
fun DataTextField(text: String, icon: ImageVector, data: String, onEmailChanged: (String) -> Unit) {
    TextField(
        value = data,
        onValueChange = {onEmailChanged(it)},
        placeholder = { Text(text = text) },
        leadingIcon = {
            Icon(
                imageVector = icon,
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
        placeholder = { Text(text = "Contraseña") },
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
fun SignUpButton(enabledSignup: Boolean, onSignupSelected: () -> Unit) {
    Button(
        onClick = {onSignupSelected()},
        shape = MaterialTheme.shapes.small.copy(CornerSize(5.dp)),
        enabled = enabledSignup
    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun SignInButton(onSigninSelected: () -> Unit) {
    Button(
        onClick = onSigninSelected,
        shape = MaterialTheme.shapes.small.copy(CornerSize(5.dp))
    ) {
        Text(text = "Iniciar sesión")
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