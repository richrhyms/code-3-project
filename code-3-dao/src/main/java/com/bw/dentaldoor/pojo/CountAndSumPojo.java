package com.bw.dentaldoor.pojo;

import java.math.BigDecimal;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class CountAndSumPojo {

    private Long count;

    private BigDecimal sum;

    public CountAndSumPojo(Long count, BigDecimal sum) {
        this.count = count;
        this.sum = sum;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
