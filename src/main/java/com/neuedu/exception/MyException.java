package com.neuedu.exception;

public class MyException extends RuntimeException{


    private String direction;

    public MyException(){}

    public MyException(String msg){
        super(msg);

    }

    public MyException(String msg,String direction){
        super(msg);
        this.direction=direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
