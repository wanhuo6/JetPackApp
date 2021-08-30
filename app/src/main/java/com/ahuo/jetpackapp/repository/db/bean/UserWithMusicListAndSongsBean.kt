package com.ahuo.jetpackapp.repository.db.bean

import androidx.room.Embedded
import androidx.room.Relation
import com.ahuo.jetpackapp.repository.db.entity.MusicListEntity
import com.ahuo.jetpackapp.repository.db.entity.UserEntity

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
data class UserWithMusicListAndSongsBean(
    @Embedded
    var userEntity: UserEntity,

    @Relation(
        entity = MusicListEntity::class,
        parentColumn = "uid",
        entityColumn = "userCreateId"
    )
    var musicLibraryWithSongsBeans: List<MusicListWithSongsBean>? = null
)