package io.fishingcoach.model.recyclerview.fishingdetail

import io.fishingcoach.App
import io.fishingcoach.model.database.Material
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUse

class MaterialDataRecyclerViewProvider (fishID : Int, fishingTypeID : Int, placeID : Int) {
    private val materialList : List<Material>

    init {
        if(fishID > 0 && fishingTypeID > 0 && placeID > 0)
            materialList = App.db.requestGetMaterialByFishIdAndFishingTypeID(fishID, fishingTypeID, placeID)
        else
            materialList = emptyList()
    }

    fun getMaterialToUse() : Array<MaterialToUse> {
        var materialToUseInThePlace = emptyArray<MaterialToUse>()

        if (materialList.isNotEmpty()){
            var i = 0
            val url = "https://storage.needpix.com/rsynced_images/fishing-310847_1280.png"

            materialToUseInThePlace = Array<MaterialToUse>(materialList.size){
                MaterialToUse("SPECIMEN", url)
            }

            for (material in materialList){
                materialToUseInThePlace[i++] = MaterialToUse(material.NAME,material.IMG)
            }
        }

        return materialToUseInThePlace
    }
}