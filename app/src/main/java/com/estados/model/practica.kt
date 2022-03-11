package com.estados.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Estados")

data class practica(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "estado")
    val estado: String,
    @ColumnInfo(name = "capital")
    val capital: String?,
    @ColumnInfo(name = "poblacion")
    val poblacion: Int?,
    @ColumnInfo(name = "costas")
    val costas: String?
) : Parcelable
