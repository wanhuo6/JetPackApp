package com.ahuo.jetpackapp.repository.db.entity

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author liuhuijie
 * @date 2021/8/26
 */
@Entity
data class GoodsEntity(

    @PrimaryKey
    var id: Long,
    var name: String,
    var store: Double,
    var price: Double

)