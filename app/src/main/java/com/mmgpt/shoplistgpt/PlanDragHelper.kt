package com.mmgpt.shoplistgpt

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mmgpt.shoplistgpt.adapter.ItemAdapter

/**
 * Drag-to-reorder helper shared by the Plan screens.
 * [onReorder] receives the moved row's positions already converted to list indexes
 * (row position minus the leading placeholder row).
 */
fun createPlanDragHelper(
    adapterProvider: () -> ItemAdapter,
    onReorder: (fromIndex: Int, toIndex: Int) -> Unit
): ItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val adapter = adapterProvider()
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition
        val itemFrom = adapter.currentList.getOrNull(from)
        val itemTo = adapter.currentList.getOrNull(to)
        if (itemFrom?.isPlaceholder == true || itemTo?.isPlaceholder == true) return false
        onReorder(from - 1, to - 1)
        adapter.moveItem(from, to)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun isLongPressDragEnabled(): Boolean = false
})