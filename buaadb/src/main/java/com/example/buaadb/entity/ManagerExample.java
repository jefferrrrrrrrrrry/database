package com.example.buaadb.entity;

import java.util.ArrayList;
import java.util.List;

public class ManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManagerExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMnoIsNull() {
            addCriterion("Mno is null");
            return (Criteria) this;
        }

        public Criteria andMnoIsNotNull() {
            addCriterion("Mno is not null");
            return (Criteria) this;
        }

        public Criteria andMnoEqualTo(Integer value) {
            addCriterion("Mno =", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoNotEqualTo(Integer value) {
            addCriterion("Mno <>", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoGreaterThan(Integer value) {
            addCriterion("Mno >", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Mno >=", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoLessThan(Integer value) {
            addCriterion("Mno <", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoLessThanOrEqualTo(Integer value) {
            addCriterion("Mno <=", value, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoIn(List<Integer> values) {
            addCriterion("Mno in", values, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoNotIn(List<Integer> values) {
            addCriterion("Mno not in", values, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoBetween(Integer value1, Integer value2) {
            addCriterion("Mno between", value1, value2, "mno");
            return (Criteria) this;
        }

        public Criteria andMnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Mno not between", value1, value2, "mno");
            return (Criteria) this;
        }

        public Criteria andMnameIsNull() {
            addCriterion("Mname is null");
            return (Criteria) this;
        }

        public Criteria andMnameIsNotNull() {
            addCriterion("Mname is not null");
            return (Criteria) this;
        }

        public Criteria andMnameEqualTo(String value) {
            addCriterion("Mname =", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotEqualTo(String value) {
            addCriterion("Mname <>", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThan(String value) {
            addCriterion("Mname >", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThanOrEqualTo(String value) {
            addCriterion("Mname >=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThan(String value) {
            addCriterion("Mname <", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThanOrEqualTo(String value) {
            addCriterion("Mname <=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLike(String value) {
            addCriterion("Mname like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotLike(String value) {
            addCriterion("Mname not like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameIn(List<String> values) {
            addCriterion("Mname in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotIn(List<String> values) {
            addCriterion("Mname not in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameBetween(String value1, String value2) {
            addCriterion("Mname between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotBetween(String value1, String value2) {
            addCriterion("Mname not between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andMpasswordIsNull() {
            addCriterion("Mpassword is null");
            return (Criteria) this;
        }

        public Criteria andMpasswordIsNotNull() {
            addCriterion("Mpassword is not null");
            return (Criteria) this;
        }

        public Criteria andMpasswordEqualTo(String value) {
            addCriterion("Mpassword =", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordNotEqualTo(String value) {
            addCriterion("Mpassword <>", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordGreaterThan(String value) {
            addCriterion("Mpassword >", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("Mpassword >=", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordLessThan(String value) {
            addCriterion("Mpassword <", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordLessThanOrEqualTo(String value) {
            addCriterion("Mpassword <=", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordLike(String value) {
            addCriterion("Mpassword like", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordNotLike(String value) {
            addCriterion("Mpassword not like", value, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordIn(List<String> values) {
            addCriterion("Mpassword in", values, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordNotIn(List<String> values) {
            addCriterion("Mpassword not in", values, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordBetween(String value1, String value2) {
            addCriterion("Mpassword between", value1, value2, "mpassword");
            return (Criteria) this;
        }

        public Criteria andMpasswordNotBetween(String value1, String value2) {
            addCriterion("Mpassword not between", value1, value2, "mpassword");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}