package me.tigrao.github.repo.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.github.repo.R

class CustomItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val dimen = parent.resources.getDimensionPixelSize(R.dimen.list_card_separation)

        outRect.top = dimen
        outRect.right = dimen
        outRect.left = dimen
        outRect.bottom = dimen
    }
}
