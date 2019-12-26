package com.WarNewsFeed.wnf.helpers;

import java.util.Objects;

public class Triplet <X,Y,Z> {

    public X val1;
    public Y val2;
    public Z val3;

    public Triplet(X val1, Y val2, Z val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return Objects.equals(val1, triplet.val1) &&
                Objects.equals(val2, triplet.val2) &&
                Objects.equals(val3, triplet.val3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val1, val2, val3);
    }

    @Override
    public String toString() {
        return "val1= " + val1
                +" "+"val2= "+ val2
                +" "+"val3= "+ val3;
    }
}
