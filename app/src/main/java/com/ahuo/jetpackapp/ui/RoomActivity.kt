package com.ahuo.jetpackapp.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.room.Room
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.base.BaseActivity
import com.ahuo.jetpackapp.base.BaseViewModel
import com.ahuo.jetpackapp.databinding.ActivityRoomBinding
import com.ahuo.jetpackapp.repository.db.AppDatabase
import com.ahuo.jetpackapp.repository.db.MIGRATION_1_2
import com.ahuo.jetpackapp.repository.db.MIGRATION_2_3
import com.ahuo.jetpackapp.repository.db.bean.AddressBean
import com.ahuo.jetpackapp.repository.db.bean.MusicListWithSongsBean
import com.ahuo.jetpackapp.repository.db.bean.UserWithMusicListBean
import com.ahuo.jetpackapp.repository.db.bean.UserWithMusicListAndSongsBean
import com.ahuo.jetpackapp.repository.db.entity.*
import java.lang.StringBuilder
import java.util.*

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
class RoomActivity : BaseActivity<ActivityRoomBinding, BaseViewModel>(R.layout.activity_room) {

    lateinit var appDatabase: AppDatabase

    override fun initView(savedInstanceState: Bundle?) {
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_db"
        ).addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_3).allowMainThreadQueries().build()
        initData()
        val stringBuilder = StringBuilder()
        findViewById<View>(R.id.tvLookAll).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val userEntities: List<UserEntity> = appDatabase.userDao().getAll()
            for (userEntity in userEntities) {
                stringBuilder.append(userEntity.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }
        findViewById<View>(R.id.tvLookSome).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val userEntities: List<UserEntity> =
                appDatabase.userDao().getByIds(intArrayOf(1, 2))
            for (userEntity in userEntities) {
                stringBuilder.append(userEntity.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }
        findViewById<View>(R.id.tvInsert).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            for (i in 0..3) {
                appDatabase.userDao().insertAll(
                    UserEntity(
                        i.toLong(),
                        "吉姆$i",
                        i,
                        i,
                        AddressBean("北京", "长安街道", 0, 0)
                    )
                )
            }

        }
        findViewById<View>(R.id.tvDel).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            appDatabase.userDao().delete(UserEntity(0, "吉姆", 1, 1, AddressBean("北京", "长安街道", 0, 0)))
        }
        findViewById<View>(R.id.tvUpdate).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            val userEntity: UserEntity = appDatabase.userDao().findByUId(1)
            userEntity.name = "hahaha"
            appDatabase.userDao().update(userEntity)
        }

        findViewById<View>(R.id.tvLookAllGoods).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val goodsEntities: List<GoodsEntity> =
                appDatabase.goodsDao().getAll()
            for (goodsEntity in goodsEntities) {
                stringBuilder.append(goodsEntity.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }

        findViewById<View>(R.id.tvInsertGoods).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            for (i in 0..2) {
                appDatabase.goodsDao()
                    .insertAll(GoodsEntity((100 + i).toLong(), "哈姆雷特$i", 3.0, 3.3))
            }

        }

        findViewById<View>(R.id.tvLookAllUserAndMusicLibrary).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val userAndLibraryBeans: List<UserWithMusicListBean> =
                appDatabase.musicDao().getUserWithMusicListAll()
            for (userAndLibraryBean in userAndLibraryBeans) {
                stringBuilder.append(userAndLibraryBean.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }

        findViewById<View>(R.id.tvLookAllMusicLibraryWithSong).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val musicLibraryWithSongsBeans: List<MusicListWithSongsBean> =
                appDatabase.musicDao().getMusicListWithSongsAll()
            for (musicLibraryWithSongsBean in musicLibraryWithSongsBeans) {
                stringBuilder.append(musicLibraryWithSongsBean.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }
        findViewById<View>(R.id.tvLookAllUserWithMusicLibraryAndSong).setOnClickListener { v: View? ->
            stringBuilder.setLength(0)
            stringBuilder.append("查询结果：")
            val userWithMusicLibAndSongsBeans: List<UserWithMusicListAndSongsBean> =
                appDatabase.musicDao().getAll()
            for (userWithMusicLibAndSongsBean in userWithMusicLibAndSongsBeans) {
                stringBuilder.append(userWithMusicLibAndSongsBean.toString())
            }
            (findViewById<View>(R.id.tvResult) as TextView).text = stringBuilder
        }


    }

    private fun initData() {
        for (i in 0..1) {
            appDatabase.userDao()
                .insertAll(UserEntity(i.toLong(), "小迪$i", i, i, AddressBean("北京", "长安街道$i", i, i)))
        }
        for (i in 0..9) {
            appDatabase.musicDao().insertSongs(SongEntity(i.toLong(), "黎明$i", "今夜无眠$i"))
        }
        for (i in 0..2) {
            appDatabase.musicDao()
                .insertMusicLists(MusicListEntity(i.toLong(), i.toLong(), "歌单$i", "222"))
        }

        val musicLibSongCrossRefEntity = MusicListSongCrossRefEntity(0, 0)
        for (i in 0..2) {
            musicLibSongCrossRefEntity.mlId = i.toLong()
            for (j in 0..2) {
                musicLibSongCrossRefEntity.sid = Random().nextInt(10).toLong()
                appDatabase.musicDao().insertMusicListSongCrossRefs(musicLibSongCrossRefEntity)
            }

        }
    }
}