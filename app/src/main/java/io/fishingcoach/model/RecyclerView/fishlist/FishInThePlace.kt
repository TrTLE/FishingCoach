package io.fishingcoach.model.recyclerview.fishlist

import io.fishingcoach.model.database.FishingType

data class FishInThePlace (val Name : String, val ID : Int, val Pic : String, val FishingType : Array<FishingType>){
    private var expanded : Boolean = false

    constructor(Name : String, ID : Int, Pic : String, FishingType:Array<FishingType>, expanded: Boolean) : this(Name, ID, Pic, FishingType){
            this.expanded = expanded
    }

    fun isExpanded():Boolean{
        return expanded
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }
}