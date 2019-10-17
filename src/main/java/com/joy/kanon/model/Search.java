package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;

/**
 * 道路搜索（图搜索）
 */
@Data
@ToString
public class Search {
    private StringBuffer dfs = new StringBuffer();
    private StringBuffer bfs= new StringBuffer();

    /**
     * 深度优先实现
     */
    public void searchDFS(Road root){
        if(root == null){
            return;
        }

        /**
         * 已经访问了不止一个点
         */
        if(dfs.length() > 0){
            dfs.append(" => ");
        }

        dfs.append(root.getNode().getName());
        root.setVisited(true);

        for (Road node : root.getNeighborList()) {
            if(!node.isVisited()){
                searchDFS(node);
            }
        }
    }

    /**
     * 广度优先实现
     */
    public void searchBFS(Road root){
        Deque<Road> queue = new LinkedList<>();

        if(root == null){
            return;
        }

        /**
         * 已经访问了不止一个点
         */
        if(bfs.length() > 0){
            bfs.append(" => ");
        }

        bfs.append(root.getNode().getName());
        root.setVisited(true);

        // 加到队列中
        queue.addLast(root);

        while(!queue.isEmpty()){
            // 头部元素
            Road node = queue.poll();
            for (Road n : node.neighborList) {
                if(!n.isVisited()){
                    bfs.append(" => ");
                    bfs.append(n.getNode().getName());
                    n.setVisited(true);
                    // 加入到队列中
                    queue.addLast(n);
                }
            }

        }
    }

    public static void main(String[] args) {
        Road r1 = new Road(new Location("A", 0, 3D, 2D));
        Road r2 = new Road(new Location("B", 0, 3D, 2D));
        Road r3 = new Road(new Location("C", 0, 3D, 2D));
        Road r4 = new Road(new Location("D", 0, 3D, 2D));
        Road r5 = new Road(new Location("E", 0, 3D, 2D));
        Road r6 = new Road(new Location("F", 0, 3D, 2D));
        Road r7 = new Road(new Location("G", 0, 3D, 2D));
        Road r8 = new Road(new Location("H", 0, 3D, 2D));
        Road r9 = new Road(new Location("I", 0, 3D, 2D));
        Road r10 = new Road(new Location("J", 0, 3D, 2D));

        r1.neighborList.addAll(Arrays.asList(r9,r10,r2,r3));


        r3.neighborList.addAll(Arrays.asList(r1,r6,r7));


        r7.neighborList.addAll(Arrays.asList(r3,r4, r5));

        r4.neighborList.addAll(Arrays.asList(r8,r7));

        r8.neighborList.add(r4);


        Search search = new Search();

//        search.searchDFS(r1);



        search.searchBFS(r1);


//        System.out.println("==============深度优先遍历为================");
//        System.out.println(search.getDfs());


        System.out.println("==============广度优先遍历为================");
        System.out.println(search.getBfs());

    }


}
