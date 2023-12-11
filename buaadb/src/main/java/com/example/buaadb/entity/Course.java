package com.example.buaadb.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String cno;

    private String cname;

    private String cpos;

    private String ctype;

    private Integer tno;

    private Integer ccredit;

    private Integer ccapacity;

    private Integer cremain;

    private static final long serialVersionUID = 1L;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCpos() {
        return cpos;
    }

    public void setCpos(String cpos) {
        this.cpos = cpos == null ? null : cpos.trim();
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public Integer getCcredit() {
        return ccredit;
    }

    public void setCcredit(Integer ccredit) {
        this.ccredit = ccredit;
    }

    public Integer getCcapacity() {
        return ccapacity;
    }

    public void setCcapacity(Integer ccapacity) {
        this.ccapacity = ccapacity;
    }

    public Integer getCremain() {
        return cremain;
    }

    public void setCremain(Integer cremain) {
        this.cremain = cremain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cno=").append(cno);
        sb.append(", cname=").append(cname);
        sb.append(", cpos=").append(cpos);
        sb.append(", ctype=").append(ctype);
        sb.append(", tno=").append(tno);
        sb.append(", ccredit=").append(ccredit);
        sb.append(", ccapacity=").append(ccapacity);
        sb.append(", cremain=").append(cremain);
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
        Course other = (Course) that;
        return (this.getCno() == null ? other.getCno() == null : this.getCno().equals(other.getCno()))
            && (this.getCname() == null ? other.getCname() == null : this.getCname().equals(other.getCname()))
            && (this.getCpos() == null ? other.getCpos() == null : this.getCpos().equals(other.getCpos()))
            && (this.getCtype() == null ? other.getCtype() == null : this.getCtype().equals(other.getCtype()))
            && (this.getTno() == null ? other.getTno() == null : this.getTno().equals(other.getTno()))
            && (this.getCcredit() == null ? other.getCcredit() == null : this.getCcredit().equals(other.getCcredit()))
            && (this.getCcapacity() == null ? other.getCcapacity() == null : this.getCcapacity().equals(other.getCcapacity()))
            && (this.getCremain() == null ? other.getCremain() == null : this.getCremain().equals(other.getCremain()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCno() == null) ? 0 : getCno().hashCode());
        result = prime * result + ((getCname() == null) ? 0 : getCname().hashCode());
        result = prime * result + ((getCpos() == null) ? 0 : getCpos().hashCode());
        result = prime * result + ((getCtype() == null) ? 0 : getCtype().hashCode());
        result = prime * result + ((getTno() == null) ? 0 : getTno().hashCode());
        result = prime * result + ((getCcredit() == null) ? 0 : getCcredit().hashCode());
        result = prime * result + ((getCcapacity() == null) ? 0 : getCcapacity().hashCode());
        result = prime * result + ((getCremain() == null) ? 0 : getCremain().hashCode());
        return result;
    }
}