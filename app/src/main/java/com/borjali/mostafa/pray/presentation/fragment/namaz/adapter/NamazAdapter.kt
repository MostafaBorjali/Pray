package com.borjali.mostafa.pray.presentation.fragment.namaz.adapter

import android.widget.LinearLayout
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ItemListMenuBinding
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.presentation.base.BaseAdapter


class NamazAdapter(list: ArrayList<Pray>, private var onclick: ((Int, Int) -> Unit)?) :
    BaseAdapter<Pray, ItemListMenuBinding>(list) {

    override fun getLayoutResourceId(): Int {
        return R.layout.item_list_menu
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemListMenuBinding>, position: Int) {

        list?.let {
            holder.binding.txtTitleGroup.text = list!![position]?.title
        }
        holder.binding.layoutItem.setOnClickListener {
            onclick?.let { click ->
                click(list?.get(position)?.groupId!!,position)
            }

        }


    }

}








