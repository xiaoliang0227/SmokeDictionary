package com.demo.zyl.smokedictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.demo.zyl.smokedictionary.R
import com.demo.zyl.smokedictionary.bean.SmokeItemInfo

/**
 * Created by zhaoyongliang on 2017/5/27.
 */
class SmokeListAdapter(var context: Context, var data: MutableList<SmokeItemInfo>?): BaseAdapter() {

    companion object {
        const val TAG: String = "SmokeListAdapter"
    }

    fun refresh(data: MutableList<SmokeItemInfo>) {
        this.data = data
        notifyDataSetInvalidated()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View? = null
        if (convertView == null) {
            holder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.smoke_item, parent, false)
            holder.photo = view.findViewById(R.id.photo) as ImageView
            holder.name = view.findViewById(R.id.name) as TextView
            holder.type = view.findViewById(R.id.type) as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val item: SmokeItemInfo = data!![position]
        Glide.with(context).load(item.photo).into(holder.photo)
        holder.name?.text = item.sname
        holder.type?.text = item.type

        return view!!
    }

    override fun getItem(position: Int): Any? {
        return if (null == data) null else data!![position]
    }

    override fun getItemId(position: Int): Long {
        return if (null == data) 0 else position.toLong()
    }

    override fun getCount(): Int {
        return if (null == data) 0 else data!!.size
    }


    class ViewHolder {
        var photo: ImageView? = null

        var name: TextView? = null

        var type: TextView? = null
    }
}