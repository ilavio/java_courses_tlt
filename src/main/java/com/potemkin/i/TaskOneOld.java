package com.potemkin.i;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskOneOld {

    public List<UUID> createUuid() {
        List<UUID> list = new ArrayList<UUID>();// 10000
        for (int i = 0; i < 10000; i++) {
            list.add(UUID.randomUUID());
        }
        return list;
    }
    
    public void printList(List<UUID> list) {
        for(UUID lis : list) {
        System.out.println(lis);
        }
    }
}
