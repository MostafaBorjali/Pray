package com.borjali.mostafa.pray.presentation.fragment.amozesh.player


import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED
import androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_READY
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.navigation.fragment.findNavController
import com.borjali.mostafa.pray.R
import com.borjali.mostafa.pray.core.PrayApp.Companion.simpleCache
import com.borjali.mostafa.pray.databinding.FragmentPlayerBinding
import com.borjali.mostafa.pray.presentation.base.BaseFragment
import com.borjali.mostafa.pray.presentation.fragment.amozesh.AmozeshFragment.Companion.GROUP_ID
import com.borjali.mostafa.pray.presentation.fragment.amozesh.AmozeshFragment.Companion.TITLE
import com.borjali.mostafa.pray.presentation.fragment.amozesh.AmozeshViewModel
import com.borjali.mostafa.pray.presentation.fragment.amozesh.TutorialAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerFragment : BaseFragment<FragmentPlayerBinding>() {
    private val viewModel by viewModel<AmozeshViewModel>()
    override fun getLayoutResourceId() = R.layout.fragment_player
    private var groupId: Int = 0
    private var menuTitle: String = ""
    lateinit var adapter: TutorialAdapter
    private var player: ExoPlayer? = null
    private val playerListener = object : Player.Listener {
        @OptIn(UnstableApi::class)
        @SuppressLint("NotifyDataSetChanged")
        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when (playbackState) {
                STATE_ENDED -> {
                    adapter.list?.forEach { it?.isPlay = false }
                    adapter.notifyDataSetChanged()
                }

                STATE_READY -> {}

                Player.STATE_BUFFERING -> {}

                Player.STATE_IDLE -> {
                    player?.playerError?.errorCode?.let { code ->
                        if (code == ERROR_CODE_IO_NETWORK_CONNECTION_FAILED ||
                            code == ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT
                        ) {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.internet_message),
                                Toast.LENGTH_SHORT
                            ).show()
                            player?.release()
                            adapter.list?.forEach { it?.isPlay = false }
                            adapter.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }

    override fun oncreate() {
        arguments?.getInt(GROUP_ID)?.let {
            groupId = it
        }
        arguments?.getString(TITLE)?.let {
            menuTitle = it
        }
        menuHandel()
        viewModelOperation()
    }

    private fun menuHandel() {
        with(binding.menuAmozesh) {
            btnMenuBack.setOnClickListener { findNavController().popBackStack() }
            txtTitleMenu.text = menuTitle
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun viewModelOperation() {
        with(viewModel) {
            getListOfTutorials(groupId)
            listOfTutorialVideos.observe(viewLifecycleOwner) { list ->
                adapter = TutorialAdapter(list) { position ->

                    list.forEachIndexed { index, obj ->
                        obj.isPlay = index == position
                    }
                    list[position].videoUrl?.let { url ->
                        initPlayer(url)
                        play()
                        list[position].player = player
                    }
                    adapter.notifyDataSetChanged()

                }
                binding.recyclerViewTutorial.adapter = adapter


            }
        }
    }

    override fun onPause() {
        if (this::adapter.isInitialized) {
            releasePlayer()
        }
        super.onPause()
    }

    @OptIn(UnstableApi::class)
    private fun initPlayer(mediaUrl: String) {
        player?.release()
        // Create a data source factory with cache support
        val httpDataSourceFactory = DefaultHttpDataSource.Factory()
        val cacheDataSourceFactory = CacheDataSource.Factory().setCache(simpleCache)
            .setUpstreamDataSourceFactory(httpDataSourceFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)

        // Create a media source using the cache data source factory
        val mediaSourceFactory = ProgressiveMediaSource.Factory(cacheDataSourceFactory)

        player = ExoPlayer.Builder(requireContext()).setMediaSourceFactory(mediaSourceFactory)
            .build().apply {
                setMediaItem(MediaItem.fromUri(Uri.parse(mediaUrl)))
                prepare()
                addListener(playerListener)
            }

    }

    private fun releasePlayer() {
        player?.apply {
            playWhenReady = false
            release()
        }
        player = null
    }

    private fun play() {
        player?.playWhenReady = true
    }
}
