package com.mmgpt.shoplistgpt

import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryPagesTest {

    private fun cats(vararg names: String) =
        names.mapIndexed { i, n -> ShoppingCategory(i.toLong(), n, emptyList()) }

    private val vm = cats("A", "B", "C", "D")

    @Test
    fun plan_lookupByIdOpensCorrectPage_regardlessOfScreenOrder() {
        assertEquals(3, CategoryPages.pageForCategoryId(vm, 2L, planMode = true))
    }

    @Test
    fun plan_afterReorderInViewModel_pageFollowsNewOrder() {
        val reordered = listOf(vm[2], vm[0], vm[1], vm[3])   // C, A, B, D
        assertEquals(1, CategoryPages.pageForCategoryId(reordered, 2L, planMode = true))
    }

    @Test
    fun deletedCategory_returnsNull() {
        assertEquals(null, CategoryPages.pageForCategoryId(vm, 99L, planMode = true))
    }
}