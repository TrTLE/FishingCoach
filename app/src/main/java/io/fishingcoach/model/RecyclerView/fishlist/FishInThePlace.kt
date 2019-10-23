package io.fishingcoach.model.recyclerview.fishlist

import io.fishingcoach.model.database.FishingType

data class FishInThePlace (val Name : String, val ID : Int, val Pic : String, val PlaceID : Int, val FishingType : Array<FishingType>){
    private var expanded : Boolean = false

    constructor(Name : String, ID : Int, Pic : String, Place : Int, FishingType:Array<FishingType>, expanded: Boolean) : this(Name, ID, Pic, Place, FishingType){
            this.expanded = expanded
    }

    fun isExpanded():Boolean{
        return expanded
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }
}