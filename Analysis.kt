package com.example.requestnetwork

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.reflect.*

class Analysis(val clazz:Class<*>,val request: Request) {          //反射解析类

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPoxyClass():Any?{

     val poxy=  Proxy.newProxyInstance(    //jdk动态代理
               clazz.classLoader,
             arrayOf(clazz),
            InvokeMethod(request,clazz)
       )
        return poxy



//
//        method.forEach {
//          if (it.isAnnotationPresent(GET::class.java)){  //get请求的逻辑
//              val urlAnotation = it.getAnnotation(GET::class.java)//拿到注解
//              val value = urlAnotation.value  //拿到注解的值
//
//          }else if (it.isAnnotationPresent(POST::class.java)){  //post请求的逻辑
//
//          }
//
//        }


    }



    fun analysis(method: Method?):Type{
          val backtype = method?.genericReturnType as ParameterizedType
          return backtype.actualTypeArguments[0]

    }


}