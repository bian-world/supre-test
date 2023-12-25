package com.supretest.base.domain;

import java.util.ArrayList;
import java.util.List;

public class ZendaoProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZendaoProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdIsNull() {
            addCriterion("zendao_product_id is null");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdIsNotNull() {
            addCriterion("zendao_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdEqualTo(String value) {
            addCriterion("zendao_product_id =", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdNotEqualTo(String value) {
            addCriterion("zendao_product_id <>", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdGreaterThan(String value) {
            addCriterion("zendao_product_id >", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_product_id >=", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdLessThan(String value) {
            addCriterion("zendao_product_id <", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdLessThanOrEqualTo(String value) {
            addCriterion("zendao_product_id <=", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdLike(String value) {
            addCriterion("zendao_product_id like", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdNotLike(String value) {
            addCriterion("zendao_product_id not like", value, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdIn(List<String> values) {
            addCriterion("zendao_product_id in", values, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdNotIn(List<String> values) {
            addCriterion("zendao_product_id not in", values, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdBetween(String value1, String value2) {
            addCriterion("zendao_product_id between", value1, value2, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProductIdNotBetween(String value1, String value2) {
            addCriterion("zendao_product_id not between", value1, value2, "zendaoProductId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdIsNull() {
            addCriterion("zendao_project_id is null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdIsNotNull() {
            addCriterion("zendao_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdEqualTo(String value) {
            addCriterion("zendao_project_id =", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdNotEqualTo(String value) {
            addCriterion("zendao_project_id <>", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdGreaterThan(String value) {
            addCriterion("zendao_project_id >", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_project_id >=", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdLessThan(String value) {
            addCriterion("zendao_project_id <", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdLessThanOrEqualTo(String value) {
            addCriterion("zendao_project_id <=", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdLike(String value) {
            addCriterion("zendao_project_id like", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdNotLike(String value) {
            addCriterion("zendao_project_id not like", value, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdIn(List<String> values) {
            addCriterion("zendao_project_id in", values, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdNotIn(List<String> values) {
            addCriterion("zendao_project_id not in", values, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdBetween(String value1, String value2) {
            addCriterion("zendao_project_id between", value1, value2, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectIdNotBetween(String value1, String value2) {
            addCriterion("zendao_project_id not between", value1, value2, "zendaoProjectId");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameIsNull() {
            addCriterion("zendao_project_name is null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameIsNotNull() {
            addCriterion("zendao_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameEqualTo(String value) {
            addCriterion("zendao_project_name =", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameNotEqualTo(String value) {
            addCriterion("zendao_project_name <>", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameGreaterThan(String value) {
            addCriterion("zendao_project_name >", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_project_name >=", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameLessThan(String value) {
            addCriterion("zendao_project_name <", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameLessThanOrEqualTo(String value) {
            addCriterion("zendao_project_name <=", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameLike(String value) {
            addCriterion("zendao_project_name like", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameNotLike(String value) {
            addCriterion("zendao_project_name not like", value, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameIn(List<String> values) {
            addCriterion("zendao_project_name in", values, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameNotIn(List<String> values) {
            addCriterion("zendao_project_name not in", values, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameBetween(String value1, String value2) {
            addCriterion("zendao_project_name between", value1, value2, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectNameNotBetween(String value1, String value2) {
            addCriterion("zendao_project_name not between", value1, value2, "zendaoProjectName");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusIsNull() {
            addCriterion("zendao_project_status is null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusIsNotNull() {
            addCriterion("zendao_project_status is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusEqualTo(String value) {
            addCriterion("zendao_project_status =", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusNotEqualTo(String value) {
            addCriterion("zendao_project_status <>", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusGreaterThan(String value) {
            addCriterion("zendao_project_status >", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_project_status >=", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusLessThan(String value) {
            addCriterion("zendao_project_status <", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusLessThanOrEqualTo(String value) {
            addCriterion("zendao_project_status <=", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusLike(String value) {
            addCriterion("zendao_project_status like", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusNotLike(String value) {
            addCriterion("zendao_project_status not like", value, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusIn(List<String> values) {
            addCriterion("zendao_project_status in", values, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusNotIn(List<String> values) {
            addCriterion("zendao_project_status not in", values, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusBetween(String value1, String value2) {
            addCriterion("zendao_project_status between", value1, value2, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoProjectStatusNotBetween(String value1, String value2) {
            addCriterion("zendao_project_status not between", value1, value2, "zendaoProjectStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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