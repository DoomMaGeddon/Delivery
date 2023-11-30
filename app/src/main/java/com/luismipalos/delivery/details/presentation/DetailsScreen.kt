package com.luismipalos.delivery.details.presentation

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.luismipalos.delivery.details.data.network.response.DetailsResponse
import com.luismipalos.delivery.homescreen.presentation.Header
import com.luismipalos.delivery.signin.presentation.Loading
import com.luismipalos.delivery.ui.theme.DeliveryTheme
import com.luismipalos.delivery.ui.theme.OrangeDelivery

@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel, navController: NavController, name: String?) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val dish = viewModel.dish.collectAsState().value

    viewModel.getDetails(name!!)

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
                Body(viewModel, navController, dish)
            }
        }
    }
}

@Composable
fun Body(viewModel: DetailsScreenViewModel, navController: NavController, dish: DetailsResponse?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(12.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                if (dish == null)
                    Text(text = "El plato no existe.")
                else {
                    AsyncImage(
                        model = dish.coverUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = dish.description)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Ingredients: ")
                    Spacer(modifier = Modifier.height(15.dp))
                    for (item in dish.ingredients) {
                        Text(text = "-$item")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        DeliveryTheme {
            ReturnButton {viewModel.onReturnSelected(navController)}
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