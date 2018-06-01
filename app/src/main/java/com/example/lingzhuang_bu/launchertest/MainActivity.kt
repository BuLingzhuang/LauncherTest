package com.example.lingzhuang_bu.launchertest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.content.ComponentName
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var defaultComponent: ComponentName
    private lateinit var testComponent: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //拿到当前activity注册的组件名称
        val componentName = componentName

        //拿到我们注册的MainActivity组件
        defaultComponent = ComponentName("com.example.lingzhuang_bu.launchertest", "com.example.lingzhuang_bu.launchertest.MainActivity")  //拿到默认的组件
        //拿到我注册的别名test组件
        testComponent = ComponentName("com.example.lingzhuang_bu.launchertest", "com.example.lingzhuang_bu.launchertest.test")

        btn_default.setOnClickListener { changeDefaultIcon() }
        btn_new.setOnClickListener { changeIcon() }
        btn_dis.setOnClickListener {
//            disableComponent(defaultComponent)
            val jsonObject = JSONObject("null346232626")
            Log.e("bbb",jsonObject.toString())
        }
    }

    fun changeIcon() {
        disableComponent(defaultComponent)
        enableComponent(testComponent)
    }

    fun changeDefaultIcon() {
        enableComponent(defaultComponent)
        disableComponent(testComponent)
    }

    override fun onDestroy() {
        changeIcon()
        super.onDestroy()
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
