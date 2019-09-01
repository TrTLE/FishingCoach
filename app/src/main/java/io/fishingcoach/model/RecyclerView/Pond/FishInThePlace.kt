package io.fishingcoach.model.RecyclerView.Pond

import io.fishingcoach.model.database.FishingType

data class FishInThePlace (val Name : String, val Pic : String, val FishingType : Array<FishingType>){
    private var expanded : Boolean = false

    constructor(Name : String, Pic : String, FishingType:Array<FishingType>, expanded: Boolean) : this(Name, Pic, FishingType){
        this.expanded = expanded
    }

    fun isExpanded():Boolean{
        return expanded
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }
}