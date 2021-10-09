package com.example.baspana1.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentHomeBinding
import com.example.baspana1.main.NetworkFailFragment
import com.example.baspana1.main.home.adapter.AdvertsAdapter
import com.example.baspana1.model.adverts.AdvertItem
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

   private val adapter =  AdvertsAdapter{ advert ->
       adapterOnClick(advert)
   }

    private fun adapterOnClick(advert: AdvertItem) {
        val advertId = advert.id
        this.findNavController().navigate(HomeFragmentDirections.actionFromHomeToDetails())
    }

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

        binding.homeRecyclerView.adapter = adapter


        viewModel.advertsList.observe(this, {
            adapter.setAdverts(it)
        })

        viewModel.errorMessage.observe(this, {
            binding.nestedScrollView.visibility = View.GONE
            binding.networkFailLayout.visibility = View.VISIBLE
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            binding.homeActionGrid.visibility = View.GONE
            binding.advertsTextView.visibility = View.GONE
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val childFragment: Fragment = NetworkFailFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.network_fail_container, childFragment).commit()
    }
}