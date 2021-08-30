package com.ahuo.jetpackapp.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ahuo.jetpackapp.repository.db.dao.GoodsDao
import com.ahuo.jetpackapp.repository.db.dao.MusicDao
import com.ahuo.jetpackapp.repository.db.dao.UserDao
import com.ahuo.jetpackapp.repository.db.entity.*

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
@Database(
    entities = [UserEntity::class, GoodsEntity::class, SongEntity::class, MusicListEntity::class, MusicListSongCrossRefEntity::class],
    version = 4,
    exportSchema = false
)

open abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun goodsDao(): GoodsDao
    abstract fun musicDao(): MusicDao


}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE UserEntity " + " ADD COLUMN weight DOUBLE " + " NOT NULL DEFAULT 0.0")
    }
}
val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // database.execSQL("CREATE TABLE GoodsEntity " + " ADD COLUMN weight DOUBLE " + " NOT NULL DEFAULT 0.0");
        database.execSQL(
            "CREATE TABLE 'GoodsEntity' ('id' INTEGER NOT NULL,'name' TEXT,'store' DOUBLE,'price' DOUBLE,PRIMARY KEY('id'))"
        )
    }
}