package com.yiwa.community.cache;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HotTag {
    public static List<Map.Entry<String,Integer>> hotTagEntryList;

    public HotTag() {
    }

    public static List<Map.Entry<String, Integer>> getHotTagEntryList() {
        return hotTagEntryList;
    }

    public static void setHotTagEntryList(List<Map.Entry<String, Integer>> hotTagEntryList) {
        HotTag.hotTagEntryList = hotTagEntryList;
    }
}
