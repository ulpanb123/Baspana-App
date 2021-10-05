package com.example.baspana1.main.home

import com.example.baspana1.R

class HomeFragmentViewmodel {

    var list = ArrayList<HomeActionItems>()

    init {
        list.add(HomeActionItems(R.drawable.ic_home_action_search, "Поиск"))
        list.add(HomeActionItems(R.drawable.ic_home_action_consulting, "Консультация" ))
        list.add(HomeActionItems(R.drawable.ic_home_action_buy, "Купить"))
        list.add(HomeActionItems(R.drawable.ic_home_action_sell, "Продать"))
    }
}