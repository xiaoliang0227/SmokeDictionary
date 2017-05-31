package com.demo.zyl.smokedictionary.tasks

import android.content.Context
import android.os.AsyncTask
import com.demo.zyl.smokedictionary.R
import com.demo.zyl.smokedictionary.bean.SmokeItemInfo
import com.demo.zyl.smokedictionary.util.LogUtil
import net.lemonsoft.lemonbubble.LemonBubble
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class DataInitTask : AsyncTask<Void, Void, Void>() {

    companion object {
        const val TAG: String = "DataInitTask"
    }

    private var context: Context? = null

    private var callback: DataInitTaskCallback? = null

    fun setContext(context: Context) {
        this.context = context
    }

    fun setCallback(callback: DataInitTaskCallback) {
        this.callback = callback
    }

    override fun onPreExecute() {
        super.onPreExecute()
        LemonBubble.showRoundProgress(context, "初始化数据中，请稍候")
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        LemonBubble.showRight(context, "初始化数据成功", 2000)
        callback?.afterDataInitCompleted()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        // 读取smoke data
        var inputReader: InputStreamReader = InputStreamReader(context?.resources?.openRawResource(R.raw.smoke_data))
        var bufReader: BufferedReader = BufferedReader(inputReader)
        var line: String?
        do {
            line = bufReader.readLine()
            if (line == null) {
                break
            }

            // 分解data
            var data: List<String> = line.split("|")
            var info: SmokeItemInfo = SmokeItemInfo()
            info.brand = data[0]
            info.sname = data[1]
            info.photo = data[2]
            info.type = data[3]
            info.tarContent = if ("不详".equals(data[4])) "不详" else data[4]
            info.nicotine = if ("不详".equals(data[5])) "不详" else data[5]
            info.co = if ("不详".equals(data[6])) "不详" else data[6]
            info.price1 = if ("不详".equals(data[7])) "不详" else data[7]
            info.price2 = if ("不详".equals(data[8])) "不详" else data[8]

            SmokeItemInfo().insertOrUpdateData(info)
            LogUtil.DEBUG(TAG, String.format("%s 插入成功", info.sname))
        } while (true)
        return null
    }

    interface DataInitTaskCallback {

        /**
         * callback when data init completed
         */
        fun afterDataInitCompleted()
    }

}