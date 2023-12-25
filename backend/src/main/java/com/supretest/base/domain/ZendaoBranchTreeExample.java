package com.supretest.base.domain;

import java.util.ArrayList;
import java.util.List;

public class ZendaoBranchTreeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZendaoBranchTreeExample() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
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

        public Criteria andZendaoBranchIdIsNull() {
            addCriterion("zendao_branch_id is null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdIsNotNull() {
            addCriterion("zendao_branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdEqualTo(String value) {
            addCriterion("zendao_branch_id =", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdNotEqualTo(String value) {
            addCriterion("zendao_branch_id <>", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdGreaterThan(String value) {
            addCriterion("zendao_branch_id >", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_branch_id >=", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdLessThan(String value) {
            addCriterion("zendao_branch_id <", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdLessThanOrEqualTo(String value) {
            addCriterion("zendao_branch_id <=", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdLike(String value) {
            addCriterion("zendao_branch_id like", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdNotLike(String value) {
            addCriterion("zendao_branch_id not like", value, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdIn(List<String> values) {
            addCriterion("zendao_branch_id in", values, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdNotIn(List<String> values) {
            addCriterion("zendao_branch_id not in", values, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdBetween(String value1, String value2) {
            addCriterion("zendao_branch_id between", value1, value2, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchIdNotBetween(String value1, String value2) {
            addCriterion("zendao_branch_id not between", value1, value2, "zendaoBranchId");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameIsNull() {
            addCriterion("zendao_branch_name is null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameIsNotNull() {
            addCriterion("zendao_branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameEqualTo(String value) {
            addCriterion("zendao_branch_name =", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameNotEqualTo(String value) {
            addCriterion("zendao_branch_name <>", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameGreaterThan(String value) {
            addCriterion("zendao_branch_name >", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_branch_name >=", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameLessThan(String value) {
            addCriterion("zendao_branch_name <", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameLessThanOrEqualTo(String value) {
            addCriterion("zendao_branch_name <=", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameLike(String value) {
            addCriterion("zendao_branch_name like", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameNotLike(String value) {
            addCriterion("zendao_branch_name not like", value, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameIn(List<String> values) {
            addCriterion("zendao_branch_name in", values, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameNotIn(List<String> values) {
            addCriterion("zendao_branch_name not in", values, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameBetween(String value1, String value2) {
            addCriterion("zendao_branch_name between", value1, value2, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchNameNotBetween(String value1, String value2) {
            addCriterion("zendao_branch_name not between", value1, value2, "zendaoBranchName");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusIsNull() {
            addCriterion("zendao_branch_status is null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusIsNotNull() {
            addCriterion("zendao_branch_status is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusEqualTo(String value) {
            addCriterion("zendao_branch_status =", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusNotEqualTo(String value) {
            addCriterion("zendao_branch_status <>", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusGreaterThan(String value) {
            addCriterion("zendao_branch_status >", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_branch_status >=", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusLessThan(String value) {
            addCriterion("zendao_branch_status <", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusLessThanOrEqualTo(String value) {
            addCriterion("zendao_branch_status <=", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusLike(String value) {
            addCriterion("zendao_branch_status like", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusNotLike(String value) {
            addCriterion("zendao_branch_status not like", value, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusIn(List<String> values) {
            addCriterion("zendao_branch_status in", values, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusNotIn(List<String> values) {
            addCriterion("zendao_branch_status not in", values, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusBetween(String value1, String value2) {
            addCriterion("zendao_branch_status between", value1, value2, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoBranchStatusNotBetween(String value1, String value2) {
            addCriterion("zendao_branch_status not between", value1, value2, "zendaoBranchStatus");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdIsNull() {
            addCriterion("zendao_module_id is null");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdIsNotNull() {
            addCriterion("zendao_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdEqualTo(String value) {
            addCriterion("zendao_module_id =", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdNotEqualTo(String value) {
            addCriterion("zendao_module_id <>", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdGreaterThan(String value) {
            addCriterion("zendao_module_id >", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_module_id >=", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdLessThan(String value) {
            addCriterion("zendao_module_id <", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdLessThanOrEqualTo(String value) {
            addCriterion("zendao_module_id <=", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdLike(String value) {
            addCriterion("zendao_module_id like", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdNotLike(String value) {
            addCriterion("zendao_module_id not like", value, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdIn(List<String> values) {
            addCriterion("zendao_module_id in", values, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdNotIn(List<String> values) {
            addCriterion("zendao_module_id not in", values, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdBetween(String value1, String value2) {
            addCriterion("zendao_module_id between", value1, value2, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleIdNotBetween(String value1, String value2) {
            addCriterion("zendao_module_id not between", value1, value2, "zendaoModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameIsNull() {
            addCriterion("zendao_module_name is null");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameIsNotNull() {
            addCriterion("zendao_module_name is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameEqualTo(String value) {
            addCriterion("zendao_module_name =", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameNotEqualTo(String value) {
            addCriterion("zendao_module_name <>", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameGreaterThan(String value) {
            addCriterion("zendao_module_name >", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_module_name >=", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameLessThan(String value) {
            addCriterion("zendao_module_name <", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameLessThanOrEqualTo(String value) {
            addCriterion("zendao_module_name <=", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameLike(String value) {
            addCriterion("zendao_module_name like", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameNotLike(String value) {
            addCriterion("zendao_module_name not like", value, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameIn(List<String> values) {
            addCriterion("zendao_module_name in", values, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameNotIn(List<String> values) {
            addCriterion("zendao_module_name not in", values, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameBetween(String value1, String value2) {
            addCriterion("zendao_module_name between", value1, value2, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoModuleNameNotBetween(String value1, String value2) {
            addCriterion("zendao_module_name not between", value1, value2, "zendaoModuleName");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdIsNull() {
            addCriterion("zendao_parent_module_id is null");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdIsNotNull() {
            addCriterion("zendao_parent_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdEqualTo(String value) {
            addCriterion("zendao_parent_module_id =", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdNotEqualTo(String value) {
            addCriterion("zendao_parent_module_id <>", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdGreaterThan(String value) {
            addCriterion("zendao_parent_module_id >", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_parent_module_id >=", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdLessThan(String value) {
            addCriterion("zendao_parent_module_id <", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdLessThanOrEqualTo(String value) {
            addCriterion("zendao_parent_module_id <=", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdLike(String value) {
            addCriterion("zendao_parent_module_id like", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdNotLike(String value) {
            addCriterion("zendao_parent_module_id not like", value, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdIn(List<String> values) {
            addCriterion("zendao_parent_module_id in", values, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdNotIn(List<String> values) {
            addCriterion("zendao_parent_module_id not in", values, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdBetween(String value1, String value2) {
            addCriterion("zendao_parent_module_id between", value1, value2, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoParentModuleIdNotBetween(String value1, String value2) {
            addCriterion("zendao_parent_module_id not between", value1, value2, "zendaoParentModuleId");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathIsNull() {
            addCriterion("zendao_module_path is null");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathIsNotNull() {
            addCriterion("zendao_module_path is not null");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathEqualTo(String value) {
            addCriterion("zendao_module_path =", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathNotEqualTo(String value) {
            addCriterion("zendao_module_path <>", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathGreaterThan(String value) {
            addCriterion("zendao_module_path >", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathGreaterThanOrEqualTo(String value) {
            addCriterion("zendao_module_path >=", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathLessThan(String value) {
            addCriterion("zendao_module_path <", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathLessThanOrEqualTo(String value) {
            addCriterion("zendao_module_path <=", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathLike(String value) {
            addCriterion("zendao_module_path like", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathNotLike(String value) {
            addCriterion("zendao_module_path not like", value, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathIn(List<String> values) {
            addCriterion("zendao_module_path in", values, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathNotIn(List<String> values) {
            addCriterion("zendao_module_path not in", values, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathBetween(String value1, String value2) {
            addCriterion("zendao_module_path between", value1, value2, "zendaoModulePath");
            return (Criteria) this;
        }

        public Criteria andZendaoModulePathNotBetween(String value1, String value2) {
            addCriterion("zendao_module_path not between", value1, value2, "zendaoModulePath");
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