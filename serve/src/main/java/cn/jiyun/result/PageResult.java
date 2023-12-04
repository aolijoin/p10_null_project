package cn.jiyun.result;

import java.io.Serializable;

import java.util.List;

public class PageResult<T> implements Serializable {
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }
}