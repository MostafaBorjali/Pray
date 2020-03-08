package com.borjali.mostafa.pray.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*


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


