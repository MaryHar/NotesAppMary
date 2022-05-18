package com.example.myprojectnotesapp.ui.SplashAndIntroScreen.RegisterOrLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.databinding.FragmentLogOutBinding
import com.roman.authenticationfirebasemvvm.viewmodel.Authviewmodel


class LogOutFragment : Fragment() {

    private var _binding : FragmentLogOutBinding?= null
    private val binding get() = _binding!!
    private val viewModel : Authviewmodel by viewModels()
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getuserData().observe(this, Observer {
            if(it != null){
                binding.showemail.text =it.email
            }else{
                navController.navigate(R.id.action_logOutFragment_to_loginFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogOutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnSignOut.setOnClickListener {
            viewModel.signout()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
            }
    }
}