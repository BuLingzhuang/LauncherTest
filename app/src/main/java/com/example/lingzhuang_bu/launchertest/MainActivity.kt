package com.example.lingzhuang_bu.launchertest

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.content.ComponentName

import kotlinx.android.synthetic.main.activity_main.*


@SuppressLint("Registered")
open class MainActivity : AppCompatActivity() {

    private lateinit var defaultComponent: ComponentName
    private val componentList = ArrayList<ComponentName>()
    private val launcherPath = "com.example.lingzhuang_bu.launchertest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defaultComponent = ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.DefaultActivity")
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.PremiumActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.PrestigeActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Default2018ChristmasActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Premium2018ChristmasActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Prestige2018ChristmasActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Default2019YearActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Premium2019YearActivity"))
        componentList.add(ComponentName(launcherPath, "com.example.lingzhuang_bu.launchertest.Prestige2019YearActivity"))

        btn_default.setOnClickListener { changeDefaultIcon() }
        btn_2018_christmas.setOnClickListener { showSpecIcon(2, 3, 4) }
        btn_2019_year.setOnClickListener { showSpecIcon(5, 6, 7) }
        btn_all.setOnClickListener { showAllIcon() }
    }

    fun showAllIcon() {
        for (componentName in componentList) {
            enableComponent(componentName)
        }
    }

    fun showSpecIcon(vararg numList: Int) {
        for (num in numList) {
            enableComponent(componentList[num])
        }
    }

    fun changeDefaultIcon() {
        for (componentName in componentList) {
            disableComponent(componentName)
        }
    }

    /**
     * 启用组件
     *
     * @param componentName
     */
    private fun enableComponent(componentName: ComponentName) {
        val state = packageManager.getComponentEnabledSetting(componentName)
        if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            //已经启用
            return
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP)
    }

    /**
     * 禁用组件
     *
     * @param componentName
     */
    private fun disableComponent(componentName: ComponentName) {
        val state = packageManager.getComponentEnabledSetting(componentName)
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            //已经禁用
            return
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)
    }
}
