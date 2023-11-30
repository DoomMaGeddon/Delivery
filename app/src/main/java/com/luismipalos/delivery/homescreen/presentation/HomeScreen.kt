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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.luismipalos.delivery.R
import com.luismipalos.delivery.signin.presentation.Logo
import com.luismipalos.delivery.ui.theme.OrangeDelivery
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, navController: NavController) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val restaurants = viewModel.restaurants.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    if (restaurants == null)
        coroutineScope.launch { viewModel.getRestaurants() }

    val restaurantes = listOf(
        "https://hips.hearstapps.com/hmg-prod/images/el-portal-alicante-restaurante-elle-gourmet-642eb08578c8a.jpg?crop=1.00xw:1.00xh;0,0&resize=980:*",
        "https://hips.hearstapps.com/hmg-prod/images/monastrell-3-insta-1525364082.jpg?crop=1xw:1xh;center,top&resize=980:*",
        "https://res.cloudinary.com/tf-lab/image/upload/w_600,h_310,c_fill,g_auto:subject,q_auto,f_auto/restaurant/ae360162-650e-4960-9f3d-3b7fa1e8130b/f3a5df57-6110-4e45-af58-40f0d4ff9997.jpg",
        "https://res.cloudinary.com/tf-lab/image/upload/w_600,h_310,c_fill,g_auto:subject,q_auto,f_auto/restaurant/42414cd4-0208-4e27-9cce-499978a299dd/3e427c22-196f-439e-a0e0-65afa19303bb.jpg"
    )

    val platos = listOf(
        "https://www.sargento.com/assets/Uploads/Recipe/Image/burgercampNachos_07__FillWzExNzAsNTgzXQ.jpg",
        "https://www.noracooks.com/wp-content/uploads/2023/04/veggie-burgers-6-1024x1536.jpg",
        "https://www.nowfindglutenfree.com/wp-content/uploads/sites/2/2016/02/nachos.gif",
    )

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
                Body(restaurantes, platos, viewModel, navController)
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
fun Body(restaurantes: List<String>, platos: List<String>, viewModel: HomeScreenViewModel,
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
                Restaurant(restaurant = restaurantes[0])
                Spacer(modifier = Modifier.height(20.dp))
                Restaurant(restaurant = restaurantes[1])
                Spacer(modifier = Modifier.height(20.dp))
                Restaurant(restaurant = restaurantes[2])
                Spacer(modifier = Modifier.height(20.dp))
                Restaurant(restaurant = restaurantes[3])
            }
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Dish(dish = platos[0], navController, viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            Dish(dish = platos[1], navController, viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            Dish(dish = platos[2], navController, viewModel)
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

@Composable
fun Restaurant(restaurant: String) {
    Image(
        painter = rememberAsyncImagePainter(restaurant),
        contentDescription = null,
        modifier = Modifier.size(124.dp)
    )
}

@Composable
fun Dish(dish: String, navController: NavController, viewModel: HomeScreenViewModel) {
    val coroutineScope = rememberCoroutineScope()

    val name: String = when (dish) {
        "https://www.sargento.com/assets/Uploads/Recipe/Image/burgercampNachos_07__FillWzExNzAsNTgzXQ.jpg"
        -> "BeefBurger"
        "https://www.noracooks.com/wp-content/uploads/2023/04/veggie-burgers-6-1024x1536.jpg"
        -> "VegBurger"
        else -> "Nachos"
    }

    Image(
        painter = rememberAsyncImagePainter(dish),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { coroutineScope.launch { viewModel.onPlateSelected(navController, name) } }
    )
}