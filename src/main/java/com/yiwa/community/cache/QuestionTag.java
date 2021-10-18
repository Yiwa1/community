package com.yiwa.community.cache;


import com.yiwa.community.dto.TagDTO;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionTag {

    //获得所有TagDTO
    public static List<TagDTO> getTagDTOList(){
        List<TagDTO> list=new LinkedList<>();
        TagDTO tagDTO=new TagDTO();
        tagDTO.setCategoryName("前端");
        tagDTO.setTags(Arrays.asList("javascript","前端","vue.js","css","html","html5","react.js","jquery"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("后端");
        tagDTO.setTags(Arrays.asList("php","java","node.js","python","golang","c++","c","后端","spring","springboot","django","flask","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("移动端");
        tagDTO.setTags(Arrays.asList("java","andriod","ios","objective-c","小程序","react-native","swift","xcode","android-studio","flutter","webapp","kotlin"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("数据库");
        tagDTO.setTags(Arrays.asList("mysql","redis","sql","数据库","mongodb","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("运维");
        tagDTO.setTags(Arrays.asList("linux","nginx","docker","apache","运维","centos","ubuntu","服务器","负载均衡","ssh","容器","jenkins","vagrant","devops","debian","fabric"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("工具");
        tagDTO.setTags(Arrays.asList("git","github","macos","visual-studio-code","windows","vim","sublime-text","intellij-idea","phpstorm","eclipse","webstorm","编辑器","svn","visual-studio","pycharm","emacs"));
        list.add(tagDTO);

        tagDTO=new TagDTO();
        tagDTO.setCategoryName("其他");
        tagDTO.setTags(Arrays.asList("程序员","http","segmentfault","安全","https","websocket","restful","xss","区块链","graphql","csrf","rpc","比特币","以太坊","udp","智能合约"));
        list.add(tagDTO);

        return list;
    }

}

