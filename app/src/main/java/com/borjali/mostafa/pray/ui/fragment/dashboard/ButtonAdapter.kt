package com.borjali.mostafa.pray.ui.fragment.dashboard

import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ItemRecyclerTimeNamazBinding
import com.borjali.mostafa.pray.ui.base.BaseAdapter

import com.borjali.mostafa.pray.utils.Data


class ButtonAdapter(list: ArrayList<String>, private var onclick: ((Int) -> Unit)?) :
    BaseAdapter<String, ItemRecyclerTimeNamazBinding>(list) {

    override fun getLayoutResourceId(): Int {
        return R.layout.item_recycler_time_namaz
    }


    override fun onBindViewHolder(holder: ViewHolder<ItemRecyclerTimeNamazBinding>, position: Int) {

        list?.let {
            if (Data.position == position) {
                holder.binding.btnBackground.setCardBackgroundColor(
                    binding.root.resources.getColor(
                        R.color.colorPrimary
                    )
                )
                holder.binding.btnTitle.text = list!![position]
                holder.binding.btnTitle.setTextColor(binding.root.resources.getColor(R.color.white))
            } else {
                holder.binding.btnBackground.setCardBackgroundColor(
                    binding.root.resources.getColor(
                        R.color.white
                    )
                )
                holder.binding.btnTitle.text = list!![position]
                holder.binding.btnTitle.setTextColor(binding.root.resources.getColor(R.color.titleTextColor))
            }
            holder.binding.btnBackground.setOnClickListener { _ ->
                onclick?.let { click ->
                    Data.position = position
                    when(position){
                        4 ->{ Data.rokaat = 2 }
                        1 ->{ Data.rokaat = 3 }
                        0,2,3->{ Data.rokaat = 4 }
                    }
                    click(position)
                }

            }


        }

    }

}



