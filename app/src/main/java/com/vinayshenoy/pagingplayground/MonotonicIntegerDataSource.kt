package com.vinayshenoy.pagingplayground

import androidx.paging.ItemKeyedDataSource

class MonotonicIntegerDataSource : ItemKeyedDataSource<Int, Int>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int>) {
        val initialKey = params.requestedInitialKey ?: 0
        val initialDataSet = (initialKey + 1..(initialKey + params.requestedLoadSize)).toList()
        callback.onResult(initialDataSet)
        println("wtf:data source:initial data set:$initialDataSet")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int>) {
        val key = params.key
        val nextDataSet = (key + 1..(key + params.requestedLoadSize)).toList()
        callback.onResult(nextDataSet)
        println("wtf:data source:next data set:$nextDataSet")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int>) {
        val key = params.key
        val previousDataSet = ((key - params.requestedLoadSize).coerceAtLeast(1) until key).toList()
        callback.onResult(previousDataSet)
        println("wtf:data source:prev data set:$previousDataSet")
    }

    override fun getKey(item: Int): Int = item
}
