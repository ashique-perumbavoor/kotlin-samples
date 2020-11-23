package com.ashique.databindingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ashique.databindingsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = UserInfo("Ashique", 18)
        val binder: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binder.setVariable(BR.user, user)
        binder.executePendingBindings()
    }
}