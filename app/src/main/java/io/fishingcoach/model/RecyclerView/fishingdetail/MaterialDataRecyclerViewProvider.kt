package io.fishingcoach.model.recyclerview.fishingdetail

import io.fishingcoach.App
import io.fishingcoach.model.database.Material
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUse

class MaterialDataRecyclerViewProvider (private val FishID : Int) {
    private val materialList : List<Material>

    init {
        if(FishID > 0)
            materialList = App.db.requestGetMaterialByFishId(FishID)
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