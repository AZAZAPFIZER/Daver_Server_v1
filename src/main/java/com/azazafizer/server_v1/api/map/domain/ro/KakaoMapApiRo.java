package com.azazafizer.server_v1.api.map.domain.ro;

import lombok.Getter;

@Getter
public class KakaoMapApiRo {

    @Getter
    public static class Address {
        private String address_name;
        private String b_code;
        private String h_code;
        private String main_address_no;
        private String mountain_yn;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_h_namel;
        private String region_3depth_name;
        private String sub_address_no;
        private String x;
        private String y;
    }

    @Getter
    public static class RoadAddress {
        private String address_name;
        private String b_code;
        private String h_code;
        private String main_building_no;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_name;
        private String road_name;
        private String sub_address_no;
        private String underground_yn;
        private String x;
        private String y;
        private String zone_no;
    }

    private Address address;
    private RoadAddress road_address;
}
