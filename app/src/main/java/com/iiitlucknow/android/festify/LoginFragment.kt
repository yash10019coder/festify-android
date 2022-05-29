package com.iiitlucknow.android.festify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.iiitlucknow.android.festify.ViewModels.Login_view_model
import com.iiitlucknow.android.festify.data_classes.login_data
import com.iiitlucknow.android.festify.databinding.FragmentLoginBinding
import org.json.JSONException
import org.json.JSONObject

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var vm: Login_view_model
    lateinit var log_msg: String
    lateinit var log_er_msg: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        vm = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(Login_view_model::class.java)
        vm.log_Response.observe(
            viewLifecycleOwner
        ) {
            if (it.isSuccessful) {
                try {
                    val jsonObject = JSONObject(Gson().toJson(it.body()))
                    log_msg = jsonObject.getString("message")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Toast.makeText(context, log_msg, Toast.LENGTH_LONG).show()
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("username", binding.logUser.text.toString().trim())
                startActivity(intent)
            } else {
                try {
                    val jObjError = JSONObject(it.errorBody()!!.string())
                    log_er_msg = jObjError.getString("message")
                    if (log_er_msg.contains("not")) {
                        binding.layLogUser!!.error = "Invalid Credentials"
                        binding.layLogPassword!!.error = "Invalid Credentials"
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.logUser!!.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.layLogUser!!.error = null
            }
        }
        binding.logPassword!!.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.layLogPassword!!.error = null
            }
        }

        binding.changeToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.logBtn!!.setOnClickListener {
            if (binding.logUser!!.text.toString().trim().isEmpty()) {
                binding.layLogUser!!.error = "This Field Cannot Be Empty"
            } else {
                binding.layLogUser!!.error = null
            }
            if (binding.logPassword!!.text.toString().trim().isEmpty()) {
                binding.layLogPassword!!.error = "Password Cannot Be Empty"
            } else {
                binding.layLogPassword!!.error = null
            }
            if (binding.logUser!!.text.toString().trim()
                .isNotEmpty() && binding.logPassword!!.text.toString().trim().isNotEmpty()
            ) {
                vm.checkLogin(
                    login_data(
                        binding.logUser!!.text.toString().trim(),
                        binding.logPassword!!.text.toString().trim()
                    )
                )
            }
        }

        return binding.root
    }
}
