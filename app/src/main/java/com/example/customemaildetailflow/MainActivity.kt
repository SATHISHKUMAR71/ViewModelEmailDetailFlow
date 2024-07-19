package com.example.customemaildetailflow

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {



    private var appbar:AppBarLayout? = null
    private var toolbar:MaterialToolbar? = null
    private lateinit var emailList:MutableList<MutableLiveData<Email>>
    private var isDualPane = false
    val fragmentEmailList = EmailListFragment()
    val fragmentEmailDetail = EmailDetailFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        emailList = viewModel.emailListVM
        appbar = findViewById(R.id.appBarLayout)
        toolbar = findViewById(R.id.toolbar)
        if((savedInstanceState == null)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentEmailList, fragmentEmailList, "Email Fragment List")
                .commit()
        }
        if(resources.configuration.screenWidthDp>=700){
            onRestart()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentEmailList,fragmentEmailList,"Email Fragment List")
                .replace(R.id.fragmentEmailDetail,fragmentEmailDetail)
                .commit()
        }

//        navigationButton Listener
        findViewById<MaterialToolbar?>(R.id.toolbar)?.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
            appbar?.findViewById<MaterialToolbar>(R.id.toolbar)?.apply {
                title = "Email"
                navigationIcon = ContextCompat.getDrawable(baseContext,R.drawable.baseline_menu_24)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

