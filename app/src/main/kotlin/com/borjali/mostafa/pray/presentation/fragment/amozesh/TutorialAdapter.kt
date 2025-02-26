package com.borjali.mostafa.pray.presentation.fragment.amozesh

import android.view.View
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.databinding.ItemListVideoBinding
import com.borjali.mostafa.pray.domain.model.Tutorial
import com.borjali.mostafa.pray.presentation.base.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class TutorialAdapter(
    list: List<Tutorial>,
    private var onclick: ((Int) -> Unit)
) :
    BaseAdapter<Tutorial, ItemListVideoBinding>(list) {
    override fun getLayoutResourceId(): Int {
        return R.layout.item_list_video
    }

    @OptIn(UnstableApi::class)
    override fun onBindViewHolder(holder: ViewHolder<ItemListVideoBinding>, position: Int) {

        list!![position]?.let { tutorial ->
            holder.binding.txtTitle.text = tutorial.title

            holder.binding.videoCover.setImageResource(R.drawable.ahkam)


            holder.binding.play.setOnClickListener {
                onclick(position)
            }
            if (tutorial.isPlay) {
                holder.binding.videoCover.visibility = View.GONE
                holder.binding.play.visibility = View.GONE
                holder.binding.playerView.player = tutorial.player

            } else {
                holder.binding.videoCover.visibility = View.VISIBLE
                holder.binding.play.visibility = View.VISIBLE
                holder.binding.playerView.player = null

            }

            Glide.with(holder.binding.videoCover.context)
                .load(tutorial.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ahkam)
                .into(holder.binding.videoCover)

        }
    }

}








