package com.vinayshenoy.pagingplayground.infiniteintegers

import androidx.paging.PageKeyedDataSource

class IntegerDataSource(
    private val initialPageNumber: Int = 0
) : PageKeyedDataSource<Int, Int>() {

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Int>) {
    val pageSize = params.requestedLoadSize
    val firstPage = computePage(pageNumber = initialPageNumber, pageSize = pageSize)
    callback.onResult(firstPage, initialPageNumber * pageSize, Int.MAX_VALUE, null, 1)
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Int>) {
    val pageNumber = params.key
    val nextPage = computePage(pageNumber = pageNumber, pageSize = params.requestedLoadSize)
    callback.onResult(nextPage, pageNumber + 1)
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Int>) {
    val pageNumber = params.key
    if (pageNumber < 0) {
      callback.onResult(emptyList(), null)
    } else {
      val previousPage = computePage(pageNumber = pageNumber, pageSize = params.requestedLoadSize)
      callback.onResult(previousPage, pageNumber - 1)
    }
  }

  private fun computePage(pageNumber: Int, pageSize: Int): List<Int> {
    return ((pageSize * pageNumber + 1)..(pageSize * pageNumber + pageSize)).toList()
  }
}
