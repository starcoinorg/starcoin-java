package org.starcoin.bean;

public class Tvl <T>{

    private T t;

    private long value;

    public Tvl(T t, long value) {
        this.t = t;
        this.value = value;
    }

    public T getT() {
        return t;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Tvl{" +
                "t=" + t +
                ", value=" + value +
                '}';
    }
}
