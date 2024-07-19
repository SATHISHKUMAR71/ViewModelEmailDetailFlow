package com.example.customemaildetailflow

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater

class EmailDetailFragment : Fragment() {

    private lateinit var title:TextView
    private lateinit var subtitle:TextView
    private lateinit var content:TextView
    private lateinit var date:TextView
    private var isStarred=false
    private lateinit var viewModel: MainActivityViewModel
    private var isViewed=false
    private lateinit var starImage:ImageView
    private lateinit var profileView:TextView
    private lateinit var view:View
    private lateinit var scrollView:ScrollView
    private var isStarredDetail = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.enter_transition)
        sharedElementReturnTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.exit_transition)
        viewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_email_detail, container, false)
        date = view.findViewById(R.id.date)
        subtitle = view.findViewById(R.id.heading)
        title = view.findViewById(R.id.title)
        content = view.findViewById(R.id.content)
        starImage = view.findViewById(R.id.star)
        view.transitionName = arguments?.getString("transitionName")
        profileView = view.findViewById(R.id.profileView)
        scrollView = view.findViewById(R.id.scrollViewEmailDetail)

        viewModel.selectedEmail.observe(requireActivity(), Observer {email ->
//            view.visibility = View.VISIBLE
            starImage.setOnClickListener {
                if(email.isStarred){
                    email.isStarred = false
                    starImage.setImageResource(R.drawable.baseline_star_outline_24)
                }
                else{
                    email.isStarred = true
                    starImage.setImageResource(R.drawable.baseline_star_24)
                }
            }
            date.text = email.date
            email.isViewed = true
            title.text = email.title
            content.text = email.content
            if(email.isStarred){
                starImage.apply {
                    setImageResource(R.drawable.baseline_star_24)
                }
            }
            else{
                starImage.apply {
                    setImageResource(R.drawable.baseline_star_outline_24)
                }
            }
            profileView.apply {
                text = email.title[0].toString()
            }
            scrollView.scrollTo(0,0)
        })



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }



}