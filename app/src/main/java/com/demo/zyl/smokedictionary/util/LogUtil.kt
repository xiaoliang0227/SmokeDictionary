package com.demo.zyl.smokedictionary.util

import android.util.Log

/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class LogUtil {

    companion object {

        const val TAG: String = "SomkeDictionary"

        fun DEBUG(tag:String, msg: String) {
            Log.d(TAG, String.format("%s:%s", tag, msg))
        }

        fun ERROR(tag:String, msg: String) {
            Log.e(TAG, String.format("%s:%s", tag, msg))
        }
    }
}