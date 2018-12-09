package com.qa.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liweiwei.li on 2018/12/8.
 */
public class ParseKey {
    public static Map<String, String> parseJsonObject(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<String, String>();
        JSONArray results = (JSONArray) jsonObject.get("results");
        JSONObject options = (JSONObject) jsonObject.get("options");
        Boolean ignoreLatLngInput = (Boolean) options.get("ignoreLatLngInput");
        List<String> latLngList = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++) {
            JSONObject ro = (JSONObject) results.get(i);
            JSONArray locations = (JSONArray) ro.get("locations");
            for (int j = 0; j < locations.size(); j++) {
                JSONObject lo = (JSONObject) locations.get(j);
                latLngList.add(lo.get("latLng").toString());
            }
        }
        map.put("ignoreLatLngInput",ignoreLatLngInput.toString());
        map.put("latLng",latLngList.toString());
        return map;
    }
}