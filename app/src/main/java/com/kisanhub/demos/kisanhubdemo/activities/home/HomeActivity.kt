package com.kisanhub.demos.kisanhubdemo.activities.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.kisanhub.demos.kisanhubdemo.R
import com.kisanhub.demos.kisanhubdemo.activities.BaseActivity
import com.kisanhub.demos.kisanhubdemo.activities.home.adapter.CountriesAdapter
import com.kisanhub.demos.kisanhubdemo.activities.home.adapter.PopupCallback
import com.kisanhub.demos.kisanhubdemo.activities.whether.WeatherActivity
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

class HomeActivity : BaseActivity(), PopupCallback {

    private lateinit var metrics: Metrics
    private lateinit var selectedCountry: String

    companion object {
        fun start(from: AppCompatActivity) {
            from.startActivity(Intent(from, HomeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initialize()
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        menu!!.setHeaderTitle("Please select metrics")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        metrics =
                when (item!!.itemId) {
                    R.id.rainfall -> {
                        Metrics.RAINFALL
                    }
                    R.id.tMin -> {
                        Metrics.TMIN
                    }
                    R.id.tMax -> {
                        Metrics.TMAX
                    }
                    else -> {
                        Metrics.TMAX
                    }
                }


        WeatherActivity.start(this@HomeActivity, metrics.getMetric(), selectedCountry)
        Timber.d(metrics.getMetric())
        return true
    }


    private fun initialize() {
        val countries = arrayListOf("UK", "England", "Scotland", "Wales")
        val adapter = CountriesAdapter(this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        countryRv.addItemDecoration(dividerItemDecoration)
        countryRv.layoutManager = layoutManager
        countryRv.adapter = adapter
        adapter.update(countries)

        registerForContextMenu(countryRv)
    }


    override fun onPopupCall(countryName: String, itemView: View) {
        selectedCountry = countryName
        openContextMenu(itemView)
    }
}
