package com.joy.kanon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joy.kanon.algo.Attack;
import com.joy.kanon.config.NetworkConfig;
import com.joy.kanon.enums.KColor;
import com.joy.kanon.util.ComputeUtil;
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
     * 所有的随机用户位置点位置轨迹
     */
    private List<Vertex> randomVertex = new ArrayList<>();

    /**
     * DFS访问结果
     */
    private List<Vertex> dfs = new ArrayList<>();

    /**
     * BFS访问结果
     */
    private List<Vertex> bfs = new ArrayList<>();

    /**
     * 边信息
     */
    private List<Edge> edges = new ArrayList<>();

    /**
     * 路况
     */
    @JsonIgnore
    public List<Graphic> road = new ArrayList<>();

    /**
     * 用户运行点
     */
    public List<Vertex> movePoint = new ArrayList<>();

    /**
     * 划分的网格
     */
    private Cell[][] cells;

    /**
     * 用户的实际运行轨迹
     */
    private List<Edge> movePath = new ArrayList<>();

    /**
     * 用户 - 区块
     */
    private List<UserWithBlock> userWithBlocks= new ArrayList<>();

    /**
     * 网络块
     */
    private List<Block> blocks = new ArrayList<>();

    /**
     * 系统配置
     */
    private NetworkConfig config;

    /**
     * 根据配置生成网络拓扑图
     */
    public NetWork(NetworkConfig config) {
        this.config = config;
        /**
         * 网格初始化
         */
        cells = new Cell[NetworkConfig.WIDTH][NetworkConfig.WIDTH];
        for (int i = 0; i < NetworkConfig.WIDTH; i++) {
            for (int j = 0; j < NetworkConfig.WIDTH; j++) {
                cells[i][j] = new Cell(
                        i ,
                        j,
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
        Graphic N1 = new Graphic(new Vertex("N1", 0, 0, 0));
        Graphic N2 = new Graphic(new Vertex("N2", 0, 200, 0));
        Graphic N3 = new Graphic(new Vertex("N3", 0, 0, 500));
        Graphic N4 = new Graphic(new Vertex("N4", 0, 100, 500));
        Graphic N5 = new Graphic(new Vertex("N5", 0, 100, 900));
        Graphic N6 = new Graphic(new Vertex("N6", 0, 0, 900));
        Graphic N7 = new Graphic(new Vertex("N7", 0, 0, 1000));
        Graphic N8 = new Graphic(new Vertex("N8", 0, 200, 1000));
        Graphic N9 = new Graphic(new Vertex("N9", 0, 1000, 1000));
        Graphic N10 = new Graphic(new Vertex("N10", 0, 1000, 700));
        Graphic N11 = new Graphic(new Vertex("N11", 0, 400, 150));
        Graphic N12 = new Graphic(new Vertex("N12", 0, 400, 500));
        Graphic N13 = new Graphic(new Vertex("N13", 0, 800, 150));
        Graphic N14 = new Graphic(new Vertex("N14", 0, 1000, 0));
        Graphic N15 = new Graphic(new Vertex("N15", 0, 200, 500));
        Graphic N16 = new Graphic(new Vertex("N16", 0, 200, 700));
        Graphic N17 = new Graphic(new Vertex("N17", 0, 800, 0));
        N1.getNeighborList().add(N2);
        N2.getNeighborList().addAll(Arrays.asList(N1,N15));
        N3.getNeighborList().addAll(Arrays.asList(N4));
        N4.getNeighborList().addAll(Arrays.asList(N3,N15,N5));
        N5.getNeighborList().addAll(Arrays.asList(N4,N6));
        N6.getNeighborList().addAll(Arrays.asList(N5,N7));
        N7.getNeighborList().addAll(Arrays.asList(N6,N8));
        N8.getNeighborList().addAll(Arrays.asList(N7,N9,N16));
        N9.getNeighborList().addAll(Arrays.asList(N8,N10));
        N10.getNeighborList().addAll(Arrays.asList(N14,N16,N9));
        N11.getNeighborList().addAll(Arrays.asList(N12,N13));
        N12.getNeighborList().addAll(Arrays.asList(N11,N15));
        N13.getNeighborList().addAll(Arrays.asList(N11,N17));
        N14.getNeighborList().addAll(Arrays.asList(N17,N10));
        N15.getNeighborList().addAll(Arrays.asList(N2,N4,N12,N16));
        N16.getNeighborList().addAll(Arrays.asList(N15,N8,N10));
        road.addAll(Arrays.asList(N1,N2,N3,N4,N5,N6,N7,N8,N9,N10,N11,N12,N13,N14,N15,N16,N17));


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
                Edge t = new Edge(r.getVertex().getName() + n.getVertex().getName(),r.getVertex(), n.getVertex());
                for (Edge edge : edges) {
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
         * 初始化网格，网格的顶点都是由图中的点构成的
         */
        blocks = new ArrayList<>();
        blocks.add(new Block(Arrays.asList(N1.getVertex(),N2.getVertex(),N15.getVertex(),N3.getVertex()),new Vertex("P1",0,80,250)));
        blocks.add(new Block(Arrays.asList(N3.getVertex(),N4.getVertex(),N5.getVertex(),N6.getVertex()),new Vertex("P2",0,50,700)));
        Vertex P3 = new Vertex("P3",0,200,900);
        blocks.add(new Block(Arrays.asList(N6.getVertex(),N7.getVertex(),N8.getVertex(),P3),new Vertex("P3",0,100,950)));

        Vertex P4 = new Vertex("P4",0,200,900);
        blocks.add(new Block(Arrays.asList(N4.getVertex(),N5.getVertex(),P4, N15.getVertex()),new Vertex("P4",0,150,700)));

        Vertex P5 = new Vertex("P5",0,400,0);
        blocks.add(new Block(Arrays.asList(N2.getVertex(),N15.getVertex(),N12.getVertex(),P5),new Vertex("P5",0,300,250)));

        Vertex P6 = new Vertex("P6",0,400,700);
        blocks.add(new Block(Arrays.asList(N15.getVertex(),N16.getVertex(),P6, N12.getVertex()),new Vertex("P6",0,300,600)));

        blocks.add(new Block(Arrays.asList(N16.getVertex(),N8.getVertex(),N9.getVertex(),N10.getVertex()),new Vertex("P7",0,600,850)));

        Vertex P8 = new Vertex("P8",0,1000,150);
        blocks.add(new Block(Arrays.asList(N11.getVertex(),P6, N10.getVertex(),P8),new Vertex("P8",0,700,400)));

        blocks.add(new Block(Arrays.asList(N17.getVertex(),N13.getVertex(),P8, N14.getVertex()),new Vertex("P9",0,700,400)));

        blocks.add(new Block(Arrays.asList(P5, N11.getVertex(),N13.getVertex(),N17.getVertex()),new Vertex("P10",0,700,400)));

        /**
         * 随机生成数据点
         */
        for (int i = 0; i < config.getRandomNum(); i++) {
            Random random = new Random();
            int x = random.nextInt(NetworkConfig.WIDTH);
            int y = random.nextInt(NetworkConfig.WIDTH);
            Vertex point = new Vertex("RANDOM_LOCATION",
                    0,
                    x * NetworkConfig.CELL_SIZE,
                    y * NetworkConfig.CELL_SIZE);
            randomVertex.add(point);

            int number = cells[x][y].getNumber() + 1;
            cells[x][y].setNumber(number);
            if(number < config.getThreshold()){
                cells[x][y].setColor(KColor.LESS.getColor());
            }else{
                cells[x][y].setColor(KColor.THAN.getColor());
            }
        }
        /**
         * 随机点排序
         */
        randomVertex = randomVertex.stream()
                .sorted(Comparator.comparing(Vertex::getX)
                        .thenComparing(Vertex::getY))
                .collect(Collectors.toList());

        /**
         * 用户运行轨迹
         */
        movePoint = new ArrayList<>();
        movePoint.add(new Vertex("u1", 0, 42, 12));
        movePoint.add(new Vertex("u2", 1, 175, 284));
        movePoint.add(new Vertex("u3", 2, 217, 614));
        movePoint.add(new Vertex("u4", 3, 387, 677));
        movePoint.add(new Vertex("u5", 4, 935, 683));

        /**
         * 推测用户路径，这作为最准确的路径存在
         */
        movePath = Attack.attack(this,movePoint);

        /**
         * 依次获取每个点所在的网格
         */
        userWithBlocks = new ArrayList<>();
        for (Vertex u : movePoint) {
            for (Block block : blocks) {
                if(Block.isInPolygon(u, block)){
                    userWithBlocks.add(new UserWithBlock(u, block));
                    break;
                }
            }
        }

    }

    private String getKColor(int number){
        if(number == 0){
            return KColor.NOTHING.getColor();
        }else if(number < config.getThreshold()){
            return KColor.LESS.getColor();
        }else{
            return KColor.THAN.getColor();
        }
    }

    public void clear(){
        Search.clearVisited(road);
    }

    /**
     * 根据运动点推算路网信息
     */
    public List<Edge> getPath(List<Vertex> vertices){
        List<Edge> result = new ArrayList<>();
        for (Vertex vertex : vertices) {
            double min = ComputeUtil.MAX_DISTANCE;
            Edge path = null;
            for (Edge edge : edges) {
                double distance = ComputeUtil.getInstance(edge, vertex);
                if(distance < min){
                    min = distance;
                    path = edge;
                }
            }
            if(!result.contains(path)){
                result.add(path);
            }
        }
        return result;
    }



}
