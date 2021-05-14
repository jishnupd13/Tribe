package com.app.tribewac.paging

import androidx.paging.DataSource
import com.app.tribewac.data.models.Post

/**
 * Created By antony on 12/18/2019.
 */
class SampleDataSourceFactory : DataSource.Factory<Int,Post>() {
    override fun create(): DataSource<Int, Post> {
        return SampleDataSource()
    }
}