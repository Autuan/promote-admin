package com.autuan.project.promote.dataBank.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabDataBank {
    private String id;

    private LocalDateTime applyDate;

    private LocalDateTime verifyDate;

    private String applyId;

    private Integer approveStatus;

    private String bankName;

    private String cMobile;

    private String cName;

    private String channelCode;

    private Integer customFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

    private String taskId;

    private String salesmanId;

    private BigDecimal reward;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        applyDate("apply_date", "applyDate", "TIMESTAMP", false),
        verifyDate("verify_date", "verifyDate", "TIMESTAMP", false),
        applyId("apply_id", "applyId", "VARCHAR", false),
        approveStatus("approve_status", "approveStatus", "INTEGER", false),
        bankName("bank_name", "bankName", "VARCHAR", false),
        cMobile("c_mobile", "cMobile", "VARCHAR", false),
        cName("c_name", "cName", "VARCHAR", false),
        channelCode("channel_code", "channelCode", "VARCHAR", false),
        customFlag("custom_flag", "customFlag", "INTEGER", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        taskId("task_id", "taskId", "VARCHAR", false),
        salesmanId("salesman_id", "salesmanId", "VARCHAR", false),
        reward("reward", "reward", "DECIMAL", false);

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