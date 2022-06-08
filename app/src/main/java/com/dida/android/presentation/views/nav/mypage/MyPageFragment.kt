package com.dida.android.presentation.views.nav.mypage

import android.content.Intent
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dida.android.GlobalApplication
import com.dida.android.R
import com.dida.android.databinding.FragmentMypageBinding
import com.dida.android.domain.model.nav.mypage.MyPageNFTHolderModel
import com.dida.android.presentation.adapter.MyPageRecyclerViewAdapter
import com.dida.android.presentation.base.BaseFragment
import com.dida.android.presentation.viewmodel.nav.mypage.MyPageViewModel
import com.dida.android.presentation.views.login.LoginActivity
import com.dida.android.util.GridSpacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding, MyPageViewModel>(R.layout.fragment_mypage) {

    private val TAG = "MyPageFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_mypage

    override val viewModel : MyPageViewModel by viewModels()


    override fun initStartView() {
        //loginCheck()
        initToolbar()
        initSpinner()
        initRecyclerView()
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun loginCheck(){
        val accessToken = GlobalApplication.mySharedPreferences.getAccessToken()
        if(accessToken.isNullOrEmpty()){
            val loginIntent = Intent(activity, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun initToolbar(){
        binding.toolbar.inflateMenu(R.menu.menu_mypage_toolbar)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_wallet ->{

                }
                R.id.action_example1 ->{

                }
            }
            true
        }
    }

    private fun initSpinner(){
        ArrayAdapter.createFromResource(requireContext(),R.array.mypage_spinner_list, R.layout.holder_mypage_nft_type_spinner)
            .also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
    }

    private fun initRecyclerView(){
        val list = mutableListOf(
            MyPageNFTHolderModel("https://movie-phinf.pstatic.net/20190417_250/1555465284425i6WQE_JPEG/movie_image.jpg?type=m665_443_2","user name here","NFT name here",1.65),
            MyPageNFTHolderModel("https://movie-phinf.pstatic.net/20190417_250/1555465284425i6WQE_JPEG/movie_image.jpg?type=m665_443_2","user name here","NFT name here",1.65),
            MyPageNFTHolderModel("https://movie-phinf.pstatic.net/20190417_250/1555465284425i6WQE_JPEG/movie_image.jpg?type=m665_443_2","user name here","NFT name here",1.65),
            MyPageNFTHolderModel("https://movie-phinf.pstatic.net/20190417_250/1555465284425i6WQE_JPEG/movie_image.jpg?type=m665_443_2","user name here","NFT name here",1.65),
            MyPageNFTHolderModel("https://movie-phinf.pstatic.net/20190417_250/1555465284425i6WQE_JPEG/movie_image.jpg?type=m665_443_2","user name here","NFT name here",1.65)
        )

        binding.recyclerView.apply {
            adapter = MyPageRecyclerViewAdapter(list)
            layoutManager = GridLayoutManager(requireContext(),2)
            addItemDecoration(GridSpacing(30,30))
        }
    }
}