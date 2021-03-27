package com.example.requestnetwork

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.reflect.Type


class Request() {    //组装网络请求用的

     var baseUrl:String? =null
    var MyGson:MyGosn?=null
    var clazz:Any? =null
    var  url:String?=null
   // var tag:String? = null
    var type:Type? = null
    fun setbaseurl(url:String):Request{
        this.baseUrl=url
        return this
    }



    fun addConverterFactory(gson:MyGosn):Request{
        this.MyGson=gson
        return this
    }



    fun build():Request{    //执行构建完成的操作   暂无需求


         return this
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun create(clazz:Class<*>):Any?{    //创建类
         this.clazz = clazz
         val analysis = Analysis(clazz,this)

        return analysis.getPoxyClass()
    }

    fun getbaseUrl() = this.baseUrl










}