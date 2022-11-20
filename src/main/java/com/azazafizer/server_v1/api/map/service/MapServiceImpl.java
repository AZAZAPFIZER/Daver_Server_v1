package com.azazafizer.server_v1.api.map.service;

import com.azazafizer.server_v1.api.map.domain.ro.KakaoMapApiRo;
import com.azazafizer.server_v1.api.map.domain.ro.MapRo;
import com.azazafizer.server_v1.common.resttemplate.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{

    private final RestTemplateConfig restTemplateConfig;

    @Override
    public MapRo getSearchPlaceByKeyword(String query) {
        try {
            String queryString = "?query=" + URLEncoder.encode(query, "UTF-8");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.add("Authorization", "KakaoAK " + "1c2e8e71a4d52bad86587b46130fd814");
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

            URI url = URI.create("https://dapi.kakao.com" + "/v2/local/search/keyword.json" + queryString);
            RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
            String re = restTemplate.exchange(rq, String.class).getBody();

            JSONObject document = new JSONObject(re);
            JSONArray documents = (JSONArray) document.get("documents");
            JSONObject row = (JSONObject) documents.get(0);
            String x = (String) row.get("x");
            String y = (String) row.get("y");
            return new MapRo(x, y);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
