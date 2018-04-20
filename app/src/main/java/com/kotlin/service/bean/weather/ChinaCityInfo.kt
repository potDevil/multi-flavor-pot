package com.kotlin.service.bean.weather

import java.io.Serializable

/**
 * Created by fzh on 2018/1/30.
 */

class ChinaCityInfo : Serializable {

    /**
     * id : 1
     * name : 北京
     * weather_id : 查询天气的id
     */

    var id: Int = 0
    var name: String? = null
    var weather_id: String? = null
}
