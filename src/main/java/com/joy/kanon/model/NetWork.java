package com.joy.kanon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.enums.KColor;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 构建网络
 */
@Data
@ToString
public class NetWork {
    /**
     * 所有的位置轨迹
     */
    private List<Location> randomLocation;

    /**
     * DFS访问结果
     */
    private List<Location> dfs;

    /**
     * BFS访问结果
     */
    private List<Location> bfs;

    /**
     * 边信息
     */
    private List<Tuple2<Location>> edges;

    /**
     * 路况
     */
    @JsonIgnore
    public List<Graphic> road;

    private Cell[][] cells = new Cell[NetworkConfig.WIDTH][NetworkConfig.WIDTH];

    public NetWork() {
        edges = new ArrayList<>();
        randomLocation = new ArrayList<>();
        road = new ArrayList<>();
        /**
         * 网格初始化
         */
        for (int i = 0; i < NetworkConfig.WIDTH; i++) {
            for (int j = 0; j < NetworkConfig.WIDTH; j++) {
                cells[i][j] = new Cell(
                        i * NetworkConfig.CELL_SIZE,
                        j * NetworkConfig.CELL_SIZE,
                        0,
                        KColor.NOTHING.getColor());
            }
        }
        init();
    }

    public void init(){
        /**
         * 初始化路况
         */
        Graphic A = new Graphic(new Location("A", 0, 0, 0));
        Graphic B = new Graphic(new Location("B", 0, 20, 0));
        Graphic C = new Graphic(new Location("C", 0, 20, 40));
        Graphic D = new Graphic(new Location("D", 0, 0, 50));
        Graphic E = new Graphic(new Location("E", 0, 10, 50));
        Graphic F = new Graphic(new Location("F", 0, 10, 90));
        Graphic G = new Graphic(new Location("G", 0, 0, 90));
        Graphic H = new Graphic(new Location("H", 0, 0, 100));
        Graphic I = new Graphic(new Location("I", 0, 20, 100));
        Graphic J = new Graphic(new Location("J", 0, 100, 100));
        Graphic K = new Graphic(new Location("K", 0, 100, 70));
        Graphic L = new Graphic(new Location("L", 0, 80, 70));
        Graphic M = new Graphic(new Location("M", 0, 40, 15));
        Graphic N = new Graphic(new Location("N", 0, 40, 50));
        Graphic O = new Graphic(new Location("O", 0, 80, 15));
        Graphic P = new Graphic(new Location("P", 0, 100, 0));
        Graphic Q = new Graphic(new Location("Q", 0, 20, 50));
        Graphic R = new Graphic(new Location("R", 0, 20, 70));
        Graphic S = new Graphic(new Location("S", 0, 80, 0));

        A.getNeighborList().add(B);
        B.getNeighborList().addAll(Arrays.asList(A,C));
        C.getNeighborList().addAll(Arrays.asList(B,M,Q));
        D.getNeighborList().addAll(Arrays.asList(E));
        E.getNeighborList().addAll(Arrays.asList(D,Q,F));
        F.getNeighborList().addAll(Arrays.asList(E,G));
        G.getNeighborList().addAll(Arrays.asList(F,H));
        H.getNeighborList().addAll(Arrays.asList(G,I));
        I.getNeighborList().addAll(Arrays.asList(H,J,R));
        J.getNeighborList().addAll(Arrays.asList(I,K));
        K.getNeighborList().addAll(Arrays.asList(P,L,J));
        L.getNeighborList().addAll(Arrays.asList(K,M,R));
        M.getNeighborList().addAll(Arrays.asList(C,N,L,O));
        N.getNeighborList().addAll(Arrays.asList(M,Q));
        O.getNeighborList().addAll(Arrays.asList(M,S));
        P.getNeighborList().addAll(Arrays.asList(S,K));
        Q.getNeighborList().addAll(Arrays.asList(C,E,N,R));
        R.getNeighborList().addAll(Arrays.asList(Q,I,L));
        road.addAll(Arrays.asList(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S));


        /**
         * 初始化数据遍历数据
         */
        Search search = new Search();
        search.searchBFS(this.road.get(0));
        bfs = search.getBFS();
        clear();
        search.searchDFS(this.road.get(0));
        dfs = search.getDFS();
        clear();

        /**
         * 初始化边数据
         */
        for (Graphic r : road) {
            for (Graphic n : r.neighborList) {
                boolean same = false;
                Tuple2<Location> t = new Tuple2<>(r.getNode(), n.getNode());
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

        /**
         * 随机生成1000个数据点
         */
        for (int i = 0; i < NetworkConfig.RANDOM_NUM; i++) {
            Random random = new Random();
            int x = random.nextInt(NetworkConfig.WIDTH);
            int y = random.nextInt(NetworkConfig.WIDTH);
            Location point = new Location("RANDOM_LOCATION",
                    0,
                    x * NetworkConfig.CELL_SIZE,
                    y * NetworkConfig.CELL_SIZE);
            randomLocation.add(point);

            int number = cells[x][y].getNumber() + 1;
            cells[x][y].setNumber(number);
            if(number < NetworkConfig.THRESHOLD){
                cells[x][y].setColor(KColor.LESS.getColor());
            }else{
                cells[x][y].setColor(KColor.THAN.getColor());
            }
        }
        /**
         * 随机点排序
         */
        randomLocation = randomLocation.stream()
                .sorted(Comparator.comparing(Location::getX)
                        .thenComparing(Location::getY))
                .collect(Collectors.toList());
    }

    private String getKColor(int number){
        if(number == 0){
            return KColor.NOTHING.getColor();
        }else if(number < NetworkConfig.THRESHOLD){
            return KColor.LESS.getColor();
        }else{
            return KColor.THAN.getColor();
        }

    }

    public void clear(){
        Search.clearVisited(road);
    }


}
