package com.demo.zyl.smokedictionary.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ListView
import com.demo.zyl.smokedictionary.MainActivity
import com.demo.zyl.smokedictionary.R
import com.demo.zyl.smokedictionary.SmokeDetailActivity
import com.demo.zyl.smokedictionary.adapter.SmokeListAdapter
import com.demo.zyl.smokedictionary.bean.SmokeItemInfo
import com.demo.zyl.smokedictionary.util.CommonConsts
import kotlinx.android.synthetic.main.smoke_fragment.*

/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class SmokeFragment: Fragment(), AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    var main: View? = null

    var smokeList: ListView? = null

    var data: MutableList<SmokeItemInfo>? = ArrayList()

    var adapter: SmokeListAdapter? = null

    var brand: String? = "mainland"

    val handler: Handler = Handler()

    var lastIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        main = inflater?.inflate(R.layout.smoke_fragment, container, false)
        smokeList = main?.findViewById(R.id.smoke_list) as ListView
        return main
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()
    }

    private fun initEvent() {
        smokeList?.onItemClickListener = this
        smokeList?.setOnScrollListener(this)

    }

    private fun initData() {
        lastIndex = 0
        brand = arguments.getString("brand")
        var table: SmokeItemInfo = SmokeItemInfo()
        data = table.findData(brand!!, 0, CommonConsts.PAGE_PER_SIZE)

        adapter = SmokeListAdapter(MainActivity.context!!, data)
        smokeList?.adapter = adapter
        smokeList?.setSelection(lastIndex - 1)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var intent: Intent = Intent(MainActivity.context, SmokeDetailActivity::class.java)
        intent.putExtra("info", data!![position])
        startActivity(intent)
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        var count: Int = smokeList!!.count
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && smokeList!!.lastVisiblePosition == count - 1) {
            var lastChild: View = view!!.getChildAt(view.childCount - 1)
            val distance: Int = view.bottom - lastChild.bottom
            if (distance == 0) {
                lastIndex = count
                delayShowRefreshLoading()
            }
        }
    }

    private fun delayShowRefreshLoading() {
        handler.postDelayed({
            showRefreshLoading()
        }, 500)
    }

    private fun showRefreshLoading() {
        loading.visibility = View.VISIBLE
        handler.postDelayed({
            refreshData()
        }, 1000)
    }

    private fun refreshData() {
        loading.visibility = View.GONE
        val table: SmokeItemInfo = SmokeItemInfo()
        val list: MutableList<SmokeItemInfo>? = table.findData(brand!!, smokeList!!.childCount, CommonConsts.PAGE_PER_SIZE)
        if (null != list) {
            data!!.addAll(list.asIterable())
        }
        adapter!!.refresh(data!!)
        smokeList?.setSelection(lastIndex - 1)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

    }
}