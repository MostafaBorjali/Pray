package com.borjali.mostafa.pray.presentation.fragment.rakat_shomar

import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ItemRecyclerTimeNamazBinding
import com.borjali.mostafa.pray.presentation.base.BaseAdapter

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
                    when (position) {
                        0 -> {
                            Data.numberOFRokaat = 4
                            Data.typeOFPray = 25
                        }

                        1 -> {
                            Data.numberOFRokaat = 3
                            Data.typeOFPray = 24
                        }

                        2 -> {
                            Data.numberOFRokaat = 4
                            Data.typeOFPray = 23

                        }

                        3 -> {
                            Data.numberOFRokaat = 4
                            Data.typeOFPray = 22

                        }

                        4 -> {
                            Data.numberOFRokaat = 2
                            Data.typeOFPray = 21
                        }
                    }
                    click(position)
                }

            }


        }

    }

}



