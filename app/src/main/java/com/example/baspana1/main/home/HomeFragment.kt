package com.example.baspana1.main.home

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthEnterNumberBinding
import com.example.baspana1.databinding.FragmentHomeBinding
import com.example.baspana1.main.home.adapter.HomeActionsAdapter
import com.example.baspana1.main.home.model.HomeActionItems

class HomeFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
   private lateinit var adapter : HomeActionsAdapter
   private lateinit var list : ArrayList<HomeActionItems>

   private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_enter_number, container, false)
        initData()
        initView()
        setData()
        return binding.root
    }

    private fun initView() {
        recyclerView = binding.homeActionsRecyclerView
    }

    private fun setData() {
        adapter = HomeActionsAdapter()

        recyclerView.hasFixedSize()
        val gridLayoutManager = GridLayoutManager(this.context, 2)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 2
            }
        }
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 10
                outRect.bottom = 10
                outRect.left = 10
                outRect.right = 10
            }
        })
        recyclerView.adapter = adapter
    }

    private fun initData() {
        list.add(HomeActionItems(R.drawable.ic_home_action_search, "Поиск"))
        list.add(HomeActionItems(R.drawable.ic_home_action_consulting, "Консультация" ))
        list.add(HomeActionItems(R.drawable.ic_home_action_buy, "Купить"))
        list.add(HomeActionItems(R.drawable.ic_home_action_sell, "Продать"))
    }




}