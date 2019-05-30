package com.mx.demoapplication.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable


class NewsDesc{
    @PrimaryKey
    @ColumnInfo(name = "newsId")
    private val mId: String? = null

    @Nullable
    @ColumnInfo(name = "newsTitle")
    private val mTitle: String? = null

    @Nullable
    @ColumnInfo(name = "description")
    private val mDescription: String? = null

    fun getId(): String {
        return mId!!
    }

    fun getTitle(): String? {
        return mTitle
    }
    fun getDesc(): String? {
        return mDescription
    }
}