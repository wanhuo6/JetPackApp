package com.ahuo.jetpackapp.repository.db.bean

import androidx.room.Embedded
import androidx.room.Relation
import com.ahuo.jetpackapp.repository.db.entity.MusicListEntity
import com.ahuo.jetpackapp.repository.db.entity.UserEntity

/**
 *
 * @author liuhuijie
 * @date   2021/8/30
 */
data class UserWithMusicListBean(
    @Embedded
    var userEntity: UserEntity,
    @Relation(
        parentColumn = "uid", entityColumn = "userCreateId"
    )
    var musicListEntities: List<MusicListEntity>
)