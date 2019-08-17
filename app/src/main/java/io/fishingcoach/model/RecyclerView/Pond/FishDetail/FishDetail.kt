package io.fishingcoach.model.RecyclerView.Pond

data class FishDetail (val Name : String, val Pic : Int, val Material : Array<String>, private var expanded : Boolean = false){
    fun isExpanded():Boolean{
        return expanded
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }
}