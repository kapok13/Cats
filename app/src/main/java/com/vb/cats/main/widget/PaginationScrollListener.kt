package com.vb.cats.main.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    var onLoad: (() -> Unit)? = null
    private var lastTotalItemCount = 0
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount < lastTotalItemCount) {
            lastTotalItemCount = totalItemCount
        }

        if (loading && totalItemCount > lastTotalItemCount) {
            loading = false
            val initialScroll = lastTotalItemCount == 0
            lastTotalItemCount = totalItemCount
            if (initialScroll) {
                return
            }
        }

        if (!loading && lastVisibleItem >= totalItemCount - 1) {
            loading = true
            onLoad?.invoke()
        }
    }
}
