package com.dida.android.presentation.views

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dida.android.R
import com.dida.android.databinding.FragmentNicknameBinding
import com.dida.android.presentation.base.BaseFragment
import com.dida.android.presentation.viewmodel.NicknameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding, NicknameViewModel>(R.layout.fragment_nickname) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_nickname // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel : NicknameViewModel by viewModels()
    lateinit var navController: NavController

    override fun initStartView() {
        navController = Navigation.findNavController(requireView())
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        binding.nicknameEdit.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""
            var job: Job? = null

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                job?.cancel()
                searchFor = searchText
                job = lifecycleScope.launch {
                    delay(500)  //debounce timeOut <- 현재 0.5초
                    if (searchText != searchFor)
                        return@launch

                    Log.d("lifecycle_scope", searchText.toString())

                    // nickname check api
//                    viewModel.getBookamrk(searchFor)
                }
            }
        })
    }
}