package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.requestnetwork.MyGosn
import com.example.requestnetwork.NetworkRequest
import com.example.requestnetwork.Request

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val r = Request()
            .setbaseurl("https://pixabay.com/api/")
            .addConverterFactory(MyGosn())
            .create(Requesttest::class.java) as Requesttest
        val map = mutableMapOf<String, String>()
        map.put("key", "12472743-874dc01dadd26dc44e0801d61&q")

        val s = r.getdata("-874dc01dadd26dc44e0801d61&q")
        s.setCallback {
            it as Test
            Log.i("测试", "get返回的类型对了嘛" + it.hits[0].id)
        }


        val x = r.getdata2(map)
        val p = s == x
        x.setCallback {
            it as Test
            Log.i("测试", "post返回的类型对了嘛" + it.hits[1].id)
        }


    }
}