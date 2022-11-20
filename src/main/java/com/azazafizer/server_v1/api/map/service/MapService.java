package com.azazafizer.server_v1.api.map.service;

import com.azazafizer.server_v1.api.map.domain.ro.MapRo;

public interface MapService {

    MapRo getSearchPlaceByKeyword(String query);
}
