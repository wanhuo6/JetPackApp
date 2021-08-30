package com.ahuo.jetpackapp.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ahuo.jetpackapp.repository.db.entity.GoodsEntity
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author liuhuijie
 * @date 2021/8/26
 */
@Dao
interface GoodsDao {
    /**
     * 查询所有商品
     *
     * @return
     */
    @Query("SELECT * FROM goodsEntity")
    fun getAll(): List<GoodsEntity>

    /**
     * 通过用户数组筛选商品
     *
     * @param goodsIds
     * @return
     */
    @Query("SELECT * FROM goodsEntity WHERE id IN(:goodsIds)")
    fun getByIds(goodsIds: IntArray): List<GoodsEntity>

    /**
     * 通过uid查找商品
     *
     * @param id
     * @return
     */
    @Query("SELECT * FROM goodsEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): GoodsEntity?

    /**
     * 插入商品数据
     *
     * @param goodsEntities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg goodsEntities: GoodsEntity?)
}