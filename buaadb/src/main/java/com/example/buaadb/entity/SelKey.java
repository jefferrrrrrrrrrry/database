package com.example.buaadb.entity;

import java.io.Serializable;

public class SelKey implements Serializable {
    private Integer clno;

    private Integer sno;

    private static final long serialVersionUID = 1L;

    public Integer getClno() {
        return clno;
    }

    public void setClno(Integer clno) {
        this.clno = clno;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clno=").append(clno);
        sb.append(", sno=").append(sno);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SelKey other = (SelKey) that;
        return (this.getClno() == null ? other.getClno() == null : this.getClno().equals(other.getClno()))
            && (this.getSno() == null ? other.getSno() == null : this.getSno().equals(other.getSno()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClno() == null) ? 0 : getClno().hashCode());
        result = prime * result + ((getSno() == null) ? 0 : getSno().hashCode());
        return result;
    }
}