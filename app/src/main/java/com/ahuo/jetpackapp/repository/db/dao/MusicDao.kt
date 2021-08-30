package com.ahuo.jetpackapp.repository.db.dao

import androidx.room.*
import com.ahuo.jetpackapp.repository.db.bean.MusicListWithSongsBean
import com.ahuo.jetpackapp.repository.db.bean.UserWithMusicListBean
import com.ahuo.jetpackapp.repository.db.bean.UserWithMusicListAndSongsBean
import com.ahuo.jetpackapp.repository.db.entity.MusicListEntity
import com.ahuo.jetpackapp.repository.db.entity.MusicListSongCrossRefEntity
import com.ahuo.jetpackapp.repository.db.entity.SongEntity

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
@Dao
interface MusicDao {

    /**
     * 查询所有用户歌单歌曲
     *
     * @return
     */
    @Query("SELECT * FROM userEntity")
    fun getAll(): List<UserWithMusicListAndSongsBean>


    /**
     * 查询所有歌单歌曲
     *
     * @return
     */
    @Transaction
    @Query("SELECT * FROM musicListEntity")
    fun getMusicListWithSongsAll(): List<MusicListWithSongsBean>

    /**
     * 查询所有用户歌单
     *
     * @return
     */
    @Transaction
    @Query("SELECT * FROM userEntity")
    fun getUserWithMusicListAll(): List<UserWithMusicListBean>


    /**
     * 插入音乐数据
     *
     * @param songs
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(vararg songs: SongEntity)

    /**
     * 插入音乐歌单
     *
     * @param musicLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMusicLists(vararg musicLists: MusicListEntity)

    /**
     * 插入用户歌单关系表
     *
     * @param musicLists
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMusicListSongCrossRefs(vararg musicLists: MusicListSongCrossRefEntity)

}