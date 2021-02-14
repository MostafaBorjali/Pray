package com.borjali.mostafa.pray.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "menu")
@Parcelize
data class Menu(
    @PrimaryKey
    val id: Int,
    val groupId: Int,
    val type: Int,
    val groupTitle: String?):Parcelable