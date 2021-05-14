package com.antony.mybasepackage.paging

import androidx.paging.DataSource
import com.antony.mybasepackage.data.models.Post

/**
 * Created By antony on 12/18/2019.
 */
class SampleDataSourceFactory : DataSource.Factory<Int,Post>() {
    override fun create(): DataSource<Int, Post> {
        return SampleDataSource()
    }
}