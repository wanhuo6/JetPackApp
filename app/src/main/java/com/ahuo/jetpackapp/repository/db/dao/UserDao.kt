package com.ahuo.jetpackapp.repository.db.dao

import androidx.room.*
import com.ahuo.jetpackapp.repository.db.entity.UserEntity

/**
 *
 * @author liuhuijie
 * @date   2021/8/27
 */
@Dao
interface UserDao {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Query("SELECT * FROM userEntity")
    fun getAll(): List<UserEntity>

    /**
     * 通过用户数组筛选用户
     *
     * @param userIds
     * @return
     */
    @Query("SELECT * FROM userEntity WHERE uid IN(:userIds)")
    fun getByIds(userIds: IntArray): List<UserEntity>

    /**
     * 通过Uid查找用户
     *
     * @param uid
     * @return
     */
    @Query("SELECT * FROM userEntity WHERE uid LIKE :uid LIMIT 1")
    fun findByUId(uid: Int): UserEntity

    /**
     * 通过名字查找用户
     *
     * @param name
     * @return
     */
    @Query("SELECT * FROM UserEntity WHERE u_name LIKE :name LIMIT 1")
    fun findByName(name: String): UserEntity

    /**
     * 插入用户数据
     *
     * @param users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserEntity)

    /**
     * 删除用户
     *
     * @param userEntity
     */
    @Delete
    fun delete(userEntity: UserEntity)

    /**
     * 更新用户
     *
     * @param userEntity
     */
    @Update
    fun update(userEntity: UserEntity)


}