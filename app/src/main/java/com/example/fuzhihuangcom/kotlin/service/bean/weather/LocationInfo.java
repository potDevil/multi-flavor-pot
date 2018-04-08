package com.example.fuzhihuangcom.kotlin.service.bean.weather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fzh on 2018/1/25.
 */

public class LocationInfo implements Serializable {

    /**
     * status : 0
     * result : {"location":{"lng":115.95845799999996,"lat":28.696117021980246},"formatted_address":"江西省南昌市青山湖区创新路1号","business":"高新开发区,火炬广场,发展路","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"江西省","city":"南昌市","city_level":2,"district":"青山湖区","town":"","adcode":"360111","street":"创新路","street_number":"1号","direction":"东南","distance":"96"},"pois":[{"addr":"火炬大道161号","cp":"","direction":"内","distance":"0","name":"海上汇商业广场","poiType":"购物","point":{"x":115.95904231411537,"y":28.694873191912354},"tag":"购物","tel":"","uid":"5865e75c0edaa8bc16c90541","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市高新开发区创新路1号","cp":" ","direction":"南","distance":"72","name":"7天连锁酒店(南昌高新店)","poiType":"酒店","point":{"x":115.95835061887291,"y":28.69668712120073},"tag":"酒店;快捷酒店","tel":"","uid":"1e08c752e8e862c70189725e","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号附近","cp":" ","direction":"东北","distance":"72","name":"建昌工业园-东门","poiType":"出入口","point":{"x":115.95788350000787,"y":28.695847489758084},"tag":"出入口;门","tel":"","uid":"f2545a7d331542f24302458f","zip":"","parent_poi":{"name":"建昌工业园","tag":"公司企业;园区","addr":"高新一路203号","point":{"x":115.95747027947341,"y":28.695395987017644},"direction":"东北","distance":"142","uid":"00f1c7fbfc7b001fc056acde"}},{"addr":"南昌市青山湖区","cp":" ","direction":"南","distance":"111","name":"豫乡苑","poiType":"房地产","point":{"x":115.9580721441649,"y":28.696932672506243},"tag":"房地产;住宅区","tel":"","uid":"aefd2b79e89330aaadb22aa1","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市青山湖区高新一路","cp":" ","direction":"东","distance":"132","name":"创业孵化中心","poiType":"公司企业","point":{"x":115.95728163531639,"y":28.695966305941656},"tag":"公司企业;园区","tel":"","uid":"7d782fd2e89012f4eddfca9a","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号","cp":" ","direction":"东北","distance":"142","name":"建昌工业园","poiType":"公司企业","point":{"x":115.95747027947341,"y":28.695395987017644},"tag":"公司企业;园区","tel":"","uid":"00f1c7fbfc7b001fc056acde","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市青山湖区火炬大街89号","cp":" ","direction":"东","distance":"147","name":"海外大厦","poiType":"房地产","point":{"x":115.95713790643484,"y":28.69598214808917},"tag":"房地产;写字楼","tel":"","uid":"c4fb408ae5c8ab66d5b74e60","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"火炬一路与高新二路交叉口西100米","cp":" ","direction":"西南","distance":"149","name":"南昌市LED景观工程技术研究中心","poiType":"教育培训","point":{"x":115.95932977187846,"y":28.697019803474735},"tag":"教育培训;科研机构","tel":"","uid":"2c7a11d0f24f205f2efaa748","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号金庐软件园金庐软件开发中心2楼201","cp":" ","direction":"东","distance":"156","name":"金庐软件园管理办公室","poiType":"政府机构","point":{"x":115.95706604199407,"y":28.69592670056228},"tag":"政府机构","tel":"","uid":"85a06535b36d2a7b210262b7","zip":"","parent_poi":{"name":"金庐软件开发中心","tag":"公司企业;园区","addr":"南昌市青山湖区火炬大街125号","point":{"x":115.95689536394723,"y":28.69574451562209},"direction":"东","distance":"180","uid":"c8ede73d00cd905832870a3b"}},{"addr":"南昌市青山湖区火炬大街125号泰豪创业大厦1层","cp":" ","direction":"东北","distance":"166","name":"如家精选酒店(南昌高新开发区店)","poiType":"酒店","point":{"x":115.95787451695277,"y":28.694904876539294},"tag":"酒店;快捷酒店","tel":"","uid":"7388dffd7f1a00f2d2ff23f6","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}}],"roads":[],"poiRegions":[{"direction_desc":"内","name":"海上汇商业广场","tag":"购物"}],"sematic_description":"海上汇商业广场内","cityCode":163}
     */
// http://api.map.baidu.com/geocoder/v2/?ak=xB2GDLWY76rq7GlwUmTtCmn6rlQwvQnh&callback=renderReverse&location=31.696117,121.958458&output=json&pois=1
    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":115.95845799999996,"lat":28.696117021980246}
         * formatted_address : 江西省南昌市青山湖区创新路1号
         * business : 高新开发区,火炬广场,发展路
         * addressComponent : {"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"江西省","city":"南昌市","city_level":2,"district":"青山湖区","town":"","adcode":"360111","street":"创新路","street_number":"1号","direction":"东南","distance":"96"}
         * pois : [{"addr":"火炬大道161号","cp":"","direction":"内","distance":"0","name":"海上汇商业广场","poiType":"购物","point":{"x":115.95904231411537,"y":28.694873191912354},"tag":"购物","tel":"","uid":"5865e75c0edaa8bc16c90541","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市高新开发区创新路1号","cp":" ","direction":"南","distance":"72","name":"7天连锁酒店(南昌高新店)","poiType":"酒店","point":{"x":115.95835061887291,"y":28.69668712120073},"tag":"酒店;快捷酒店","tel":"","uid":"1e08c752e8e862c70189725e","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号附近","cp":" ","direction":"东北","distance":"72","name":"建昌工业园-东门","poiType":"出入口","point":{"x":115.95788350000787,"y":28.695847489758084},"tag":"出入口;门","tel":"","uid":"f2545a7d331542f24302458f","zip":"","parent_poi":{"name":"建昌工业园","tag":"公司企业;园区","addr":"高新一路203号","point":{"x":115.95747027947341,"y":28.695395987017644},"direction":"东北","distance":"142","uid":"00f1c7fbfc7b001fc056acde"}},{"addr":"南昌市青山湖区","cp":" ","direction":"南","distance":"111","name":"豫乡苑","poiType":"房地产","point":{"x":115.9580721441649,"y":28.696932672506243},"tag":"房地产;住宅区","tel":"","uid":"aefd2b79e89330aaadb22aa1","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市青山湖区高新一路","cp":" ","direction":"东","distance":"132","name":"创业孵化中心","poiType":"公司企业","point":{"x":115.95728163531639,"y":28.695966305941656},"tag":"公司企业;园区","tel":"","uid":"7d782fd2e89012f4eddfca9a","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号","cp":" ","direction":"东北","distance":"142","name":"建昌工业园","poiType":"公司企业","point":{"x":115.95747027947341,"y":28.695395987017644},"tag":"公司企业;园区","tel":"","uid":"00f1c7fbfc7b001fc056acde","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"南昌市青山湖区火炬大街89号","cp":" ","direction":"东","distance":"147","name":"海外大厦","poiType":"房地产","point":{"x":115.95713790643484,"y":28.69598214808917},"tag":"房地产;写字楼","tel":"","uid":"c4fb408ae5c8ab66d5b74e60","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"火炬一路与高新二路交叉口西100米","cp":" ","direction":"西南","distance":"149","name":"南昌市LED景观工程技术研究中心","poiType":"教育培训","point":{"x":115.95932977187846,"y":28.697019803474735},"tag":"教育培训;科研机构","tel":"","uid":"2c7a11d0f24f205f2efaa748","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}},{"addr":"高新一路203号金庐软件园金庐软件开发中心2楼201","cp":" ","direction":"东","distance":"156","name":"金庐软件园管理办公室","poiType":"政府机构","point":{"x":115.95706604199407,"y":28.69592670056228},"tag":"政府机构","tel":"","uid":"85a06535b36d2a7b210262b7","zip":"","parent_poi":{"name":"金庐软件开发中心","tag":"公司企业;园区","addr":"南昌市青山湖区火炬大街125号","point":{"x":115.95689536394723,"y":28.69574451562209},"direction":"东","distance":"180","uid":"c8ede73d00cd905832870a3b"}},{"addr":"南昌市青山湖区火炬大街125号泰豪创业大厦1层","cp":" ","direction":"东北","distance":"166","name":"如家精选酒店(南昌高新开发区店)","poiType":"酒店","point":{"x":115.95787451695277,"y":28.694904876539294},"tag":"酒店;快捷酒店","tel":"","uid":"7388dffd7f1a00f2d2ff23f6","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}}]
         * roads : []
         * poiRegions : [{"direction_desc":"内","name":"海上汇商业广场","tag":"购物"}]
         * sematic_description : 海上汇商业广场内
         * cityCode : 163
         */

        private LocationBean location;
        private String formatted_address;
        private String business;
        private AddressComponentBean addressComponent;
        private String sematic_description;
        private int cityCode;
        private List<PoisBean> pois;
        private List<?> roads;
        private List<PoiRegionsBean> poiRegions;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public List<PoisBean> getPois() {
            return pois;
        }

        public void setPois(List<PoisBean> pois) {
            this.pois = pois;
        }

        public List<?> getRoads() {
            return roads;
        }

        public void setRoads(List<?> roads) {
            this.roads = roads;
        }

        public List<PoiRegionsBean> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(List<PoiRegionsBean> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public static class LocationBean {
            /**
             * lng : 115.95845799999996
             * lat : 28.696117021980246
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        public static class AddressComponentBean {
            /**
             * country : 中国
             * country_code : 0
             * country_code_iso : CHN
             * country_code_iso2 : CN
             * province : 江西省
             * city : 南昌市
             * city_level : 2
             * district : 青山湖区
             * town :
             * adcode : 360111
             * street : 创新路
             * street_number : 1号
             * direction : 东南
             * distance : 96
             */

            private String country;
            private int country_code;
            private String country_code_iso;
            private String country_code_iso2;
            private String province;
            private String city;
            private int city_level;
            private String district;
            private String town;
            private String adcode;
            private String street;
            private String street_number;
            private String direction;
            private String distance;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getCountry_code_iso() {
                return country_code_iso;
            }

            public void setCountry_code_iso(String country_code_iso) {
                this.country_code_iso = country_code_iso;
            }

            public String getCountry_code_iso2() {
                return country_code_iso2;
            }

            public void setCountry_code_iso2(String country_code_iso2) {
                this.country_code_iso2 = country_code_iso2;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_level() {
                return city_level;
            }

            public void setCity_level(int city_level) {
                this.city_level = city_level;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class PoisBean {
            /**
             * addr : 火炬大道161号
             * cp :
             * direction : 内
             * distance : 0
             * name : 海上汇商业广场
             * poiType : 购物
             * point : {"x":115.95904231411537,"y":28.694873191912354}
             * tag : 购物
             * tel :
             * uid : 5865e75c0edaa8bc16c90541
             * zip :
             * parent_poi : {"name":"","tag":"","addr":"","point":{"x":0,"y":0},"direction":"","distance":"","uid":""}
             */

            private String addr;
            private String cp;
            private String direction;
            private String distance;
            private String name;
            private String poiType;
            private PointBean point;
            private String tag;
            private String tel;
            private String uid;
            private String zip;
            private ParentPoiBean parent_poi;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoiType() {
                return poiType;
            }

            public void setPoiType(String poiType) {
                this.poiType = poiType;
            }

            public PointBean getPoint() {
                return point;
            }

            public void setPoint(PointBean point) {
                this.point = point;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public ParentPoiBean getParent_poi() {
                return parent_poi;
            }

            public void setParent_poi(ParentPoiBean parent_poi) {
                this.parent_poi = parent_poi;
            }

            public static class PointBean {
                /**
                 * x : 115.95904231411537
                 * y : 28.694873191912354
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }

            public static class ParentPoiBean {
                /**
                 * name :
                 * tag :
                 * addr :
                 * point : {"x":0,"y":0}
                 * direction :
                 * distance :
                 * uid :
                 */

                private String name;
                private String tag;
                private String addr;
                private PointBeanX point;
                private String direction;
                private String distance;
                private String uid;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public PointBeanX getPoint() {
                    return point;
                }

                public void setPoint(PointBeanX point) {
                    this.point = point;
                }

                public String getDirection() {
                    return direction;
                }

                public void setDirection(String direction) {
                    this.direction = direction;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public static class PointBeanX {
                    /**
                     * x : 0
                     * y : 0
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }
            }
        }

        public static class PoiRegionsBean {
            /**
             * direction_desc : 内
             * name : 海上汇商业广场
             * tag : 购物
             */

            private String direction_desc;
            private String name;
            private String tag;

            public String getDirection_desc() {
                return direction_desc;
            }

            public void setDirection_desc(String direction_desc) {
                this.direction_desc = direction_desc;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
