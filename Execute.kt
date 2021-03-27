package com.example.requestnetwork

import android.util.Log
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object  Execute {   //管理所以的网络请求

        //将开启的线程放在一起  需要时回收
    private var thread:NetworkRequest<*>?=null
    private val ThreadS:MutableList<NetworkRequest<*>?> = mutableListOf()
    private val requestQueue by lazy {   //网络请求的阻塞队列
        LinkedBlockingQueue<Request>();
    }

    val Requests = mutableSetOf<Runnable>()
    val executo = ThreadPoolExecutor(
            1,
            10,
            60,
            TimeUnit.SECONDS,
            LinkedBlockingDeque()

    )   //创建线程池


    fun addRequest(request: Request):NetworkRequest<Any> {
        requestQueue.add(request)
         thread = NetworkRequest<Any>(requestQueue)
      //  Log.i("开局","返回的对象是空的吗"+ thread)
        startAllRequestThread()
        return thread as NetworkRequest<Any>
    }

    fun startAllRequestThread(){
                //给线程注册回调接口
                //当队列为空时 停止所有线程
        //获取可以拿到的最大线程数
        thread?.setcompletelisten {
            stopallThreads()
        }

        val threadCount = Runtime.getRuntime().availableProcessors()
        if(threadCount>0){
            for (index in 1..threadCount){
                executo.execute(thread)
                ThreadS.add(thread)
            }

        }

    }


    fun stopallThreads(){
        ThreadS.forEach{
            it?.interrupt()
            ThreadS.remove(it)
        }
    }




}