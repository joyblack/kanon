package com.joy.kanon.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserWithBlock {
    private Vertex user;
    private Block block;

    public UserWithBlock(Vertex user, Block block) {
        this.user = user;
        this.block = block;
    }
}
