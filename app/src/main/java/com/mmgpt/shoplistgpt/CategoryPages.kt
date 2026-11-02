package com.mmgpt.shoplistgpt

object CategoryPages {

    fun pageForRowPosition(rowPosition: Int, planMode: Boolean): Int =
        if (planMode) (rowPosition - 1) + 1 else rowPosition

    fun pageForCategoryId(
        categories: List<ShoppingCategory>,
        categoryId: Long,
        planMode: Boolean
    ): Int? {
        val index = categories.indexOfFirst { it.id == categoryId }
        if (index < 0) return null
        return if (planMode) index + 1 else index
    }
}