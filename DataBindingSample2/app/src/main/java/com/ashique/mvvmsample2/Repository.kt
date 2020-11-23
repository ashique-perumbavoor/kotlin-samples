package com.ashique.mvvmsample2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {

    private val fruitNames: List<String> = listOf(
        "Apple", "Banana", "Orange", "Kiwi", "Grapes", "Fig",
        "Pear", "Strawberry", "Gooseberry", "Raspberry"
    )

    private val currentRandomFruitNameMutableLiveData = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String> = currentRandomFruitNameMutableLiveData

    init {
        currentRandomFruitNameMutableLiveData.value = fruitNames.first()
    }

    fun getRandomFruitName(): String {
        return fruitNames[Random().nextInt(fruitNames.size)]
    }

    fun changeCurrentRandomFruitName() {
        currentRandomFruitNameMutableLiveData.value = getRandomFruitName()
    }
}