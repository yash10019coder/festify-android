package com.iiitlucknow.android.festify.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iiitlucknow.android.festify.R
import com.iiitlucknow.android.festify.databinding.FragmentClickBinding
import com.iiitlucknow.android.model.EventRecyclerItemClick

class EventSubcategoryFragment : Fragment() {
    private var _binding: FragmentClickBinding? = null
    private val binding get() = _binding!!
    val args: EventSubcategoryFragmentArgs by navArgs()
    lateinit var adapter: EventSubcategoryAdapter
    private var m_android: MutableList<EventRecyclerItemClick> = mutableListOf(
        EventRecyclerItemClick(
            R.drawable.android_img,
            R.string.android,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.android_img,
            R.string.android,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.android_img,
            R.string.android,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.android_img,
            R.string.android,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.android_img,
            R.string.android,
            R.string.date
        )
    )
    private var m_web: MutableList<EventRecyclerItemClick> = mutableListOf(
        EventRecyclerItemClick(
            R.drawable.web_development_img,
            R.string.web,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.web_development_img,
            R.string.web,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.web_development_img,
            R.string.web,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.web_development_img,
            R.string.web,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.web_development_img,
            R.string.web,
            R.string.date
        )
    )
    private var m_cyber: MutableList<EventRecyclerItemClick> = mutableListOf(
        EventRecyclerItemClick(
            R.drawable.cybersecurity_img,
            R.string.cyber_sec,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.cybersecurity_img,
            R.string.cyber_sec,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.cybersecurity_img,
            R.string.cyber_sec,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.cybersecurity_img,
            R.string.cyber_sec,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.cybersecurity_img,
            R.string.cyber_sec,
            R.string.date
        )
    )
    private var m_ai: MutableList<EventRecyclerItemClick> = mutableListOf(
        EventRecyclerItemClick(
            R.drawable.ai_img,
            R.string.ai,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.ai_img,
            R.string.ai,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.ai_img,
            R.string.ai,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.ai_img,
            R.string.ai,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.ai_img,
            R.string.ai,
            R.string.date
        )
    )
    private var m_design: MutableList<EventRecyclerItemClick> = mutableListOf(
        EventRecyclerItemClick(
            R.drawable.designing_img,
            R.string.design,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.designing_img,
            R.string.design,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.designing_img,
            R.string.design,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.designing_img,
            R.string.design,
            R.string.date
        ),
        EventRecyclerItemClick(
            R.drawable.designing_img,
            R.string.design,
            R.string.date
        )
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
            adapter = EventSubcategoryAdapter(m_android)
        } else if (args.event == resources.getString(R.string.web)) {
            adapter = EventSubcategoryAdapter(m_web)
        } else if (args.event == resources.getString(R.string.design)) {
            adapter = EventSubcategoryAdapter(m_design)
        } else if (args.event == resources.getString(R.string.ai)) {
            adapter = EventSubcategoryAdapter(m_ai)
        } else if (args.event == resources.getString(R.string.cyber_sec)) {
            adapter = EventSubcategoryAdapter(m_cyber)
        }
        binding.clickRecycler.adapter = adapter
        binding.clickRecycler.setHasFixedSize(true)
        return binding.root
    }
}
