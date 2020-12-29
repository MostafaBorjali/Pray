package com.borjali.mostafa.pray.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "mostahabi",
    indices = [
        Index(name = "group", value = ["groupId"])
    ]
)
@Parcelize
data class Pray(
    @PrimaryKey
    val id: Int,
    val type: Int,
    val groupId: Int,
    val groupTitle: String?,
    val title: String?,
    val content: String?
):Parcelable