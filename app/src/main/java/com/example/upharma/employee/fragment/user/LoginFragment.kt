package com.example.upharma.employee.fragment.user

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.upharma.MainActivity
import com.example.upharma.R
import com.example.upharma.common.Status
import com.example.upharma.di.Injectable
import com.example.upharma.employee.viewmodel.user.LoginViewModel
import com.example.upharma.model.entity.User
import com.example.upharma.utilities.*

import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        subscribeUi()

        btnLogin.setOnClickListener {
            login()
        }

    }
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }
    private fun subscribeUi() {
        with(viewModel){
            //   deleteRoom()

            mediatorliveLoginResource.observe(viewLifecycleOwner, Observer { resource ->
                if (resource != null) {
                    val status = checkStatus(resource.status,null)
                    if (status && resource.data != null && !resource.data.token.equals("")){
                        val token = resource.data.token!!
                        val userName = edtUserCode.text.toString()
                        val fullName = resource.data.fullName
                        val user = User(userName = userName, fullName = fullName )
                        viewModel.insertToRoom(user)

                        SharedPreferences.saveToken(context!!,token)
                        SharedPreferences.saveUserCode(context!!,userName)

                        InjectorUtils.USER_CODE = userName
                        InjectorUtils.TOKEN = token
                        nextToMainActivity()
                    }

                    if (resource.respCode != null && resource.respCode != 0){
                        showToast(resource.respText!!)
                    }
                }
            })
        }
    }

    private fun checkStatus(status: Status, messageSuccess: String? ): Boolean {
        when (status){
            Status.LOADING ->{
                startProgress()
            }
            Status.SUCCESS_NETWORK ->{
                //  endProcess()
                return true
            }
            Status.SUCCESS_DB ->{
                endProcess()
            }
            Status.ERROR ->{
                endProcess()
                showToast(resources.getString(R.string.error_network_text))
            }
            Status.ERROR_TOKEN ->{
                endProcess()
            }
            Status.ERROR_DB ->{
                endProcess()
            }
        }
        return false
    }

    private fun endProcess(){
        visibilityView(lineMain)
        goneView(progresMain)
    }

    private fun startProgress(){
        goneView(lineMain)
        visibilityView(progresMain)
    }

    fun login() {

        if (edtUserCode.text.toString().equals("")){
            showToast("Nhập tài khoản")
            return
        }
        if (edtPassword.text.toString().equals("")){
            showToast("Nhập mật khẩu")
            return
        }
        val userCode = edtUserCode.text.toString()
        val password = edtPassword.text.toString()
        viewModel.login(userCode,password)

    }

    private fun nextToMainActivity() {
        startActivity(Intent(context, MainActivity::class.java))
        activity!!.finish()
    }

}
