package com.luismipalos.delivery.homescreen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.luismipalos.delivery.R
import com.luismipalos.delivery.homescreen.data.network.response.RestaurantResponse
import com.luismipalos.delivery.signin.presentation.Logo
import com.luismipalos.delivery.ui.theme.OrangeDelivery


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, navController: NavController) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val restaurants = viewModel.restaurants.collectAsState().value


    if (restaurants == null)
        viewModel.getRestaurants()

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
            ) {
                Header()
                Body(restaurants, viewModel, navController)
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Text(
            text = "DeliverEat",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun Body(restaurantes: List<RestaurantResponse>?, viewModel: HomeScreenViewModel,
         navController: NavController) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                for (i in 0..4) {
                    AsyncImage(
                        model = restaurantes?.get(i)?.coverURL,
                        contentDescription = null,
                        modifier = Modifier.size(124.dp)
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            for (i in 0..3) {
                AsyncImage(
                    model = restaurantes?.get(i)?.dish?.coverUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {viewModel.onPlateSelected(
                            navController,
                            restaurantes?.get(i)?.dish?.name!!
                        )}
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
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