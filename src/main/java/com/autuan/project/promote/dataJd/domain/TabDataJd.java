package com.autuan.project.promote.dataJd.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabDataJd {
    private String id;

    private LocalDateTime recordTime;

    private String taskInnerId;

    private String taskUrl;

    private String orderNo;

    private String orderName;

    private String channelFirst;

    private String channelBelowSource;

    private Integer checkStatus;

    private String checkReason;

    private String joinJdPin;

    private String joinLink;

    private LocalDateTime joinTime;

    private String joinOrder;
    private String openJdCreditPin;

    private String openJdCreditUrl;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime openJdCreditTime;

    private Integer openJdCreditType;

    private String jdCreditFirstOrderNo;

    private String jdCreditFirstOrderPin;

    private LocalDateTime jdCreditFirstOrderTime;

    private String jdCreditFirstOrderUrl;

    private String bankAndOrderNo;

    private String bankAndPin;

    private String bankAndIncomeMoney;

    private LocalDateTime bankAndIncomeTime;

    private String bankAndPromoteUrl;

    private String joinedFirstOrderWithJdCreditTimeDifference;

    private String joinedFirstOrderWithBankIncomeTimeDifference;

    private Integer jdCreditOpenOneDay;

    private LocalDateTime newbiePackageOperTime;

    private String newbiePackagePin;

    private String newbiePackageUrlId;

    private Integer newbiePackageResult;

    private String jdGoldOrderNo;

    private Integer jdGoldIsFirst;

    private LocalDateTime jdGoldConfirmTime;

    private String jdNewHandRewardId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

    private String taskId;

    private String salesmanId;

    private BigDecimal reward;

    private String channelBelow;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        recordTime("record_time", "recordTime", "TIMESTAMP", false),
        taskInnerId("task_inner_id", "taskInnerId", "VARCHAR", false),
        taskUrl("task_url", "taskUrl", "VARCHAR", false),
        orderNo("order_no", "orderNo", "VARCHAR", false),
        orderName("order_name", "orderName", "VARCHAR", false),
        channelFirst("channel_first", "channelFirst", "VARCHAR", false),
        channelBelowSource("channel_below_source", "channelBelowSource", "VARCHAR", false),
        checkStatus("check_status", "checkStatus", "INTEGER", false),
        checkReason("check_reason", "checkReason", "VARCHAR", false),
        joinJdPin("join_jd_pin", "joinJdPin", "VARCHAR", false),
        joinLink("join_link", "joinLink", "VARCHAR", false),
        joinTime("join_time", "joinTime", "TIMESTAMP", false),
        joinOrder("join_order", "joinOrder", "VARCHAR", false),
        openJdCreditPin("open_jd_credit_pin", "openJdCreditPin", "VARCHAR", false),
        openJdCreditUrl("open_jd_credit_url", "openJdCreditUrl", "VARCHAR", false),
        openJdCreditTime("open_jd_credit_time", "openJdCreditTime", "TIMESTAMP", false),
        openJdCreditType("open_jd_credit_type", "openJdCreditType", "INTEGER", false),
        jdCreditFirstOrderNo("jd_credit_first_order_no", "jdCreditFirstOrderNo", "VARCHAR", false),
        jdCreditFirstOrderPin("jd_credit_first_order_pin", "jdCreditFirstOrderPin", "VARCHAR", false),
        jdCreditFirstOrderTime("jd_credit_first_order_time", "jdCreditFirstOrderTime", "TIMESTAMP", false),
        jdCreditFirstOrderUrl("jd_credit_first_order_url", "jdCreditFirstOrderUrl", "VARCHAR", false),
        bankAndOrderNo("bank_and_order_no", "bankAndOrderNo", "VARCHAR", false),
        bankAndPin("bank_and_pin", "bankAndPin", "VARCHAR", false),
        bankAndIncomeMoney("bank_and_income_money", "bankAndIncomeMoney", "VARCHAR", false),
        bankAndIncomeTime("bank_and_income_time", "bankAndIncomeTime", "TIMESTAMP", false),
        bankAndPromoteUrl("bank_and_promote_url", "bankAndPromoteUrl", "VARCHAR", false),
        joinedFirstOrderWithJdCreditTimeDifference("joined_first_order_with_jd_credit_time_difference", "joinedFirstOrderWithJdCreditTimeDifference", "VARCHAR", false),
        joinedFirstOrderWithBankIncomeTimeDifference("joined_first_order_with_bank_income_time_difference", "joinedFirstOrderWithBankIncomeTimeDifference", "VARCHAR", false),
        jdCreditOpenOneDay("jd_credit_open_one_day", "jdCreditOpenOneDay", "INTEGER", false),
        newbiePackageOperTime("newbie_package_oper_time", "newbiePackageOperTime", "TIMESTAMP", false),
        newbiePackagePin("newbie_package_pin", "newbiePackagePin", "VARCHAR", false),
        newbiePackageUrlId("newbie_package_url_id", "newbiePackageUrlId", "VARCHAR", false),
        newbiePackageResult("newbie_package_result", "newbiePackageResult", "INTEGER", false),
        jdGoldOrderNo("jd_gold_order_no", "jdGoldOrderNo", "VARCHAR", false),
        jdGoldIsFirst("jd_gold_is_first", "jdGoldIsFirst", "INTEGER", false),
        jdGoldConfirmTime("jd_gold_confirm_time", "jdGoldConfirmTime", "TIMESTAMP", false),
        jdNewHandRewardId("jd_new_hand_reward_id", "jdNewHandRewardId", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        taskId("task_id", "taskId", "VARCHAR", false),
        salesmanId("salesman_id", "salesmanId", "VARCHAR", false),
        reward("reward", "reward", "DECIMAL", false),
        channelBelow("channel_below", "channelBelow", "VARCHAR", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}