package com.borjali.mostafa.pray.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "mostahabi",
    indices = [
        Index(name = "group", value = ["groupId"])
    ]
)
data class Pray(
    @PrimaryKey
    val id: Int,
    val groupId: Int,
    val title: String?,
    val content: String?
) : Serializable