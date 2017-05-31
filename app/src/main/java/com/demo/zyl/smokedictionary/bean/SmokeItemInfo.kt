package com.demo.zyl.smokedictionary.bean

import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by zhaoyongliang on 2017/5/25.
 */
class SmokeItemInfo: DataSupport(), Serializable {

    // 品牌
    var brand: String? = null

    // 照片
    var photo: String? = null

    // 名称
    var sname: String? = null

    // 香烟类型：烤烟型等等
    var type: String? = null

    // 焦油量
    var tarContent: String? = null

    // 烟气烟碱量
    var nicotine: String? = null

    // 烟气一氧化碳量
    var co: String? = null

    // 单包价格
    var price1: String? = null

    // 单条价格
    var price2: String? = null

    /**
     * 插入或者更新数据
     */
    fun insertOrUpdateData(info: SmokeItemInfo) : SmokeItemInfo? {
        if (null == info) {
            return null
        }
        if (info.baseObjId == 0L) {
            info.save()
        } else {
            info.update(info.baseObjId)
        }
        return info
    }

    /**
     * 获取数据数量
     */
    fun findDataCount():Int {
        return DataSupport.count(SmokeItemInfo::class.java)
    }

    /**
     * 获取数据 从下标：start开始，获取limit个
     */
    fun findData(type: String, start: Int, limit: Int): MutableList<SmokeItemInfo>? {
        return DataSupport.where("brand = ?", type).offset(start).limit(limit).find(SmokeItemInfo::class.java)
    }
}