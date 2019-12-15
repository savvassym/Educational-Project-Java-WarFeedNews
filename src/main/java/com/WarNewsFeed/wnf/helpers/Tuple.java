package com.WarNewsFeed.wnf.helpers;

import java.util.Objects;

public class Tuple<X,Y> {

    public X val1;
    public Y val2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(val1, tuple.val1) &&
                Objects.equals(val2, tuple.val2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val1, val2);
    }

    public Tuple(X x, Y y){
        this.val1 = x;
        this.val2 = y;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                '}';
    }
}
