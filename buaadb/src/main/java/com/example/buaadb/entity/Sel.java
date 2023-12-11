package com.example.buaadb.entity;

import java.io.Serializable;

public class Sel extends SelKey implements Serializable {
    private Integer segrade;

    private static final long serialVersionUID = 1L;

    public Integer getSegrade() {
        return segrade;
    }

    public void setSegrade(Integer segrade) {
        this.segrade = segrade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", segrade=").append(segrade);
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
        Sel other = (Sel) that;
        return (this.getClno() == null ? other.getClno() == null : this.getClno().equals(other.getClno()))
            && (this.getSno() == null ? other.getSno() == null : this.getSno().equals(other.getSno()))
            && (this.getSegrade() == null ? other.getSegrade() == null : this.getSegrade().equals(other.getSegrade()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClno() == null) ? 0 : getClno().hashCode());
        result = prime * result + ((getSno() == null) ? 0 : getSno().hashCode());
        result = prime * result + ((getSegrade() == null) ? 0 : getSegrade().hashCode());
        return result;
    }
}