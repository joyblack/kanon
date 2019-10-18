package com.joy.kanon.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.joy.kanon.repository.RoadPathRepository;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建网络 1000*1000
 */
@Data
@ToString
public class NetWork {
    public static final int NET_WORK_WIDTH = 100;
    public static final int NET_WORK_HEIGHT = 100;

    /**
     * 用户的运行轨迹（几个点）
     */
    private List<Location> MOVE_PATH;

    /**
     * 路况图
     */
    @JsonBackReference
    private List<Graphic> graphic;

    /**
     * DFS访问结果
     */
    private List<Location> DFS;

    /**
     * BFS访问结果
     */
    private List<Location> BFS;

    /**
     * 边信息
     */
    private List<Tuple2<Location>> edges;





    public NetWork() {
//        this.MOVE_PATH = MOVE_PATH;
        this.graphic = RoadPathRepository.GRAPHIC_A;
        edges = new ArrayList<>();
        init();
    }

    public void init(){
        /**
         * 初始化数据遍历数据
         */
        Search search = new Search();
        search.searchBFS(this.graphic.get(0));
        BFS = search.getBFS();
        clear();
        search.searchDFS(this.getGraphic().get(0));
        DFS = search.getDFS();
        clear();

        /**
         * 初始化边数据
         */
        for (Graphic graphic1 : graphic) {
            for (Graphic n : graphic1.neighborList) {
                boolean same = false;
                Tuple2<Location> t = new Tuple2<>(graphic1.getNode(), n.getNode());
                for (Tuple2<Location> edge : edges) {
                    same = (edge.getV1().equals(t.getV1()) && edge.getV2().equals(t.getV2())) ||
                            (edge.getV1().equals(t.getV2()) && edge.getV2().equals(t.getV1()));

                    if(same){
                        break;
                    }
                }
                if(!same){
                    edges.add(t);
                }
            }
        }
    }

    public void clear(){
        Search.clearVisited(graphic);
    }


}
