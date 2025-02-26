package com.borjali.mostafa.pray.domain.model

import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "amoozesh",
    indices = [
        Index(name = "groups", value = ["groupId"])
    ]
)
data class Tutorial(
    @PrimaryKey
    val id: Int,
    val groupId: Int,
    val title: String,
    val description: String?,
    val videoUrl: String?,
    val imageUrl: String?
) : Serializable {
    @Ignore
    var isPlay: Boolean = false
    @Ignore
    var player: ExoPlayer? = null
}
