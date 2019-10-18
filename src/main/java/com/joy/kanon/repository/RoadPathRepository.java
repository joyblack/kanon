package com.joy.kanon.repository;

import com.joy.kanon.model.Location;
import com.joy.kanon.model.Graphic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 构建路况
 */
@Repository
public class RoadPathRepository {
    /**
     * 生成路况
     */
    public static final List<Graphic> GRAPHIC_A;

    static {
        System.out.println("生成基础路况...");
        Graphic A = new Graphic(new Location("A", 0, 0D, 0D));
        Graphic B = new Graphic(new Location("B", 0, 20D, 0D));
        Graphic C = new Graphic(new Location("C", 0, 20D, 40D));
        Graphic D = new Graphic(new Location("D", 0, 0D, 50D));
        Graphic E = new Graphic(new Location("E", 0, 10D, 50D));
        Graphic F = new Graphic(new Location("F", 0, 10D, 90D));
        Graphic G = new Graphic(new Location("G", 0, 0D, 90D));
        Graphic H = new Graphic(new Location("H", 0, 0D, 100D));
        Graphic I = new Graphic(new Location("I", 0, 20D, 100D));
        Graphic J = new Graphic(new Location("J", 0, 100D, 100D));
        Graphic K = new Graphic(new Location("K", 0, 100D, 70D));
        Graphic L = new Graphic(new Location("L", 0, 80D, 70D));
        Graphic M = new Graphic(new Location("M", 0, 40D, 15D));
        Graphic N = new Graphic(new Location("N", 0, 40D, 50D));
        Graphic O = new Graphic(new Location("O", 0, 80D, 15D));
        Graphic P = new Graphic(new Location("P", 0, 100D, 0D));
        Graphic Q = new Graphic(new Location("Q", 0, 20D, 50D));
        Graphic R = new Graphic(new Location("R", 0, 20D, 70D));
        Graphic S = new Graphic(new Location("S", 0, 80D, 0D));

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

        GRAPHIC_A = new ArrayList<>();
        GRAPHIC_A.addAll(Arrays.asList(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S));
    }

}
