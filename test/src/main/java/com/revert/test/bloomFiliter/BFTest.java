package com.revert.test.bloomFiliter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * 布隆过滤
 * xiecong
 */
public class BFTest {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 1024*1024*32);

        String vals[] = {"1","6","999999","1919919191","42342sdfg","asdfjxlkcvzm","a","c"};
        for(String v : vals){
            bloomFilter.put(v);
        }
        String search = "d";
        //是否存在
        boolean res = bloomFilter.mightContain(search);
        System.out.println(search+"是否存在"+res);
    }

}
