package com.iiitlucknow.festify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iiitlucknow.festify.Adapters.clickAdapter
import com.iiitlucknow.festify.data_classes.recyclerItemClick
import com.iiitlucknow.festify.databinding.FragmentClickBinding

class ClickFragment : Fragment() {
    private var _binding: FragmentClickBinding? = null
    private val binding get() = _binding!!
    val args: ClickFragmentArgs by navArgs()
    lateinit var adapter: clickAdapter
    private var m_android: MutableList<recyclerItemClick> = mutableListOf(
        recyclerItemClick(R.drawable.android_img, R.string.android, R.string.date),
        recyclerItemClick(R.drawable.android_img, R.string.android, R.string.date),
        recyclerItemClick(R.drawable.android_img, R.string.android, R.string.date),
        recyclerItemClick(R.drawable.android_img, R.string.android, R.string.date),
        recyclerItemClick(R.drawable.android_img, R.string.android, R.string.date)
    )
    private var m_web: MutableList<recyclerItemClick> = mutableListOf(
        recyclerItemClick(R.drawable.web_development_img, R.string.web, R.string.date),
        recyclerItemClick(R.drawable.web_development_img, R.string.web, R.string.date),
        recyclerItemClick(R.drawable.web_development_img, R.string.web, R.string.date),
        recyclerItemClick(R.drawable.web_development_img, R.string.web, R.string.date),
        recyclerItemClick(R.drawable.web_development_img, R.string.web, R.string.date)
    )
    private var m_cyber: MutableList<recyclerItemClick> = mutableListOf(
        recyclerItemClick(R.drawable.cybersecurity_img, R.string.cyber_sec, R.string.date),
        recyclerItemClick(R.drawable.cybersecurity_img, R.string.cyber_sec, R.string.date),
        recyclerItemClick(R.drawable.cybersecurity_img, R.string.cyber_sec, R.string.date),
        recyclerItemClick(R.drawable.cybersecurity_img, R.string.cyber_sec, R.string.date),
        recyclerItemClick(R.drawable.cybersecurity_img, R.string.cyber_sec, R.string.date)
    )
    private var m_ai: MutableList<recyclerItemClick> = mutableListOf(
        recyclerItemClick(R.drawable.ai_img, R.string.ai, R.string.date),
        recyclerItemClick(R.drawable.ai_img, R.string.ai, R.string.date),
        recyclerItemClick(R.drawable.ai_img, R.string.ai, R.string.date),
        recyclerItemClick(R.drawable.ai_img, R.string.ai, R.string.date),
        recyclerItemClick(R.drawable.ai_img, R.string.ai, R.string.date)
    )
    private var m_design: MutableList<recyclerItemClick> = mutableListOf(
        recyclerItemClick(R.drawable.designing_img, R.string.design, R.string.date),
        recyclerItemClick(R.drawable.designing_img, R.string.design, R.string.date),
        recyclerItemClick(R.drawable.designing_img, R.string.design, R.string.date),
        recyclerItemClick(R.drawable.designing_img, R.string.design, R.string.date),
        recyclerItemClick(R.drawable.designing_img, R.string.design, R.string.date)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClickBinding.inflate(
            inflater, container, false
        )
        if (args.event == resources.getString(R.string.android)) {
            adapter = clickAdapter(m_android)
        } else if (args.event == resources.getString(R.string.web)) {
            adapter = clickAdapter(m_web)
        } else if (args.event == resources.getString(R.string.design)) {
            adapter = clickAdapter(m_design)
        } else if (args.event == resources.getString(R.string.ai)) {
            adapter = clickAdapter(m_ai)
        } else if (args.event == resources.getString(R.string.cyber_sec)) {
            adapter = clickAdapter(m_cyber)
        }
        binding.clickRecycler.adapter = adapter
        binding.clickRecycler.setHasFixedSize(true)
        return binding.root
    }
}
