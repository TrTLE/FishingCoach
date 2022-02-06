package io.fishingcoach.model.recyclerView.fishlist

import io.fishingcoach.model.database.FishingType

data class FishInThePlace(
    val Name: String,
    val ID: Int,
    val Pic: String,
    val PlaceID: Int,
    val FishingType: Array<FishingType>
) {
    private var expanded: Boolean = false

    fun isExpanded(): Boolean {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }
}