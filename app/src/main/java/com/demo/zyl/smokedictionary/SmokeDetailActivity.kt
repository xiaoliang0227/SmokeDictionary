package com.demo.zyl.smokedictionary

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.demo.zyl.smokedictionary.bean.SmokeItemInfo
import kotlinx.android.synthetic.main.act_smoke_detail.*


/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class SmokeDetailActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "SmokeDetailActivity"
    }

    var info: SmokeItemInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_smoke_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
    }

    private fun initData() {
        var intent: Intent = intent
        if (intent.hasExtra("info")) {
            info = intent.getSerializableExtra("info") as SmokeItemInfo
            title = info?.sname
            Glide.with(this).load(info?.photo).into(photo)
            type.text = info?.type
            name.text = info?.sname
            if ("不详".equals(info?.tarContent)) {
                none_tar.visibility = View.VISIBLE
            } else {
                tar.visibility = View.VISIBLE
                Glide.with(this).load(info?.tarContent).into(tar)
            }

            if ("不详".equals(info?.nicotine)) {
                none_nicotine.visibility = View.VISIBLE
            } else {
                nicotine.visibility = View.VISIBLE
                Glide.with(this).load(info?.nicotine).into(nicotine)
            }

            if ("不详".equals(info?.co)) {
                none_co.visibility = View.VISIBLE
            } else {
                co.visibility = View.VISIBLE
                Glide.with(this).load(info?.co).into(co)
            }

            if ("不详".equals(info?.price1)) {
                none_price.visibility = View.VISIBLE
            } else {
                has_price.visibility = View.VISIBLE
                Glide.with(this).load(info?.price1).into(price)
            }

            if ("不详".equals(info?.price2)) {
                none_price1.visibility = View.VISIBLE
            } else {
                has_price1.visibility = View.VISIBLE
                Glide.with(this).load(info?.price2).into(price1)
            }

        }
    }
}