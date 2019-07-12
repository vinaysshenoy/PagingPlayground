package com.vinayshenoy.pagingplayground

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.computation
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null

    private val adapter = IntegerAdapter()

    private val fetchExecutor = Executors.newSingleThreadExecutor()

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }

        disposable = Single
            .fromCallable {
                val initialKey = savedInstanceState?.getInt("visible_item") ?: 0
                println("wtf:onCreate:initial key:$initialKey")

                PagedList
                    .Builder<Int, Int>(MonotonicIntegerDataSource2(), 10)
                    .setFetchExecutor(fetchExecutor)
                    .setNotifyExecutor { handler.post(it) }
                    .setInitialKey(initialKey)
                    .build()
            }
            .subscribeOn(computation())
            .subscribe { list -> adapter.submitList(list) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*val position = (list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val pageSize = adapter.currentList?.config?.pageSize!!
        println("wtf:onSaveInstanceState:position:$position")
        println("wtf:onSaveInstanceState:page size:$pageSize")
        outState.putInt("visible_item", position / pageSize)*/
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
