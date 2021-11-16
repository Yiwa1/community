package com.yiwa.community.cache;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LanguageGirl {
    public static List<String> girlPicture=Arrays.asList("https://cdn.jsdelivr.net/gh/yiwa1/Picture-bed/linux/202111161204618.jpeg", "https://cdn.jsdelivr.net/gh/yiwa1/Picture-bed/linux/202111161205138.jpeg", "https://cdn.jsdelivr.net/gh/yiwa1/Picture-bed/linux/202111161205147.jpeg", "https://cdn.jsdelivr.net/gh/yiwa1/Picture-bed/linux/202111161205774.jpeg", "https://cdn.jsdelivr.net/gh/yiwa1/Picture-bed/linux/202111161205106.jpeg");

    public LanguageGirl() {
    }

    public LanguageGirl(List<String> girlPicture) {
        this.girlPicture = girlPicture;
    }

    public static List<String> getGirlPicture() {
        return LanguageGirl.girlPicture;
    }
}
