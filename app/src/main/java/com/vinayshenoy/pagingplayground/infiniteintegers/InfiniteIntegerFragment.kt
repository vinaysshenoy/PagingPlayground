package com.vinayshenoy.pagingplayground.infiniteintegers

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinayshenoy.pagingplayground.R
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_infinite_integers.*
import java.util.concurrent.Executors

class InfiniteIntegerFragment : Fragment() {

  private var disposable: Disposable? = null

  private val adapter = IntegerAdapter()

  private val fetchExecutor = Executors.newSingleThreadExecutor()

  private val handler = Handler()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_infinite_integers, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupList()
    setupDataSource()
  }

  private fun setupList() {
    integerList.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = this@InfiniteIntegerFragment.adapter
    }
  }

  private fun setupDataSource() {
    disposable = Single
        .fromCallable { constructPagedList() }
        .subscribeOn(Schedulers.computation())
        .subscribe { list -> adapter.submitList(list) }
  }

  private fun constructPagedList(): PagedList<Int> {
    val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(false)
        .build()

    return PagedList
        .Builder<Int, Int>(IntegerDataSource(), config)
        .setFetchExecutor(fetchExecutor)
        .setNotifyExecutor { handler.post(it) }
        .build()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    disposable?.dispose()
  }
}
