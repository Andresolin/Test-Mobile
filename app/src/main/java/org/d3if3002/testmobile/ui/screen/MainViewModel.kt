package org.d3if3002.testmobile.ui.screen

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import org.d3if3002.testmobile.R
import org.d3if3002.testmobile.model.Catatan

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Catatan> {
        val data = mutableListOf<Catatan>()
        for (i in 1 until  9) {
            data.add(
                Catatan(
                        i.toLong(),
                    "$i",
                    "670622300$i",
                    "D3IF-46-0$i"
                )
            )
        }
        return data
    }
}