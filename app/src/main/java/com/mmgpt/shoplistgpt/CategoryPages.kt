package com.mmgpt.shoplistgpt

object CategoryPages {
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