package com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo

/**
 * Created by fzh on 2018/1/31.
 */
class WeatherInfo {

    var HeWeather: List<HeWeatherBean>? = null

    class HeWeatherBean {
        /**
         * aqi : {"city":{"aqi":"69","qlty":"良","pm25":"17","pm10":"87","no2":"41","so2":"49","co":"1.0","o3":"31"}}
         * basic : {"city":"门头沟","cnty":"中国","id":"CN101011400","lat":"39.93718338","lon":"116.1053772","update":{"loc":"2018-01-31 09:51","utc":"2018-01-31 01:51"}}
         * daily_forecast : [{"astro":{"mr":"17:18","ms":"06:57","sr":"07:24","ss":"17:34"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-01-31","hum":"21","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"4","min":"-8"},"uv":"2","vis":"20","wind":{"deg":"185","dir":"南风","sc":"微风","spd":"6"}},{"astro":{"mr":"18:29","ms":"07:46","sr":"07:23","ss":"17:35"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-02-01","hum":"22","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"4","min":"-8"},"uv":"2","vis":"20","wind":{"deg":"329","dir":"西北风","sc":"微风","spd":"8"}},{"astro":{"mr":"19:41","ms":"08:29","sr":"07:22","ss":"17:36"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-02","hum":"19","pcpn":"0.0","pop":"0","pres":"1040","tmp":{"max":"-1","min":"-10"},"uv":"2","vis":"20","wind":{"deg":"315","dir":"西北风","sc":"微风","spd":"6"}},{"astro":{"mr":"20:50","ms":"09:07","sr":"07:21","ss":"17:37"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-03","hum":"19","pcpn":"0.0","pop":"0","pres":"1039","tmp":{"max":"0","min":"-10"},"uv":"2","vis":"20","wind":{"deg":"287","dir":"西北风","sc":"微风","spd":"8"}},{"astro":{"mr":"21:56","ms":"09:41","sr":"07:20","ss":"17:38"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-04","hum":"23","pcpn":"0.0","pop":"0","pres":"1038","tmp":{"max":"1","min":"-8"},"uv":"2","vis":"20","wind":{"deg":"340","dir":"西北风","sc":"微风","spd":"6"}},{"astro":{"mr":"23:00","ms":"10:13","sr":"07:19","ss":"17:40"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-05","hum":"24","pcpn":"0.0","pop":"0","pres":"1036","tmp":{"max":"3","min":"-9"},"uv":"2","vis":"20","wind":{"deg":"171","dir":"南风","sc":"微风","spd":"5"}},{"astro":{"mr":"01:45","ms":"10:44","sr":"07:18","ss":"17:41"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-06","hum":"22","pcpn":"0.0","pop":"0","pres":"1032","tmp":{"max":"4","min":"-7"},"uv":"2","vis":"19","wind":{"deg":"175","dir":"南风","sc":"微风","spd":"5"}}]
         * hourly_forecast : [{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-01-31 10:00","hum":"18","pop":"0","pres":"1032","tmp":"0","wind":{"deg":"307","dir":"西北风","sc":"微风","spd":"7"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-01-31 13:00","hum":"14","pop":"0","pres":"1031","tmp":"1","wind":{"deg":"271","dir":"西风","sc":"微风","spd":"7"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-01-31 16:00","hum":"15","pop":"0","pres":"1029","tmp":"2","wind":{"deg":"219","dir":"西南风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-01-31 19:00","hum":"17","pop":"0","pres":"1030","tmp":"0","wind":{"deg":"223","dir":"西南风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-01-31 22:00","hum":"19","pop":"0","pres":"1031","tmp":"-2","wind":{"deg":"258","dir":"西南风","sc":"微风","spd":"5"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-01 01:00","hum":"21","pop":"0","pres":"1030","tmp":"-2","wind":{"deg":"314","dir":"西北风","sc":"微风","spd":"4"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-01 04:00","hum":"23","pop":"0","pres":"1030","tmp":"-5","wind":{"deg":"342","dir":"西北风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-01 07:00","hum":"26","pop":"0","pres":"1031","tmp":"-6","wind":{"deg":"340","dir":"西北风","sc":"微风","spd":"6"}}]
         * now : {"cond":{"code":"100","txt":"晴"},"fl":"-12","hum":"18","pcpn":"0.0","pres":"1031","tmp":"0","vis":"8","wind":{"deg":"327","dir":"西北风","sc":"3-4","spd":"15"}}
         * status : ok
         * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},"flu":{"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动。"},"trav":{"brf":"适宜","txt":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
         */

        var aqi: AqiBean? = null
        var basic: BasicBean? = null
        var now: NowBean? = null
        var status: String? = null
        var suggestion: SuggestionBean? = null
        var daily_forecast: List<DailyForecastBean>? = null
        var hourly_forecast: List<HourlyForecastBean>? = null

        class AqiBean {
            /**
             * city : {"aqi":"69","qlty":"良","pm25":"17","pm10":"87","no2":"41","so2":"49","co":"1.0","o3":"31"}
             */

            var city: CityBean? = null

            class CityBean {
                /**
                 * aqi : 69
                 * qlty : 良
                 * pm25 : 17
                 * pm10 : 87
                 * no2 : 41
                 * so2 : 49
                 * co : 1.0
                 * o3 : 31
                 */

                var aqi: String? = null
                var qlty: String? = null
                var pm25: String? = null
                var pm10: String? = null
                var no2: String? = null
                var so2: String? = null
                var co: String? = null
                var o3: String? = null
            }
        }

        class BasicBean {
            /**
             * city : 门头沟
             * cnty : 中国
             * id : CN101011400
             * lat : 39.93718338
             * lon : 116.1053772
             * update : {"loc":"2018-01-31 09:51","utc":"2018-01-31 01:51"}
             */

            var city: String? = null
            var cnty: String? = null
            var id: String? = null
            var lat: String? = null
            var lon: String? = null
            var update: UpdateBean? = null

            class UpdateBean {
                /**
                 * loc : 2018-01-31 09:51
                 * utc : 2018-01-31 01:51
                 */

                var loc: String? = null
                var utc: String? = null
            }
        }

        class NowBean {
            /**
             * cond : {"code":"100","txt":"晴"}
             * fl : -12
             * hum : 18
             * pcpn : 0.0
             * pres : 1031
             * tmp : 0
             * vis : 8
             * wind : {"deg":"327","dir":"西北风","sc":"3-4","spd":"15"}
             */

            var cond: CondBean? = null
            var fl: String? = null
            var hum: String? = null
            var pcpn: String? = null
            var pres: String? = null
            var tmp: String? = null
            var vis: String? = null
            var wind: WindBean? = null

            class CondBean {
                /**
                 * code : 100
                 * txt : 晴
                 */

                var code: String? = null
                var txt: String? = null
            }

            class WindBean {
                /**
                 * deg : 327
                 * dir : 西北风
                 * sc : 3-4
                 * spd : 15
                 */

                var deg: String? = null
                var dir: String? = null
                var sc: String? = null
                var spd: String? = null
            }
        }

        class SuggestionBean {
            /**
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * comf : {"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"}
             * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
             * drsg : {"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"}
             * flu : {"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"}
             * sport : {"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动。"}
             * trav : {"brf":"适宜","txt":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"}
             * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             */

            var air: AirBean? = null
            var comf: ComfBean? = null
            var cw: CwBean? = null
            var drsg: DrsgBean? = null
            var flu: FluBean? = null
            var sport: SportBean? = null
            var trav: TravBean? = null
            var uv: UvBean? = null

            class AirBean {
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class ComfBean {
                /**
                 * brf : 较不舒适
                 * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class CwBean {
                /**
                 * brf : 较适宜
                 * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class DrsgBean {
                /**
                 * brf : 冷
                 * txt : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class FluBean {
                /**
                 * brf : 较易发
                 * txt : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class SportBean {
                /**
                 * brf : 较不宜
                 * txt : 天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动。
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class TravBean {
                /**
                 * brf : 适宜
                 * txt : 天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！
                 */

                var brf: String? = null
                var txt: String? = null
            }

            class UvBean {
                /**
                 * brf : 弱
                 * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                 */

                var brf: String? = null
                var txt: String? = null
            }
        }

        class DailyForecastBean {
            /**
             * astro : {"mr":"17:18","ms":"06:57","sr":"07:24","ss":"17:34"}
             * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
             * date : 2018-01-31
             * hum : 21
             * pcpn : 0.0
             * pop : 0
             * pres : 1031
             * tmp : {"max":"4","min":"-8"}
             * uv : 2
             * vis : 20
             * wind : {"deg":"185","dir":"南风","sc":"微风","spd":"6"}
             */

            var astro: AstroBean? = null
            var cond: CondBeanX? = null
            var date: String? = null
            var hum: String? = null
            var pcpn: String? = null
            var pop: String? = null
            var pres: String? = null
            var tmp: TmpBean? = null
            var uv: String? = null
            var vis: String? = null
            var wind: WindBeanX? = null

            class AstroBean {
                /**
                 * mr : 17:18
                 * ms : 06:57
                 * sr : 07:24
                 * ss : 17:34
                 */

                var mr: String? = null
                var ms: String? = null
                var sr: String? = null
                var ss: String? = null
            }

            class CondBeanX {
                /**
                 * code_d : 100
                 * code_n : 100
                 * txt_d : 晴
                 * txt_n : 晴
                 */

                var code_d: String? = null
                var code_n: String? = null
                var txt_d: String? = null
                var txt_n: String? = null
            }

            class TmpBean {
                /**
                 * max : 4
                 * min : -8
                 */

                var max: String? = null
                var min: String? = null
            }

            class WindBeanX {
                /**
                 * deg : 185
                 * dir : 南风
                 * sc : 微风
                 * spd : 6
                 */

                var deg: String? = null
                var dir: String? = null
                var sc: String? = null
                var spd: String? = null
            }
        }

        class HourlyForecastBean {
            /**
             * cond : {"code":"103","txt":"晴间多云"}
             * date : 2018-01-31 10:00
             * hum : 18
             * pop : 0
             * pres : 1032
             * tmp : 0
             * wind : {"deg":"307","dir":"西北风","sc":"微风","spd":"7"}
             */

            var cond: CondBeanXX? = null
            var date: String? = null
            var hum: String? = null
            var pop: String? = null
            var pres: String? = null
            var tmp: String? = null
            var wind: WindBeanXX? = null

            class CondBeanXX {
                /**
                 * code : 103
                 * txt : 晴间多云
                 */

                var code: String? = null
                var txt: String? = null
            }

            class WindBeanXX {
                /**
                 * deg : 307
                 * dir : 西北风
                 * sc : 微风
                 * spd : 7
                 */

                var deg: String? = null
                var dir: String? = null
                var sc: String? = null
                var spd: String? = null
            }
        }
    }
}
