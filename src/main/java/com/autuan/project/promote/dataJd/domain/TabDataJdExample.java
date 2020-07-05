package com.autuan.project.promote.dataJd.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TabDataJdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public TabDataJdExample() {
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
        rows = null;
        offset = null;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public TabDataJdExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public TabDataJdExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public TabDataJdExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
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

        public Criteria andRecordTimeIsNull() {
            addCriterion("record_time is null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNotNull() {
            addCriterion("record_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeEqualTo(LocalDateTime value) {
            addCriterion("record_time =", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotEqualTo(LocalDateTime value) {
            addCriterion("record_time <>", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThan(LocalDateTime value) {
            addCriterion("record_time >", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("record_time >=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThan(LocalDateTime value) {
            addCriterion("record_time <", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("record_time <=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIn(List<LocalDateTime> values) {
            addCriterion("record_time in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotIn(List<LocalDateTime> values) {
            addCriterion("record_time not in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("record_time between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("record_time not between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdIsNull() {
            addCriterion("task_inner_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdIsNotNull() {
            addCriterion("task_inner_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdEqualTo(String value) {
            addCriterion("task_inner_id =", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdNotEqualTo(String value) {
            addCriterion("task_inner_id <>", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdGreaterThan(String value) {
            addCriterion("task_inner_id >", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_inner_id >=", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdLessThan(String value) {
            addCriterion("task_inner_id <", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdLessThanOrEqualTo(String value) {
            addCriterion("task_inner_id <=", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdLike(String value) {
            addCriterion("task_inner_id like", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdNotLike(String value) {
            addCriterion("task_inner_id not like", value, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdIn(List<String> values) {
            addCriterion("task_inner_id in", values, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdNotIn(List<String> values) {
            addCriterion("task_inner_id not in", values, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdBetween(String value1, String value2) {
            addCriterion("task_inner_id between", value1, value2, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskInnerIdNotBetween(String value1, String value2) {
            addCriterion("task_inner_id not between", value1, value2, "taskInnerId");
            return (Criteria) this;
        }

        public Criteria andTaskUrlIsNull() {
            addCriterion("task_url is null");
            return (Criteria) this;
        }

        public Criteria andTaskUrlIsNotNull() {
            addCriterion("task_url is not null");
            return (Criteria) this;
        }

        public Criteria andTaskUrlEqualTo(String value) {
            addCriterion("task_url =", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlNotEqualTo(String value) {
            addCriterion("task_url <>", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlGreaterThan(String value) {
            addCriterion("task_url >", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlGreaterThanOrEqualTo(String value) {
            addCriterion("task_url >=", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlLessThan(String value) {
            addCriterion("task_url <", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlLessThanOrEqualTo(String value) {
            addCriterion("task_url <=", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlLike(String value) {
            addCriterion("task_url like", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlNotLike(String value) {
            addCriterion("task_url not like", value, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlIn(List<String> values) {
            addCriterion("task_url in", values, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlNotIn(List<String> values) {
            addCriterion("task_url not in", values, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlBetween(String value1, String value2) {
            addCriterion("task_url between", value1, value2, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andTaskUrlNotBetween(String value1, String value2) {
            addCriterion("task_url not between", value1, value2, "taskUrl");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNameIsNull() {
            addCriterion("order_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderNameIsNotNull() {
            addCriterion("order_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNameEqualTo(String value) {
            addCriterion("order_name =", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotEqualTo(String value) {
            addCriterion("order_name <>", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThan(String value) {
            addCriterion("order_name >", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_name >=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThan(String value) {
            addCriterion("order_name <", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThanOrEqualTo(String value) {
            addCriterion("order_name <=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLike(String value) {
            addCriterion("order_name like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotLike(String value) {
            addCriterion("order_name not like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameIn(List<String> values) {
            addCriterion("order_name in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotIn(List<String> values) {
            addCriterion("order_name not in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameBetween(String value1, String value2) {
            addCriterion("order_name between", value1, value2, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotBetween(String value1, String value2) {
            addCriterion("order_name not between", value1, value2, "orderName");
            return (Criteria) this;
        }

        public Criteria andChannelFirstIsNull() {
            addCriterion("channel_first is null");
            return (Criteria) this;
        }

        public Criteria andChannelFirstIsNotNull() {
            addCriterion("channel_first is not null");
            return (Criteria) this;
        }

        public Criteria andChannelFirstEqualTo(String value) {
            addCriterion("channel_first =", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstNotEqualTo(String value) {
            addCriterion("channel_first <>", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstGreaterThan(String value) {
            addCriterion("channel_first >", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstGreaterThanOrEqualTo(String value) {
            addCriterion("channel_first >=", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstLessThan(String value) {
            addCriterion("channel_first <", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstLessThanOrEqualTo(String value) {
            addCriterion("channel_first <=", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstLike(String value) {
            addCriterion("channel_first like", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstNotLike(String value) {
            addCriterion("channel_first not like", value, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstIn(List<String> values) {
            addCriterion("channel_first in", values, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstNotIn(List<String> values) {
            addCriterion("channel_first not in", values, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstBetween(String value1, String value2) {
            addCriterion("channel_first between", value1, value2, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelFirstNotBetween(String value1, String value2) {
            addCriterion("channel_first not between", value1, value2, "channelFirst");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceIsNull() {
            addCriterion("channel_below_source is null");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceIsNotNull() {
            addCriterion("channel_below_source is not null");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceEqualTo(String value) {
            addCriterion("channel_below_source =", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceNotEqualTo(String value) {
            addCriterion("channel_below_source <>", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceGreaterThan(String value) {
            addCriterion("channel_below_source >", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceGreaterThanOrEqualTo(String value) {
            addCriterion("channel_below_source >=", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceLessThan(String value) {
            addCriterion("channel_below_source <", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceLessThanOrEqualTo(String value) {
            addCriterion("channel_below_source <=", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceLike(String value) {
            addCriterion("channel_below_source like", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceNotLike(String value) {
            addCriterion("channel_below_source not like", value, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceIn(List<String> values) {
            addCriterion("channel_below_source in", values, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceNotIn(List<String> values) {
            addCriterion("channel_below_source not in", values, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceBetween(String value1, String value2) {
            addCriterion("channel_below_source between", value1, value2, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andChannelBelowSourceNotBetween(String value1, String value2) {
            addCriterion("channel_below_source not between", value1, value2, "channelBelowSource");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNull() {
            addCriterion("check_reason is null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNotNull() {
            addCriterion("check_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonEqualTo(String value) {
            addCriterion("check_reason =", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotEqualTo(String value) {
            addCriterion("check_reason <>", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThan(String value) {
            addCriterion("check_reason >", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThanOrEqualTo(String value) {
            addCriterion("check_reason >=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThan(String value) {
            addCriterion("check_reason <", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThanOrEqualTo(String value) {
            addCriterion("check_reason <=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLike(String value) {
            addCriterion("check_reason like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotLike(String value) {
            addCriterion("check_reason not like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIn(List<String> values) {
            addCriterion("check_reason in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotIn(List<String> values) {
            addCriterion("check_reason not in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonBetween(String value1, String value2) {
            addCriterion("check_reason between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotBetween(String value1, String value2) {
            addCriterion("check_reason not between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinIsNull() {
            addCriterion("join_jd_pin is null");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinIsNotNull() {
            addCriterion("join_jd_pin is not null");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinEqualTo(String value) {
            addCriterion("join_jd_pin =", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinNotEqualTo(String value) {
            addCriterion("join_jd_pin <>", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinGreaterThan(String value) {
            addCriterion("join_jd_pin >", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinGreaterThanOrEqualTo(String value) {
            addCriterion("join_jd_pin >=", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinLessThan(String value) {
            addCriterion("join_jd_pin <", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinLessThanOrEqualTo(String value) {
            addCriterion("join_jd_pin <=", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinLike(String value) {
            addCriterion("join_jd_pin like", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinNotLike(String value) {
            addCriterion("join_jd_pin not like", value, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinIn(List<String> values) {
            addCriterion("join_jd_pin in", values, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinNotIn(List<String> values) {
            addCriterion("join_jd_pin not in", values, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinBetween(String value1, String value2) {
            addCriterion("join_jd_pin between", value1, value2, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinJdPinNotBetween(String value1, String value2) {
            addCriterion("join_jd_pin not between", value1, value2, "joinJdPin");
            return (Criteria) this;
        }

        public Criteria andJoinLinkIsNull() {
            addCriterion("join_link is null");
            return (Criteria) this;
        }

        public Criteria andJoinLinkIsNotNull() {
            addCriterion("join_link is not null");
            return (Criteria) this;
        }

        public Criteria andJoinLinkEqualTo(String value) {
            addCriterion("join_link =", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkNotEqualTo(String value) {
            addCriterion("join_link <>", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkGreaterThan(String value) {
            addCriterion("join_link >", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkGreaterThanOrEqualTo(String value) {
            addCriterion("join_link >=", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkLessThan(String value) {
            addCriterion("join_link <", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkLessThanOrEqualTo(String value) {
            addCriterion("join_link <=", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkLike(String value) {
            addCriterion("join_link like", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkNotLike(String value) {
            addCriterion("join_link not like", value, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkIn(List<String> values) {
            addCriterion("join_link in", values, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkNotIn(List<String> values) {
            addCriterion("join_link not in", values, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkBetween(String value1, String value2) {
            addCriterion("join_link between", value1, value2, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinLinkNotBetween(String value1, String value2) {
            addCriterion("join_link not between", value1, value2, "joinLink");
            return (Criteria) this;
        }

        public Criteria andJoinTimeIsNull() {
            addCriterion("join_time is null");
            return (Criteria) this;
        }

        public Criteria andJoinTimeIsNotNull() {
            addCriterion("join_time is not null");
            return (Criteria) this;
        }

        public Criteria andJoinTimeEqualTo(LocalDateTime value) {
            addCriterion("join_time =", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotEqualTo(LocalDateTime value) {
            addCriterion("join_time <>", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeGreaterThan(LocalDateTime value) {
            addCriterion("join_time >", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("join_time >=", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeLessThan(LocalDateTime value) {
            addCriterion("join_time <", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("join_time <=", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeIn(List<LocalDateTime> values) {
            addCriterion("join_time in", values, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotIn(List<LocalDateTime> values) {
            addCriterion("join_time not in", values, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("join_time between", value1, value2, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("join_time not between", value1, value2, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinOrderIsNull() {
            addCriterion("join_order is null");
            return (Criteria) this;
        }

        public Criteria andJoinOrderIsNotNull() {
            addCriterion("join_order is not null");
            return (Criteria) this;
        }

        public Criteria andJoinOrderEqualTo(String value) {
            addCriterion("join_order =", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderNotEqualTo(String value) {
            addCriterion("join_order <>", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderGreaterThan(String value) {
            addCriterion("join_order >", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderGreaterThanOrEqualTo(String value) {
            addCriterion("join_order >=", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderLessThan(String value) {
            addCriterion("join_order <", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderLessThanOrEqualTo(String value) {
            addCriterion("join_order <=", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderLike(String value) {
            addCriterion("join_order like", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderNotLike(String value) {
            addCriterion("join_order not like", value, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderIn(List<String> values) {
            addCriterion("join_order in", values, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderNotIn(List<String> values) {
            addCriterion("join_order not in", values, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderBetween(String value1, String value2) {
            addCriterion("join_order between", value1, value2, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andJoinOrderNotBetween(String value1, String value2) {
            addCriterion("join_order not between", value1, value2, "joinOrder");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinIsNull() {
            addCriterion("open_jd_credit_pin is null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinIsNotNull() {
            addCriterion("open_jd_credit_pin is not null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinEqualTo(String value) {
            addCriterion("open_jd_credit_pin =", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinNotEqualTo(String value) {
            addCriterion("open_jd_credit_pin <>", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinGreaterThan(String value) {
            addCriterion("open_jd_credit_pin >", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinGreaterThanOrEqualTo(String value) {
            addCriterion("open_jd_credit_pin >=", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinLessThan(String value) {
            addCriterion("open_jd_credit_pin <", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinLessThanOrEqualTo(String value) {
            addCriterion("open_jd_credit_pin <=", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinLike(String value) {
            addCriterion("open_jd_credit_pin like", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinNotLike(String value) {
            addCriterion("open_jd_credit_pin not like", value, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinIn(List<String> values) {
            addCriterion("open_jd_credit_pin in", values, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinNotIn(List<String> values) {
            addCriterion("open_jd_credit_pin not in", values, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinBetween(String value1, String value2) {
            addCriterion("open_jd_credit_pin between", value1, value2, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditPinNotBetween(String value1, String value2) {
            addCriterion("open_jd_credit_pin not between", value1, value2, "openJdCreditPin");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlIsNull() {
            addCriterion("open_jd_credit_url is null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlIsNotNull() {
            addCriterion("open_jd_credit_url is not null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlEqualTo(String value) {
            addCriterion("open_jd_credit_url =", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlNotEqualTo(String value) {
            addCriterion("open_jd_credit_url <>", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlGreaterThan(String value) {
            addCriterion("open_jd_credit_url >", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlGreaterThanOrEqualTo(String value) {
            addCriterion("open_jd_credit_url >=", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlLessThan(String value) {
            addCriterion("open_jd_credit_url <", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlLessThanOrEqualTo(String value) {
            addCriterion("open_jd_credit_url <=", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlLike(String value) {
            addCriterion("open_jd_credit_url like", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlNotLike(String value) {
            addCriterion("open_jd_credit_url not like", value, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlIn(List<String> values) {
            addCriterion("open_jd_credit_url in", values, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlNotIn(List<String> values) {
            addCriterion("open_jd_credit_url not in", values, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlBetween(String value1, String value2) {
            addCriterion("open_jd_credit_url between", value1, value2, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditUrlNotBetween(String value1, String value2) {
            addCriterion("open_jd_credit_url not between", value1, value2, "openJdCreditUrl");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeIsNull() {
            addCriterion("open_jd_credit_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeIsNotNull() {
            addCriterion("open_jd_credit_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeEqualTo(LocalDateTime value) {
            addCriterion("open_jd_credit_time =", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeNotEqualTo(LocalDateTime value) {
            addCriterion("open_jd_credit_time <>", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeGreaterThan(LocalDateTime value) {
            addCriterion("open_jd_credit_time >", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("open_jd_credit_time >=", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeLessThan(LocalDateTime value) {
            addCriterion("open_jd_credit_time <", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("open_jd_credit_time <=", value, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeIn(List<LocalDateTime> values) {
            addCriterion("open_jd_credit_time in", values, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeNotIn(List<LocalDateTime> values) {
            addCriterion("open_jd_credit_time not in", values, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("open_jd_credit_time between", value1, value2, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("open_jd_credit_time not between", value1, value2, "openJdCreditTime");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeIsNull() {
            addCriterion("open_jd_credit_type is null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeIsNotNull() {
            addCriterion("open_jd_credit_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeEqualTo(Integer value) {
            addCriterion("open_jd_credit_type =", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeNotEqualTo(Integer value) {
            addCriterion("open_jd_credit_type <>", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeGreaterThan(Integer value) {
            addCriterion("open_jd_credit_type >", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_jd_credit_type >=", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeLessThan(Integer value) {
            addCriterion("open_jd_credit_type <", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeLessThanOrEqualTo(Integer value) {
            addCriterion("open_jd_credit_type <=", value, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeIn(List<Integer> values) {
            addCriterion("open_jd_credit_type in", values, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeNotIn(List<Integer> values) {
            addCriterion("open_jd_credit_type not in", values, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeBetween(Integer value1, Integer value2) {
            addCriterion("open_jd_credit_type between", value1, value2, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andOpenJdCreditTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("open_jd_credit_type not between", value1, value2, "openJdCreditType");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoIsNull() {
            addCriterion("jd_credit_first_order_no is null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoIsNotNull() {
            addCriterion("jd_credit_first_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoEqualTo(String value) {
            addCriterion("jd_credit_first_order_no =", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoNotEqualTo(String value) {
            addCriterion("jd_credit_first_order_no <>", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoGreaterThan(String value) {
            addCriterion("jd_credit_first_order_no >", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_no >=", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoLessThan(String value) {
            addCriterion("jd_credit_first_order_no <", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoLessThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_no <=", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoLike(String value) {
            addCriterion("jd_credit_first_order_no like", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoNotLike(String value) {
            addCriterion("jd_credit_first_order_no not like", value, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoIn(List<String> values) {
            addCriterion("jd_credit_first_order_no in", values, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoNotIn(List<String> values) {
            addCriterion("jd_credit_first_order_no not in", values, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_no between", value1, value2, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderNoNotBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_no not between", value1, value2, "jdCreditFirstOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinIsNull() {
            addCriterion("jd_credit_first_order_pin is null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinIsNotNull() {
            addCriterion("jd_credit_first_order_pin is not null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinEqualTo(String value) {
            addCriterion("jd_credit_first_order_pin =", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinNotEqualTo(String value) {
            addCriterion("jd_credit_first_order_pin <>", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinGreaterThan(String value) {
            addCriterion("jd_credit_first_order_pin >", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinGreaterThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_pin >=", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinLessThan(String value) {
            addCriterion("jd_credit_first_order_pin <", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinLessThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_pin <=", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinLike(String value) {
            addCriterion("jd_credit_first_order_pin like", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinNotLike(String value) {
            addCriterion("jd_credit_first_order_pin not like", value, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinIn(List<String> values) {
            addCriterion("jd_credit_first_order_pin in", values, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinNotIn(List<String> values) {
            addCriterion("jd_credit_first_order_pin not in", values, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_pin between", value1, value2, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderPinNotBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_pin not between", value1, value2, "jdCreditFirstOrderPin");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeIsNull() {
            addCriterion("jd_credit_first_order_time is null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeIsNotNull() {
            addCriterion("jd_credit_first_order_time is not null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeEqualTo(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time =", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeNotEqualTo(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time <>", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeGreaterThan(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time >", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time >=", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeLessThan(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time <", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("jd_credit_first_order_time <=", value, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeIn(List<LocalDateTime> values) {
            addCriterion("jd_credit_first_order_time in", values, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeNotIn(List<LocalDateTime> values) {
            addCriterion("jd_credit_first_order_time not in", values, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("jd_credit_first_order_time between", value1, value2, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("jd_credit_first_order_time not between", value1, value2, "jdCreditFirstOrderTime");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlIsNull() {
            addCriterion("jd_credit_first_order_url is null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlIsNotNull() {
            addCriterion("jd_credit_first_order_url is not null");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlEqualTo(String value) {
            addCriterion("jd_credit_first_order_url =", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlNotEqualTo(String value) {
            addCriterion("jd_credit_first_order_url <>", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlGreaterThan(String value) {
            addCriterion("jd_credit_first_order_url >", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlGreaterThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_url >=", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlLessThan(String value) {
            addCriterion("jd_credit_first_order_url <", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlLessThanOrEqualTo(String value) {
            addCriterion("jd_credit_first_order_url <=", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlLike(String value) {
            addCriterion("jd_credit_first_order_url like", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlNotLike(String value) {
            addCriterion("jd_credit_first_order_url not like", value, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlIn(List<String> values) {
            addCriterion("jd_credit_first_order_url in", values, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlNotIn(List<String> values) {
            addCriterion("jd_credit_first_order_url not in", values, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_url between", value1, value2, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andJdCreditFirstOrderUrlNotBetween(String value1, String value2) {
            addCriterion("jd_credit_first_order_url not between", value1, value2, "jdCreditFirstOrderUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoIsNull() {
            addCriterion("bank_and_order_no is null");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoIsNotNull() {
            addCriterion("bank_and_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoEqualTo(String value) {
            addCriterion("bank_and_order_no =", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoNotEqualTo(String value) {
            addCriterion("bank_and_order_no <>", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoGreaterThan(String value) {
            addCriterion("bank_and_order_no >", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_and_order_no >=", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoLessThan(String value) {
            addCriterion("bank_and_order_no <", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoLessThanOrEqualTo(String value) {
            addCriterion("bank_and_order_no <=", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoLike(String value) {
            addCriterion("bank_and_order_no like", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoNotLike(String value) {
            addCriterion("bank_and_order_no not like", value, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoIn(List<String> values) {
            addCriterion("bank_and_order_no in", values, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoNotIn(List<String> values) {
            addCriterion("bank_and_order_no not in", values, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoBetween(String value1, String value2) {
            addCriterion("bank_and_order_no between", value1, value2, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndOrderNoNotBetween(String value1, String value2) {
            addCriterion("bank_and_order_no not between", value1, value2, "bankAndOrderNo");
            return (Criteria) this;
        }

        public Criteria andBankAndPinIsNull() {
            addCriterion("bank_and_pin is null");
            return (Criteria) this;
        }

        public Criteria andBankAndPinIsNotNull() {
            addCriterion("bank_and_pin is not null");
            return (Criteria) this;
        }

        public Criteria andBankAndPinEqualTo(String value) {
            addCriterion("bank_and_pin =", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinNotEqualTo(String value) {
            addCriterion("bank_and_pin <>", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinGreaterThan(String value) {
            addCriterion("bank_and_pin >", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinGreaterThanOrEqualTo(String value) {
            addCriterion("bank_and_pin >=", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinLessThan(String value) {
            addCriterion("bank_and_pin <", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinLessThanOrEqualTo(String value) {
            addCriterion("bank_and_pin <=", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinLike(String value) {
            addCriterion("bank_and_pin like", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinNotLike(String value) {
            addCriterion("bank_and_pin not like", value, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinIn(List<String> values) {
            addCriterion("bank_and_pin in", values, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinNotIn(List<String> values) {
            addCriterion("bank_and_pin not in", values, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinBetween(String value1, String value2) {
            addCriterion("bank_and_pin between", value1, value2, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndPinNotBetween(String value1, String value2) {
            addCriterion("bank_and_pin not between", value1, value2, "bankAndPin");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyIsNull() {
            addCriterion("bank_and_income_money is null");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyIsNotNull() {
            addCriterion("bank_and_income_money is not null");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyEqualTo(String value) {
            addCriterion("bank_and_income_money =", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyNotEqualTo(String value) {
            addCriterion("bank_and_income_money <>", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyGreaterThan(String value) {
            addCriterion("bank_and_income_money >", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("bank_and_income_money >=", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyLessThan(String value) {
            addCriterion("bank_and_income_money <", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyLessThanOrEqualTo(String value) {
            addCriterion("bank_and_income_money <=", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyLike(String value) {
            addCriterion("bank_and_income_money like", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyNotLike(String value) {
            addCriterion("bank_and_income_money not like", value, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyIn(List<String> values) {
            addCriterion("bank_and_income_money in", values, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyNotIn(List<String> values) {
            addCriterion("bank_and_income_money not in", values, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyBetween(String value1, String value2) {
            addCriterion("bank_and_income_money between", value1, value2, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeMoneyNotBetween(String value1, String value2) {
            addCriterion("bank_and_income_money not between", value1, value2, "bankAndIncomeMoney");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeIsNull() {
            addCriterion("bank_and_income_time is null");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeIsNotNull() {
            addCriterion("bank_and_income_time is not null");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeEqualTo(LocalDateTime value) {
            addCriterion("bank_and_income_time =", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeNotEqualTo(LocalDateTime value) {
            addCriterion("bank_and_income_time <>", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeGreaterThan(LocalDateTime value) {
            addCriterion("bank_and_income_time >", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("bank_and_income_time >=", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeLessThan(LocalDateTime value) {
            addCriterion("bank_and_income_time <", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("bank_and_income_time <=", value, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeIn(List<LocalDateTime> values) {
            addCriterion("bank_and_income_time in", values, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeNotIn(List<LocalDateTime> values) {
            addCriterion("bank_and_income_time not in", values, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("bank_and_income_time between", value1, value2, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndIncomeTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("bank_and_income_time not between", value1, value2, "bankAndIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlIsNull() {
            addCriterion("bank_and_promote_url is null");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlIsNotNull() {
            addCriterion("bank_and_promote_url is not null");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlEqualTo(String value) {
            addCriterion("bank_and_promote_url =", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlNotEqualTo(String value) {
            addCriterion("bank_and_promote_url <>", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlGreaterThan(String value) {
            addCriterion("bank_and_promote_url >", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bank_and_promote_url >=", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlLessThan(String value) {
            addCriterion("bank_and_promote_url <", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlLessThanOrEqualTo(String value) {
            addCriterion("bank_and_promote_url <=", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlLike(String value) {
            addCriterion("bank_and_promote_url like", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlNotLike(String value) {
            addCriterion("bank_and_promote_url not like", value, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlIn(List<String> values) {
            addCriterion("bank_and_promote_url in", values, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlNotIn(List<String> values) {
            addCriterion("bank_and_promote_url not in", values, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlBetween(String value1, String value2) {
            addCriterion("bank_and_promote_url between", value1, value2, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andBankAndPromoteUrlNotBetween(String value1, String value2) {
            addCriterion("bank_and_promote_url not between", value1, value2, "bankAndPromoteUrl");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceIsNull() {
            addCriterion("joined_first_order_with_jd_credit_time_difference is null");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceIsNotNull() {
            addCriterion("joined_first_order_with_jd_credit_time_difference is not null");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceEqualTo(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference =", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceNotEqualTo(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference <>", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceGreaterThan(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference >", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference >=", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceLessThan(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference <", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceLessThanOrEqualTo(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference <=", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceLike(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference like", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceNotLike(String value) {
            addCriterion("joined_first_order_with_jd_credit_time_difference not like", value, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceIn(List<String> values) {
            addCriterion("joined_first_order_with_jd_credit_time_difference in", values, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceNotIn(List<String> values) {
            addCriterion("joined_first_order_with_jd_credit_time_difference not in", values, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceBetween(String value1, String value2) {
            addCriterion("joined_first_order_with_jd_credit_time_difference between", value1, value2, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithJdCreditTimeDifferenceNotBetween(String value1, String value2) {
            addCriterion("joined_first_order_with_jd_credit_time_difference not between", value1, value2, "joinedFirstOrderWithJdCreditTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceIsNull() {
            addCriterion("joined_first_order_with_bank_income_time_difference is null");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceIsNotNull() {
            addCriterion("joined_first_order_with_bank_income_time_difference is not null");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceEqualTo(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference =", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceNotEqualTo(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference <>", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceGreaterThan(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference >", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference >=", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceLessThan(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference <", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceLessThanOrEqualTo(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference <=", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceLike(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference like", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceNotLike(String value) {
            addCriterion("joined_first_order_with_bank_income_time_difference not like", value, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceIn(List<String> values) {
            addCriterion("joined_first_order_with_bank_income_time_difference in", values, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceNotIn(List<String> values) {
            addCriterion("joined_first_order_with_bank_income_time_difference not in", values, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceBetween(String value1, String value2) {
            addCriterion("joined_first_order_with_bank_income_time_difference between", value1, value2, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJoinedFirstOrderWithBankIncomeTimeDifferenceNotBetween(String value1, String value2) {
            addCriterion("joined_first_order_with_bank_income_time_difference not between", value1, value2, "joinedFirstOrderWithBankIncomeTimeDifference");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayIsNull() {
            addCriterion("jd_credit_open_one_day is null");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayIsNotNull() {
            addCriterion("jd_credit_open_one_day is not null");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayEqualTo(Integer value) {
            addCriterion("jd_credit_open_one_day =", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayNotEqualTo(Integer value) {
            addCriterion("jd_credit_open_one_day <>", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayGreaterThan(Integer value) {
            addCriterion("jd_credit_open_one_day >", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("jd_credit_open_one_day >=", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayLessThan(Integer value) {
            addCriterion("jd_credit_open_one_day <", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayLessThanOrEqualTo(Integer value) {
            addCriterion("jd_credit_open_one_day <=", value, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayIn(List<Integer> values) {
            addCriterion("jd_credit_open_one_day in", values, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayNotIn(List<Integer> values) {
            addCriterion("jd_credit_open_one_day not in", values, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayBetween(Integer value1, Integer value2) {
            addCriterion("jd_credit_open_one_day between", value1, value2, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andJdCreditOpenOneDayNotBetween(Integer value1, Integer value2) {
            addCriterion("jd_credit_open_one_day not between", value1, value2, "jdCreditOpenOneDay");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeIsNull() {
            addCriterion("newbie_package_oper_time is null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeIsNotNull() {
            addCriterion("newbie_package_oper_time is not null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeEqualTo(LocalDateTime value) {
            addCriterion("newbie_package_oper_time =", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeNotEqualTo(LocalDateTime value) {
            addCriterion("newbie_package_oper_time <>", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeGreaterThan(LocalDateTime value) {
            addCriterion("newbie_package_oper_time >", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("newbie_package_oper_time >=", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeLessThan(LocalDateTime value) {
            addCriterion("newbie_package_oper_time <", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("newbie_package_oper_time <=", value, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeIn(List<LocalDateTime> values) {
            addCriterion("newbie_package_oper_time in", values, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeNotIn(List<LocalDateTime> values) {
            addCriterion("newbie_package_oper_time not in", values, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("newbie_package_oper_time between", value1, value2, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageOperTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("newbie_package_oper_time not between", value1, value2, "newbiePackageOperTime");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinIsNull() {
            addCriterion("newbie_package_pin is null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinIsNotNull() {
            addCriterion("newbie_package_pin is not null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinEqualTo(String value) {
            addCriterion("newbie_package_pin =", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinNotEqualTo(String value) {
            addCriterion("newbie_package_pin <>", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinGreaterThan(String value) {
            addCriterion("newbie_package_pin >", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinGreaterThanOrEqualTo(String value) {
            addCriterion("newbie_package_pin >=", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinLessThan(String value) {
            addCriterion("newbie_package_pin <", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinLessThanOrEqualTo(String value) {
            addCriterion("newbie_package_pin <=", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinLike(String value) {
            addCriterion("newbie_package_pin like", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinNotLike(String value) {
            addCriterion("newbie_package_pin not like", value, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinIn(List<String> values) {
            addCriterion("newbie_package_pin in", values, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinNotIn(List<String> values) {
            addCriterion("newbie_package_pin not in", values, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinBetween(String value1, String value2) {
            addCriterion("newbie_package_pin between", value1, value2, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackagePinNotBetween(String value1, String value2) {
            addCriterion("newbie_package_pin not between", value1, value2, "newbiePackagePin");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdIsNull() {
            addCriterion("newbie_package_url_id is null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdIsNotNull() {
            addCriterion("newbie_package_url_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdEqualTo(String value) {
            addCriterion("newbie_package_url_id =", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdNotEqualTo(String value) {
            addCriterion("newbie_package_url_id <>", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdGreaterThan(String value) {
            addCriterion("newbie_package_url_id >", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdGreaterThanOrEqualTo(String value) {
            addCriterion("newbie_package_url_id >=", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdLessThan(String value) {
            addCriterion("newbie_package_url_id <", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdLessThanOrEqualTo(String value) {
            addCriterion("newbie_package_url_id <=", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdLike(String value) {
            addCriterion("newbie_package_url_id like", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdNotLike(String value) {
            addCriterion("newbie_package_url_id not like", value, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdIn(List<String> values) {
            addCriterion("newbie_package_url_id in", values, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdNotIn(List<String> values) {
            addCriterion("newbie_package_url_id not in", values, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdBetween(String value1, String value2) {
            addCriterion("newbie_package_url_id between", value1, value2, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageUrlIdNotBetween(String value1, String value2) {
            addCriterion("newbie_package_url_id not between", value1, value2, "newbiePackageUrlId");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultIsNull() {
            addCriterion("newbie_package_result is null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultIsNotNull() {
            addCriterion("newbie_package_result is not null");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultEqualTo(Integer value) {
            addCriterion("newbie_package_result =", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultNotEqualTo(Integer value) {
            addCriterion("newbie_package_result <>", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultGreaterThan(Integer value) {
            addCriterion("newbie_package_result >", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("newbie_package_result >=", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultLessThan(Integer value) {
            addCriterion("newbie_package_result <", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultLessThanOrEqualTo(Integer value) {
            addCriterion("newbie_package_result <=", value, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultIn(List<Integer> values) {
            addCriterion("newbie_package_result in", values, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultNotIn(List<Integer> values) {
            addCriterion("newbie_package_result not in", values, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultBetween(Integer value1, Integer value2) {
            addCriterion("newbie_package_result between", value1, value2, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andNewbiePackageResultNotBetween(Integer value1, Integer value2) {
            addCriterion("newbie_package_result not between", value1, value2, "newbiePackageResult");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoIsNull() {
            addCriterion("jd_gold_order_no is null");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoIsNotNull() {
            addCriterion("jd_gold_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoEqualTo(String value) {
            addCriterion("jd_gold_order_no =", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoNotEqualTo(String value) {
            addCriterion("jd_gold_order_no <>", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoGreaterThan(String value) {
            addCriterion("jd_gold_order_no >", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("jd_gold_order_no >=", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoLessThan(String value) {
            addCriterion("jd_gold_order_no <", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoLessThanOrEqualTo(String value) {
            addCriterion("jd_gold_order_no <=", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoLike(String value) {
            addCriterion("jd_gold_order_no like", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoNotLike(String value) {
            addCriterion("jd_gold_order_no not like", value, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoIn(List<String> values) {
            addCriterion("jd_gold_order_no in", values, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoNotIn(List<String> values) {
            addCriterion("jd_gold_order_no not in", values, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoBetween(String value1, String value2) {
            addCriterion("jd_gold_order_no between", value1, value2, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldOrderNoNotBetween(String value1, String value2) {
            addCriterion("jd_gold_order_no not between", value1, value2, "jdGoldOrderNo");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstIsNull() {
            addCriterion("jd_gold_is_first is null");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstIsNotNull() {
            addCriterion("jd_gold_is_first is not null");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstEqualTo(Integer value) {
            addCriterion("jd_gold_is_first =", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstNotEqualTo(Integer value) {
            addCriterion("jd_gold_is_first <>", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstGreaterThan(Integer value) {
            addCriterion("jd_gold_is_first >", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("jd_gold_is_first >=", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstLessThan(Integer value) {
            addCriterion("jd_gold_is_first <", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstLessThanOrEqualTo(Integer value) {
            addCriterion("jd_gold_is_first <=", value, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstIn(List<Integer> values) {
            addCriterion("jd_gold_is_first in", values, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstNotIn(List<Integer> values) {
            addCriterion("jd_gold_is_first not in", values, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstBetween(Integer value1, Integer value2) {
            addCriterion("jd_gold_is_first between", value1, value2, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldIsFirstNotBetween(Integer value1, Integer value2) {
            addCriterion("jd_gold_is_first not between", value1, value2, "jdGoldIsFirst");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeIsNull() {
            addCriterion("jd_gold_confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeIsNotNull() {
            addCriterion("jd_gold_confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeEqualTo(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time =", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeNotEqualTo(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time <>", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeGreaterThan(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time >", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time >=", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeLessThan(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time <", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("jd_gold_confirm_time <=", value, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeIn(List<LocalDateTime> values) {
            addCriterion("jd_gold_confirm_time in", values, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeNotIn(List<LocalDateTime> values) {
            addCriterion("jd_gold_confirm_time not in", values, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("jd_gold_confirm_time between", value1, value2, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdGoldConfirmTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("jd_gold_confirm_time not between", value1, value2, "jdGoldConfirmTime");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdIsNull() {
            addCriterion("jd_new_hand_reward_id is null");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdIsNotNull() {
            addCriterion("jd_new_hand_reward_id is not null");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdEqualTo(String value) {
            addCriterion("jd_new_hand_reward_id =", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdNotEqualTo(String value) {
            addCriterion("jd_new_hand_reward_id <>", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdGreaterThan(String value) {
            addCriterion("jd_new_hand_reward_id >", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdGreaterThanOrEqualTo(String value) {
            addCriterion("jd_new_hand_reward_id >=", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdLessThan(String value) {
            addCriterion("jd_new_hand_reward_id <", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdLessThanOrEqualTo(String value) {
            addCriterion("jd_new_hand_reward_id <=", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdLike(String value) {
            addCriterion("jd_new_hand_reward_id like", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdNotLike(String value) {
            addCriterion("jd_new_hand_reward_id not like", value, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdIn(List<String> values) {
            addCriterion("jd_new_hand_reward_id in", values, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdNotIn(List<String> values) {
            addCriterion("jd_new_hand_reward_id not in", values, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdBetween(String value1, String value2) {
            addCriterion("jd_new_hand_reward_id between", value1, value2, "jdNewHandRewardId");
            return (Criteria) this;
        }

        public Criteria andJdNewHandRewardIdNotBetween(String value1, String value2) {
            addCriterion("jd_new_hand_reward_id not between", value1, value2, "jdNewHandRewardId");
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

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
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

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIsNull() {
            addCriterion("salesman_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIsNotNull() {
            addCriterion("salesman_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdEqualTo(String value) {
            addCriterion("salesman_id =", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotEqualTo(String value) {
            addCriterion("salesman_id <>", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdGreaterThan(String value) {
            addCriterion("salesman_id >", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdGreaterThanOrEqualTo(String value) {
            addCriterion("salesman_id >=", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdLessThan(String value) {
            addCriterion("salesman_id <", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdLessThanOrEqualTo(String value) {
            addCriterion("salesman_id <=", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdLike(String value) {
            addCriterion("salesman_id like", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotLike(String value) {
            addCriterion("salesman_id not like", value, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdIn(List<String> values) {
            addCriterion("salesman_id in", values, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotIn(List<String> values) {
            addCriterion("salesman_id not in", values, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdBetween(String value1, String value2) {
            addCriterion("salesman_id between", value1, value2, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andSalesmanIdNotBetween(String value1, String value2) {
            addCriterion("salesman_id not between", value1, value2, "salesmanId");
            return (Criteria) this;
        }

        public Criteria andRewardIsNull() {
            addCriterion("reward is null");
            return (Criteria) this;
        }

        public Criteria andRewardIsNotNull() {
            addCriterion("reward is not null");
            return (Criteria) this;
        }

        public Criteria andRewardEqualTo(BigDecimal value) {
            addCriterion("reward =", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotEqualTo(BigDecimal value) {
            addCriterion("reward <>", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThan(BigDecimal value) {
            addCriterion("reward >", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reward >=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThan(BigDecimal value) {
            addCriterion("reward <", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reward <=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardIn(List<BigDecimal> values) {
            addCriterion("reward in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotIn(List<BigDecimal> values) {
            addCriterion("reward not in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reward between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reward not between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andChannelBelowIsNull() {
            addCriterion("channel_below is null");
            return (Criteria) this;
        }

        public Criteria andChannelBelowIsNotNull() {
            addCriterion("channel_below is not null");
            return (Criteria) this;
        }

        public Criteria andChannelBelowEqualTo(String value) {
            addCriterion("channel_below =", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowNotEqualTo(String value) {
            addCriterion("channel_below <>", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowGreaterThan(String value) {
            addCriterion("channel_below >", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowGreaterThanOrEqualTo(String value) {
            addCriterion("channel_below >=", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowLessThan(String value) {
            addCriterion("channel_below <", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowLessThanOrEqualTo(String value) {
            addCriterion("channel_below <=", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowLike(String value) {
            addCriterion("channel_below like", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowNotLike(String value) {
            addCriterion("channel_below not like", value, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowIn(List<String> values) {
            addCriterion("channel_below in", values, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowNotIn(List<String> values) {
            addCriterion("channel_below not in", values, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowBetween(String value1, String value2) {
            addCriterion("channel_below between", value1, value2, "channelBelow");
            return (Criteria) this;
        }

        public Criteria andChannelBelowNotBetween(String value1, String value2) {
            addCriterion("channel_below not between", value1, value2, "channelBelow");
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