package com.cew.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenchaofei on 2017/3/10.
 */
public class GeneratePageable {
    public static Map<String, Object> getPageInfo(List list, Integer total) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        data.put("total", total);
        return data;
    }
}
