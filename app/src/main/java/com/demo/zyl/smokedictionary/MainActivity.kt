package com.demo.zyl.smokedictionary

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.demo.zyl.smokedictionary.bean.SmokeItemInfo
import com.demo.zyl.smokedictionary.fragment.SmokeFragment
import com.demo.zyl.smokedictionary.tasks.DataInitTask
import com.demo.zyl.smokedictionary.util.LogUtil

class MainActivity : AppCompatActivity(), DataInitTask.DataInitTaskCallback {

    companion object {
        const val TAG: String = "MainActivity"

        var context: Context? = null
    }

    private var navigation: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_mainland -> {
                showMainlandData()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_overseas -> {
                showForeignData()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_none_mainland -> {
                showHKMTData()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                showHistoryData()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun showMainlandData() {
        var fragment: SmokeFragment = SmokeFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("brand", "mainland")
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun showForeignData() {
        var fragment: SmokeFragment = SmokeFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("brand", "foreign")
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun showHKMTData() {
        var fragment: SmokeFragment = SmokeFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("brand", "HKMT")
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun showHistoryData() {
        var fragment: SmokeFragment = SmokeFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("brand", "history")
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initView()
        initEvent()
        initData()
    }

    private fun initView() {
        context = this
        navigation = findViewById(R.id.navigation) as BottomNavigationView
    }

    private fun initEvent() {
        navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initData() {
        val count: Int = SmokeItemInfo().findDataCount()
        if (count == 0) {
            LogUtil.DEBUG(TAG, "首次使用初始化数据库")
            var task: DataInitTask = DataInitTask()
            task.setContext(this)
            task.setCallback(this)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        } else {
            LogUtil.DEBUG(TAG, "非首次使用")
            showMainlandData()
        }

    }

    // ***********************************************
    /**
     * callback when data init completed
     */
    override fun afterDataInitCompleted() {
        LogUtil.DEBUG(TAG, "afterDataInitCompleted")
        showMainlandData()
    }
}
