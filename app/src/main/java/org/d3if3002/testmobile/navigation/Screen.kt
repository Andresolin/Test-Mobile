package org.d3if3002.testmobile.navigation

sealed class Screen(val route : String) {
    data object Home: Screen("mainScreen")
}