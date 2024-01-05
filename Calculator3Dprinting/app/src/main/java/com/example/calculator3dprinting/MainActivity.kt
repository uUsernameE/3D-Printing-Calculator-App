package com.example.calculator3dprinting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Starting")

        val mViewPager: ViewPager = findViewById(R.id.container)
        setupViewPager(mViewPager)

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(mViewPager)
    }

        private fun setupViewPager(viewPager: ViewPager) {
            val adapter = SectionsPagerAdapter(supportFragmentManager)
            adapter.addFragment(CostsFragment(), "Cost")
            adapter.addFragment(PrinterFragment(), "Printers")
            viewPager.adapter = adapter
        }
    }

    /*
    //https://www.geeksforgeeks.org/how-to-implement-tabs-viewpager-and-fragment-in-android-using-kotlin/
    //This "ViewPagerAdapter" class overrides functions which are
    //necessary to get information about which item is selected
    //by user, what is title for selected item and so on.
    class ViewPagerAdapter : FragmentPagerAdapter {

        //objects of arraylist. One is of Fragment type and
        //another one is of String type.
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        //this is a secondary constructor of ViewPagerAdapter class.
        public constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        //returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        //returns which item is selected from arraylist of titles.
        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        //returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        //this function adds the fragment and title in 2 separate  arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
        */