package com.autuan.project.promote.task.domain;

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
public class TabTask {
    private String id;

    private String name;

    private String prefix;

    private String paramId;

    private BigDecimal reward;

    private LocalDateTime rewardBeginTime;

    private LocalDateTime rewardStopTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

    private String tags;

    private String articleId;

    private Integer priority;

    private String articleTitle;

    private String image;

    private String bgImg;

    private String summary;

    private String codePrefix;

    private Integer codePrefixDigit;

    private Integer assignType;

    private String indexName;

    private Integer isHidden;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        name("name", "name", "VARCHAR", false),
        prefix("prefix", "prefix", "VARCHAR", false),
        paramId("param_id", "paramId", "VARCHAR", false),
        reward("reward", "reward", "DECIMAL", false),
        rewardBeginTime("reward_begin_time", "rewardBeginTime", "TIMESTAMP", false),
        rewardStopTime("reward_stop_time", "rewardStopTime", "TIMESTAMP", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        tags("tags", "tags", "VARCHAR", false),
        articleId("article_id", "articleId", "VARCHAR", false),
        priority("priority", "priority", "INTEGER", false),
        articleTitle("article_title", "articleTitle", "VARCHAR", false),
        image("image", "image", "VARCHAR", false),
        bgImg("bg_img", "bgImg", "VARCHAR", false),
        summary("summary", "summary", "VARCHAR", false),
        codePrefix("code_prefix", "codePrefix", "VARCHAR", false),
        codePrefixDigit("code_prefix_digit", "codePrefixDigit", "INTEGER", false),
        assignType("assign_type", "assignType", "INTEGER", false),
        indexName("index_name", "indexName", "VARCHAR", false),
        isHidden("is_hidden", "isHidden", "INTEGER", false);

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