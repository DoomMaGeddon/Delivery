package com.luismipalos.delivery.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.luismipalos.delivery.homescreen.presentation.Header
import com.luismipalos.delivery.signin.presentation.Loading
import com.luismipalos.delivery.ui.theme.DeliveryTheme
import com.luismipalos.delivery.ui.theme.OrangeDelivery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel, navController: NavController, dish: String?) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading)
        Loading()
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeDelivery)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(Color(0xFFFAC898)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Header()
                Body(viewModel, navController, coroutineScope, dish)
            }
        }
    }
}

@Composable
fun Body(viewModel: DetailsScreenViewModel, navController: NavController,
         coroutineScope: CoroutineScope, dish: String?) {
    val url: String = when (dish) {
        "BeefBurger"
        -> "https://www.sargento.com/assets/Uploads/Recipe/Image/burgercampNachos_07__FillWzExNzAsNTgzXQ.jpg"
        "VegBurger"
        -> "https://www.noracooks.com/wp-content/uploads/2023/04/veggie-burgers-6-1024x1536.jpg"
        else -> "https://www.nowfindglutenfree.com/wp-content/uploads/sites/2/2016/02/nachos.gif"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(12.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                )
                Text(text = getPlateInfo(dish))
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        DeliveryTheme {
            ReturnButton {coroutineScope.launch{viewModel.onReturnSelected(navController)}}
        }
    }
}

@Composable
fun ReturnButton(onReturnSelected: () -> Unit) {
    Button(
        onClick = onReturnSelected,
        shape = MaterialTheme.shapes.small.copy(CornerSize(5.dp))
    ) {
        Text(text = "Volver")
    }
}

@Composable
fun getPlateInfo(dish: String?) : String {
    if (dish != null) {
        when (dish) {
            "BeefBurger"
                -> return """
                        A classic beef burger with fresh vegetables and melted cheese
                        
                        Ingredients:
                        -beef patty
                        -lettuce
                        -tomato
                        -onion
                        -cheese
                        """.trimIndent()
            "VegBurger"
                -> return """
                    A delicious vegetarian burger with avocado and fresh toppings.
                    
                    Ingredients:
                    -vegetarian patty
                    -lettuce
                    -tomato
                    -onion
                    -avocado
                """.trimIndent()
            else -> return """
                    Loaded nachos with seasoned ground beef, melted cheese, salsa, and guacamole.
                    
                    Ingredients:
                    -tortilla chips
                    -ground beef
                    -cheese
                    -salsa
                    -guacamole
                """.trimIndent()
        }
    }
    return "El plato no existe/no ha sido encontrado."
}