package com.hang.common.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 快速排序
 * @author hang on 2020/5/16
 */
public class QuickSort {

    /**
     * 排序
     * @param list 排序队列
     * @param start 需要排序的开始位置
     * @param end 需要排序的末尾位置
     */
    public static void sort(List<Integer> list, int start, int end){
        if(null == list || end - start < 1){
            return;
        }
        System.out.println(log(list,start,end));

        // 排序列表首位为 基准数
        Integer tag = list.get(start);

        int i = start+1;
        int j = end;
        Integer jVal = null;
        Integer iVal = null;

        for(;;){
            if(i == j){
                Integer s = list.get(i);
                if(s < tag){
                    list.set(start,s);
                    list.set(i,tag);
                }
                break;
            }
            if(jVal == null) {
                jVal = list.get(j);
                if (jVal >= tag) {
                    jVal = null;
                    j--;
                }
                continue;
            }
            else{
                iVal = list.get(i);
                if(iVal > tag) {
                    list.set(i, jVal);
                    list.set(j, iVal);
                    jVal = null;
                }
                i++;
            }
        }
        sort(list, start, i-1);
        sort(list, i, end);
    }

    private static String log(List<Integer> list, int start, int end){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size();i++){
            if(i == start){
                sb.append("[");
            }
            sb.append(list.get(i));
            if(i == end){
                sb.append("] ");
            }
            else{
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * 6 1 2 7 9 3 4 5 10 8
     * @param args
     */
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(1);
        list.add(6);
        list.add(100);
        list.add(2);
        list.add(17);
        list.add(19);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(10);
        list.add(8);
        list.add(20);
        list.add(11);
        list.add(18);
        list.add(12);
        list.add(17);
        list.add(7);
        list.add(9);
        list.add(1);
        System.out.println("开始排序:"+list.stream().map(n-> String.valueOf(n)).collect(Collectors.toList()));
        sort(list, 0, list.size()-1);
        System.out.println("排序完成:"+list.stream().map(n-> String.valueOf(n)).collect(Collectors.toList()));
    }
}
