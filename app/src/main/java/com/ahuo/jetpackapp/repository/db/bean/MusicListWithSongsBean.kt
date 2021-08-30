package com.ahuo.jetpackapp.repository.db.bean

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ahuo.jetpackapp.repository.db.entity.MusicListEntity
import com.ahuo.jetpackapp.repository.db.entity.MusicListSongCrossRefEntity
import com.ahuo.jetpackapp.repository.db.entity.SongEntity

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
data class MusicListWithSongsBean(
    @Embedded val musicListEntity: MusicListEntity,
    @Relation(
        parentColumn = "mlId",
        entityColumn = "sid",
        associateBy = Junction(MusicListSongCrossRefEntity::class)
    )
    val songs: List<SongEntity>
)
