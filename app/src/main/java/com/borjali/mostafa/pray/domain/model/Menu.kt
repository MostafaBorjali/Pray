package com.borjali.mostafa.pray.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "menu")

data class Menu(
    @PrimaryKey
    val id: Int,
    val groupId: Int,
    val type: Int,
    val groupTitle: String?
) : Serializable