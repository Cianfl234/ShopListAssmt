package com.mmgpt.shoplistgpt

import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryPagesTest {

    private fun cats(vararg names: String) =
        names.mapIndexed { i, n -> ShoppingCategory(i.toLong(), n, emptyList()) }

    private val vm = cats("A", "B", "C", "D")

    @Test
    fun plan_noDrift_rowPositionGivesCorrectPage() {
        assertEquals(3, CategoryPages.pageForRowPosition(3, planMode = true))
    }

    @Test
    fun plan_afterDrift_tappingCCategoryOpensCPage() {
        // Screen drifted to [A, C, B, D]: C sits at row 2 but should open page 3.
        assertEquals(3, CategoryPages.pageForRowPosition(2, planMode = true))
    }
}