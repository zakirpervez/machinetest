package com.kisanhub.demos.kisanhubdemo.activities.whether

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.kisanhub.demos.kisanhubdemo.R
import com.kisanhub.demos.kisanhubdemo.activities.BaseActivity
import com.kisanhub.demos.kisanhubdemo.activities.whether.adapter.WeatherInfoAdapter
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.sources.Metrics
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.util.COUNTRY_EXTRAS
import com.kisanhub.demos.kisanhubdemo.util.METRICS_EXTRAS
import kotlinx.android.synthetic.main.activity_weather.*


class WeatherActivity : BaseActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherObserver: Observer<ApiResponse<List<WhetherInfoEntity>, String>>
    private lateinit var countryName: String
    private lateinit var metricsStr: String

    companion object {
        fun start(from: AppCompatActivity, metrics: String, countryName: String) {
            val intent = Intent(from, WeatherActivity::class.java)
            intent.putExtra(METRICS_EXTRAS, metrics)
            intent.putExtra(COUNTRY_EXTRAS, countryName)
            from.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        initialize()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    private fun initialize() {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherObserver = Observer { it ->
            if (it!!.reponse != null) {
                val weatherInfoList: ArrayList<WhetherInfoEntity> = arrayListOf()
                weatherInfoList.addAll(it.reponse!!)
                populateWeatherInfo(weatherInfoList)
            } else {
                showToast(it.error!!)
            }
            hideProgress()
        }

        metricsStr = intent.extras!!.getString(METRICS_EXTRAS)!!
        countryName = intent.extras!!.getString(COUNTRY_EXTRAS)!!

        supportActionBar!!.title = "$countryName-$metricsStr"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        val metrics: Metrics = when (metricsStr) {
            Metrics.RAINFALL.getMetric() -> Metrics.RAINFALL
            Metrics.TMAX.getMetric() -> Metrics.TMAX
            Metrics.TMIN.getMetric() -> Metrics.TMIN
            else -> Metrics.TMAX
        }

//        countryNameTv.text = countryName
//        metricTv.text = metrics.getMetric()


        showProgress()
        weatherViewModel.getWhetherInfo(countryName, metrics)
            .observe(this, weatherObserver)
    }


    private fun populateWeatherInfo(weatherInfoList: ArrayList<WhetherInfoEntity>) {
        val adapter = WeatherInfoAdapter()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        weatherInfoRv.addItemDecoration(dividerItemDecoration)
        weatherInfoRv.layoutManager = layoutManager
        weatherInfoRv.adapter = adapter
        adapter.update(weatherInfoList)
    }
}
