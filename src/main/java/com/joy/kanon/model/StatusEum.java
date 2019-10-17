package com.joy.kanon.model;

public enum StatusEum {
    NEW("新"), OLD("旧"), DISAPPEAR("消失");

    private String status;

    StatusEum(String status) {
        this.status = status;
    }

    public static StatusEum get(String value){
        String value_new = "newpoint";
        String value_old = "point";
        String value_disappear_point = "disappearpoint";

        if(value.equals(value_new)){
            return NEW;
        }
        if(value.equals(value_old)) {
            return OLD;
        }
        if(value.equals(value_disappear_point)){
            return DISAPPEAR;
        }
        return null;
    }
}
