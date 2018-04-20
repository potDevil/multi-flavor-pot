package com.kotlin.service.bean

import java.io.Serializable

/**
 * Created by tong on 2018/1/19.
 */

/**
{
    "ret": "0",
    "msg": "操作成功",
    "info": {}
}
 */
open class HttpResp<out T>(val retCode: String? = "", val msg: String?, val result: T): Serializable

/**
{
    "ret": "0",
    "msg": "操作成功",
    "info": []
}
 */
open class HttpListResp<out T>(retCode: String?, msg: String?, result: List<T>) : HttpResp<List<T>>(retCode, msg, result)

/**
    {
    "ret": "0",
    "msg": "操作成功",
    "info": {
        "total": 0,
        "list": []
    }
}
 */
open class HttpListInfoResp<T>(retCode: String?, msg: String?, result: ListInfo<T>): HttpResp<ListInfo<T>>(retCode, msg, result)

/**
{
"total": 0,
"list": []
}
 */
class ListInfo<T>(val total: Int, val list: ArrayList<T>): Serializable
