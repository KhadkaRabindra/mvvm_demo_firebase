package com.maxx.nordvaest.utils.extensions

import android.R
import android.widget.ArrayAdapter
import fr.ganfra.materialspinner.MaterialSpinner

fun setHintSpinnerAdapter(dataList: ArrayList<String>, spinner: MaterialSpinner) {
    val adapter = ArrayAdapter(spinner.context, R.layout.simple_spinner_item, dataList)
    adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}


fun MaterialSpinner.setHintSpinnerAdapter(dataList: List<String>?) {

    var strDataList = ArrayList<String>()

    if (dataList != null)
        for (data in dataList)
            if (data != null)
                strDataList.add(data)

    setHintSpinnerAdapter(strDataList, this)
}

/**
 * get spinner value
 */
fun MaterialSpinner.getSpinnerValue(): Any? {
    return selectedItem
}