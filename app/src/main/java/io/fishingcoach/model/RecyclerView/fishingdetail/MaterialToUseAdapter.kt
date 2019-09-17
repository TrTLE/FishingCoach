package io.fishingcoach.model.recyclerview.fishingdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.fishingcoach.R
import kotlinx.android.synthetic.main.item_and_material.view.*

class MaterialToUseAdapter (val items : Array<MaterialToUse>) : RecyclerView.Adapter<MaterialToUseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_and_material, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMaterialHere(items[position])
    }

    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view){

        fun bindMaterialHere(MaterialHere : MaterialToUse){
            with(MaterialHere){
                Glide
                    .with(view)
                    .load(IMG)
                    .centerCrop()
                    .into(itemView.materialPic)

                itemView.materialName.text = "$Name"
            }
        }
    }
}