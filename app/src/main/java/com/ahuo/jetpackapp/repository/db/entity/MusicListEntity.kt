package com.ahuo.jetpackapp.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
@Entity
data class MusicListEntity(
    @PrimaryKey var mlId: Long,
    var userCreateId: Long,
    var name: String,
    var createTime: String,
)