package com.demo.zyl.smokedictionary

import org.litepal.LitePal
import org.litepal.LitePalApplication

/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class MyApplication: LitePalApplication() {

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
    }
}