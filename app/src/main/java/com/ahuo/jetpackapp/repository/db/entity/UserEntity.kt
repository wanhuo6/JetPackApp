package com.ahuo.jetpackapp.repository.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahuo.jetpackapp.repository.db.bean.AddressBean

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
@Entity
data class UserEntity(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "u_name") var name: String,
    var age: Int,
    var height: Int,
    @Embedded
    var addressBean: AddressBean
)