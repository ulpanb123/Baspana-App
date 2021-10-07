package com.example.baspana1.main.overview

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentOverviewBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding : FragmentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        val edittext = binding.searchInputLayout
        val filterIcon = binding.filterImage
        binding.appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val percentage : Float = (abs(verticalOffset)/ appBarLayout?.totalScrollRange!!).toFloat()
                if(abs(verticalOffset) == appBarLayout.totalScrollRange) {
                    edittext.visibility = GONE
                    binding.toolbarView.setNavigationIcon(R.drawable.ic_home_action_search)
                }else if(verticalOffset == 0) {
                    edittext.visibility = View.VISIBLE
                }
            }

        })
        return binding.root

    }

}