package com.sinw.stickyheader

sealed class ListItemData {
    data class HEADER(
        val item: Any
    ) : ListItemData()

    data class TOP_HOLDER(
        val item: Any
    ) : ListItemData()

    data class BOTTOM(
        val item: Any
    ) : ListItemData()

    data class ITEM(
        val item: Any
    ) : ListItemData()
}