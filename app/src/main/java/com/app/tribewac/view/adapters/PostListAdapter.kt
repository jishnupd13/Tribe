package com.app.tribewac.view.adapters

import com.app.tribewac.R
import com.app.tribewac.base.BaseAdapter
import com.app.tribewac.data.models.Post
import com.app.tribewac.view.listeners.AdapterClickListener

/**
 * Created By antony on 6/3/2019.
 */
class PostListAdapter(val listener: AdapterClickListener<Post>) :
    BaseAdapter<Post>() {

    override fun getAdapterListener(): AdapterClickListener<Post> {
        return listener
    }

    override fun getLayoutIdForViewType(viewType: Int): Int {
        return R.layout.item_post
    }

    override fun compare(oldList: Post, newList: Post): Boolean {
        return oldList.id == newList.id
    }
}

