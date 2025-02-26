package com.borjali.mostafa.pray.utils.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/*@BindingAdapter("app:refreshing")
    fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isrefresh: Boolean) {
        swipeRefreshLayout.setRefreshing(isrefresh)
    }*/

@BindingAdapter("app:direction")
fun setDirection(recyclerView: RecyclerView, orientation: String) {


    if (orientation == "vertical") {
        recyclerView.itemAnimator = DefaultItemAnimator()
        val llm = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = llm
        recyclerView.isNestedScrollingEnabled = false
        val decoration = DividerItemDecoration(recyclerView.context, 0)
        recyclerView.addItemDecoration(decoration)
    }

    if (orientation == "gridView") {
        recyclerView.itemAnimator = DefaultItemAnimator()
        val llm = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = llm
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 5)

    }
    if (orientation == "menuGrid") {
        recyclerView.itemAnimator = DefaultItemAnimator()
        val llm = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, true)
        recyclerView.layoutManager = llm
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)

    }
}
/*@BindingAdapter("app:setarray")
fun setData(appCompatSpinner: AppCompatSpinner,arrayList: ArrayList<String>?){

    arrayList?.let {
        val stationNameArrayAdapter = ArrayAdapter<String>(appCompatSpinner.context, R.layout.item_spinner, arrayList)
        stationNameArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        appCompatSpinner.adapter = stationNameArrayAdapter
    }

    //stationNameArrayAdapter.notifyDataSetChanged()
}*/


