package com.autuan.project.promote.salesman.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabSalesman {
    private String id;

    private String brokerageBankNo;

    private String brokerageBankName;

    private String brokerageBankAddress;

    private String name;

    private String mobile;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

    private String password;

    private String identifyNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;

    private String level;

    private String groupId;

    private String education;

    private Integer gender;

    private String headImg;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        brokerageBankNo("brokerage_bank_no", "brokerageBankNo", "VARCHAR", false),
        brokerageBankName("brokerage_bank_name", "brokerageBankName", "VARCHAR", false),
        brokerageBankAddress("brokerage_bank_address", "brokerageBankAddress", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        mobile("mobile", "mobile", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        password("password", "password", "VARCHAR", false),
        identifyNumber("identify_number", "identifyNumber", "VARCHAR", false),
        applyTime("apply_time", "applyTime", "TIMESTAMP", false),
        level("level", "level", "VARCHAR", false),
        groupId("group_id", "groupId", "VARCHAR", false),
        education("education", "education", "VARCHAR", false),
        gender("gender", "gender", "INTEGER", false),
        headImg("head_img", "headImg", "VARCHAR", false);

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