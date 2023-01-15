package com.example.kemetnews

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabItem
import androidx.viewpager.widget.ViewPager

import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.os.Bundle

import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import android.content.Intent

import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var homeTab: TabItem? = null
    private var sportsTab: TabItem? = null
    private var healthTab: TabItem? = null
    private var foodTab: TabItem? = null
    private var politicsTab: TabItem? = null
    private var environmentTab: TabItem? = null
    private var technologyTab: TabItem? = null
    private var scienceTab: TabItem? = null
    private var entertainmentTab: TabItem? = null
    private var businessTab: TabItem? = null

    private var viewPager: ViewPager? = null
    private var fragmentAdapter: FragmentAdapter? = null
    private var toolbar: Toolbar? = null
    private var button: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        homeTab = findViewById(R.id.home)
        sportsTab = findViewById(R.id.sports)
        healthTab = findViewById(R.id.health)
        foodTab = findViewById(R.id.food)
        politicsTab = findViewById(R.id.politics)
        environmentTab = findViewById(R.id.environment)
        technologyTab = findViewById(R.id.technology)
        scienceTab = findViewById(R.id.science)
        entertainmentTab = findViewById(R.id.entertainment)
        businessTab = findViewById(R.id.business)

        button = findViewById(R.id.search_button)
        viewPager = findViewById(R.id.fragment_container)
        tabLayout = findViewById(R.id.tab_bar)
        fragmentAdapter = FragmentAdapter(supportFragmentManager, 10)
        viewPager?.adapter = fragmentAdapter

        tabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
                if (tab.position == 0 || tab.position == 1 || tab.position == 2 ||
                    tab.position == 3 || tab.position == 4 || tab.position == 5 ||
                    tab.position == 6 || tab.position == 7 || tab.position == 8 ||
                    tab.position == 9) {
                    fragmentAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPager?.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        button?.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    SearchActivity::class.java
                )
            )
        }
    }
}