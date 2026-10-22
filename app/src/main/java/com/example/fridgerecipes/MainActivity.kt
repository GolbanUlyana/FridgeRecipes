package com.example.fridgerecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    if (selectedRecipe == null) {
        RecipeListScreen(onRecipeClick = { recipe -> selectedRecipe = recipe })
    } else {
        RecipeDetailsScreen(recipe = selectedRecipe!!, onBack = { selectedRecipe = null })
    }
}

@Composable
fun RecipeListScreen(onRecipeClick: (Recipe) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Заголовок
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFC107))
                .padding(16.dp)
        ) {
            Text(
                text = "Що приготувати?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Список рецептів
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(RecipeRepository.recipes) { recipe ->
Card(
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onRecipeClick(recipe) },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Додаємо картинку
        Image(
            painter = painterResource(id = recipe.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = recipe.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "⏱ ${recipe.time}", color = Color.Gray)
    }
}
            }
        }
    }
}

@Composable
fun RecipeDetailsScreen(recipe: Recipe, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Кнопка назад
TextButton(onClick = onBack) {
    Text(text = "← Назад до списку", fontSize = 16.sp)
}
Spacer(modifier = Modifier.height(16.dp))

// Додаємо велику картинку на екран деталей
Image(
    painter = painterResource(id = recipe.imageResId),
    contentDescription = null,
    modifier = Modifier
        .fillMaxWidth()
        .height(220.dp)
        .clip(RoundedCornerShape(12.dp)),
    contentScale = ContentScale.Crop
)
Spacer(modifier = Modifier.height(16.dp))

Text(text = recipe.title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Інгредієнти:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = recipe.ingredients, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Приготування:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = recipe.instructions, fontSize = 16.sp, lineHeight = 24.sp)
    }
}