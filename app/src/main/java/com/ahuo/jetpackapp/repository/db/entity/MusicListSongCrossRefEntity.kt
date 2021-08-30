package com.ahuo.jetpackapp.repository.db.entity

import androidx.room.Entity

/**
 * @author liuhuijie
 * @date 2021/8/27
 */
@Entity(primaryKeys = ["mlId", "sid"])
data class MusicListSongCrossRefEntity(
    var mlId: Long,
    var sid: Long
)