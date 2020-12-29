package com.borjali.mostafa.pray.presentation.fragment.namaz.adapter

import android.widget.LinearLayout
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ItemListMenuBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.presentation.base.BaseAdapter


class MenuMostahabiAdapter(list: ArrayList<Menu>, private var onclick: ((Int) -> Unit)?) :
    BaseAdapter<Menu, ItemListMenuBinding>(list) {

    override fun getLayoutResourceId(): Int {
        return R.layout.item_list_menu
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemListMenuBinding>, position: Int) {

        list?.let {
            holder.binding.txtTitleGroup.text = list!![position]?.groupTitle
           /* if (list!![position]?.type == 1) {
                holder.binding.layoutItem.layoutParams.width =
                    LinearLayout.LayoutParams.MATCH_PARENT
                holder.binding.layoutItem.layoutParams.height =
                    binding.root.resources.getDimensionPixelSize(R.dimen._50sdp)
            }*/
        }
        holder.binding.layoutItem.setOnClickListener {
            onclick?.let { click ->
                click(list?.get(position)?.groupId!!)
            }

        }


    }

}








