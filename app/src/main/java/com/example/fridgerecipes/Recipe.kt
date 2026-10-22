package com.example.fridgerecipes

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: String,
    val instructions: String,
    val time: String,
    @DrawableRes val imageResId: Int 
)

object RecipeRepository {
    val recipes = listOf(
        Recipe(
            1,
            "Омлет з того, що є",
            "Яйця, молоко, сіль, будь-який сир, залишки ковбаси або овочів.",
            "Збити яйця з молоком, додати натертий сир та інші інгредієнти. Вилити на розігріту сковороду та смажити під кришкою 5-7 хвилин.",
            "10 хв",
            R.drawable.omelette // Прив'язали картинку
        ),
        Recipe(
            2,
            "Паста з томатами",
            "Макарони, томатна паста, часник, олія, сіль, цукор.",
            "Відварити макарони. На сковороді обсмажити часник, додати томатну пасту, трохи цукру та води. Тушкувати 5 хв. Змішати з макаронами.",
            "20 хв",
            R.drawable.pasta
        ),
        Recipe(
            3,
            "Салат з тунцем",
            "Консерви тунця, яйця, кукурудза, майонез, зелень.",
            "Відварити яйця. Змішати тунець, нарізані яйця, кукурудзу та зелень. Заправити майонезом.",
            "15 хв",
            R.drawable.salad
        )
    )
}