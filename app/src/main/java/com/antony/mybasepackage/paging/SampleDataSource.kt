package com.antony.mybasepackage.paging

import androidx.paging.PageKeyedDataSource
import com.antony.mybasepackage.data.models.Post

/**
 * Created By antony on 12/18/2019.
 */
class SampleDataSource : PageKeyedDataSource<Int, Post>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Post>
    ) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {

    }

}