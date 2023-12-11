package com.example.buaadb.entity;

import java.util.ArrayList;
import java.util.List;

public class SchoolExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchoolExample() {
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

        public Criteria andScnoIsNull() {
            addCriterion("Scno is null");
            return (Criteria) this;
        }

        public Criteria andScnoIsNotNull() {
            addCriterion("Scno is not null");
            return (Criteria) this;
        }

        public Criteria andScnoEqualTo(Integer value) {
            addCriterion("Scno =", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotEqualTo(Integer value) {
            addCriterion("Scno <>", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoGreaterThan(Integer value) {
            addCriterion("Scno >", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Scno >=", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoLessThan(Integer value) {
            addCriterion("Scno <", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoLessThanOrEqualTo(Integer value) {
            addCriterion("Scno <=", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoIn(List<Integer> values) {
            addCriterion("Scno in", values, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotIn(List<Integer> values) {
            addCriterion("Scno not in", values, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoBetween(Integer value1, Integer value2) {
            addCriterion("Scno between", value1, value2, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Scno not between", value1, value2, "scno");
            return (Criteria) this;
        }

        public Criteria andScnameIsNull() {
            addCriterion("Scname is null");
            return (Criteria) this;
        }

        public Criteria andScnameIsNotNull() {
            addCriterion("Scname is not null");
            return (Criteria) this;
        }

        public Criteria andScnameEqualTo(String value) {
            addCriterion("Scname =", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameNotEqualTo(String value) {
            addCriterion("Scname <>", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameGreaterThan(String value) {
            addCriterion("Scname >", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameGreaterThanOrEqualTo(String value) {
            addCriterion("Scname >=", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameLessThan(String value) {
            addCriterion("Scname <", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameLessThanOrEqualTo(String value) {
            addCriterion("Scname <=", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameLike(String value) {
            addCriterion("Scname like", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameNotLike(String value) {
            addCriterion("Scname not like", value, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameIn(List<String> values) {
            addCriterion("Scname in", values, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameNotIn(List<String> values) {
            addCriterion("Scname not in", values, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameBetween(String value1, String value2) {
            addCriterion("Scname between", value1, value2, "scname");
            return (Criteria) this;
        }

        public Criteria andScnameNotBetween(String value1, String value2) {
            addCriterion("Scname not between", value1, value2, "scname");
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