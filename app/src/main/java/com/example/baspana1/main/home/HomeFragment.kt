package com.example.baspana1.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.baspana1.R
import com.example.baspana1.authorization.registration.RegistrationViewModel
import com.example.baspana1.databinding.FragmentHomeBinding
import com.example.baspana1.main.home.adapter.AdvertsAdapter
import com.example.baspana1.model.adverts.Adverts
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Math.abs

class HomeFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
   private val adapter =  AdvertsAdapter()

   private val viewModel : HomeFragmentViewmodel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewmodel::class.java)
    }

   private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeFragmentViewmodel = viewModel
        binding.lifecycleOwner = this
        binding.homeFragmentViewmodel = viewModel

        binding.homeRecyclerView.adapter = adapter


        viewModel.advertsList.observe(this, {
            adapter.setAdverts(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                shimmerFrameLayout.startShimmer()
            } else {
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
            }
        })

        viewModel.getAdvertsList()

      /*  binding.appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (appBarLayout != null) {
                    if(abs(verticalOffset) > appBarLayout.totalScrollRange) {
                        appBarLayout.se
                    }
                }
            }

        })*/

        return binding.root
    }






}