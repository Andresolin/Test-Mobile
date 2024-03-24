package org.d3if3002.testmobile.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3002.testmobile.model.Catatan

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Catatan> {
        val data = mutableListOf<Catatan>()
        for (i in 29 downTo 20) {
            data.add(
                Catatan(
                        i.toLong(),
                    "Kuliah Mobpro $i Feb",
                    "Yey, hari ini belajar membuat aplikasi android counter dan berhasil. hehe.. Mudah2an modul selanjutnya juga lancar. Amin.",
                    "2024-02-$i 12:34:56"
                )
            )
        }
        return data
    }
}