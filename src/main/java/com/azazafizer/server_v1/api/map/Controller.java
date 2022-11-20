package com.azazafizer.server_v1.api.map;

import com.azazafizer.server_v1.api.map.domain.ro.MapRo;
import com.azazafizer.server_v1.api.map.service.MapService;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/maps")
@RequiredArgsConstructor
public class Controller {

    private final MapService mapService;

    @GetMapping("/test")
    public ResponseData<MapRo> testMap() {
        MapRo map = mapService.getSearchPlaceByKeyword();
        return new ResponseData<>(
                HttpStatus.OK,
                "맵스 API 성공",
                map
        );
    }
}

