package com.oglasi.model;
public class Status {
    public static final Short INACTIVE = 1;
    public static final Short ACTIVE = 2;
    public static final Short UPDATE = 3;
    public static final Short EXPIRE = 4;
    public static final Short CANCELED = 5;
    
    public static Object[] fields(){
        Object[] result =  {INACTIVE, ACTIVE, UPDATE, EXPIRE, CANCELED};
        return result;
    }
}
