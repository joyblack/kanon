package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

import java.util.*;

/**
 * 道路搜索（图搜索）
 */
@Data
@ToString
public class Search {
    private List<Vertex> DFS;

    private List<Vertex> BFS;

    public Search() {
        this.DFS = new ArrayList<>();
        this.BFS = new ArrayList<>();
    }

    /**
     * 深度优先实现
     */
    public void searchDFS(Graphic root){
        if(root == null){
            return;
        }
        DFS.add(root.getVertex());
        root.setVisited(true);
        for (Graphic node : root.getNeighborList()) {
            if(!node.isVisited()){
                searchDFS(node);
            }
        }
    }

    /**
     * 广度优先实现
     */
    public void searchBFS(Graphic root){
        Deque<Graphic> queue = new LinkedList<>();
        if(root == null){
            return;
        }
        BFS.add(root.getVertex());
        root.setVisited(true);

        // 加到队列中
        queue.addLast(root);

        while(!queue.isEmpty()){
            // 头部元素
            Graphic node = queue.poll();
            for (Graphic n : node.neighborList) {
                if(!n.isVisited()){
                    BFS.add(n.getVertex());
                    n.setVisited(true);
                    // 加入到队列中
                    queue.addLast(n);
                }
            }

        }
    }

    /**
     * 初始化所有节点的访问结果
     */
    public static void clearVisited(List<Graphic> graphics){
        for (Graphic graphic : graphics) {
            graphic.setVisited(false);
        }
    }



}
