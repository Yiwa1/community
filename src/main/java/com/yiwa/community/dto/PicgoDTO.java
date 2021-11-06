package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PicgoDTO {
    private LinkedList<String> list;

    public PicgoDTO() {
    }

    public PicgoDTO(LinkedList list) {
        this.list = list;
    }

    public LinkedList getList() {
        return list;
    }

    public void setList(LinkedList list) {
        this.list = list;
    }
}
