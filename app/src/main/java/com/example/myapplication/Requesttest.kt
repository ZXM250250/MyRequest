package com.example.myapplication

import com.example.requestnetwork.GET
import com.example.requestnetwork.NetworkRequest
import com.example.requestnetwork.POST


/**
 * 用户自己需要的网络请求
 *
 */
interface Requesttest {


        @GET("?key=12472743")
        fun getdata(u:String): NetworkRequest<Test>
        @POST("?")
        fun getdata2(mao:Map<String,String>):NetworkRequest<Test>
}