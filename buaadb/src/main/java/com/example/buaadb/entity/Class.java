package com.example.buaadb.entity;

import java.io.Serializable;

public class Class implements Serializable {
    private Integer clno;

    private Integer scno;

    private static final long serialVersionUID = 1L;

    public Integer getClno() {
        return clno;
    }

    public void setClno(Integer clno) {
        this.clno = clno;
    }

    public Integer getScno() {
        return scno;
    }

    public void setScno(Integer scno) {
        this.scno = scno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clno=").append(clno);
        sb.append(", scno=").append(scno);
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
        Class other = (Class) that;
        return (this.getClno() == null ? other.getClno() == null : this.getClno().equals(other.getClno()))
            && (this.getScno() == null ? other.getScno() == null : this.getScno().equals(other.getScno()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClno() == null) ? 0 : getClno().hashCode());
        result = prime * result + ((getScno() == null) ? 0 : getScno().hashCode());
        return result;
    }
}