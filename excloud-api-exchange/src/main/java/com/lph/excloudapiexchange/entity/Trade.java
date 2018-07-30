package com.lph.excloudapiexchange.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 交易记录表
 * </p>
 *
 * @author lph
 * @since 2018-07-25
 */
@TableName("ex_trade")
public class Trade extends Model<Trade> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 成交价格
     */
    private BigDecimal price;

    /**
     * 币种（例如：BTC）
     */
    private String token;

    /**
     * 成交时间
     */
    private Date tradeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Trade{" +
        "id=" + id +
        ", price=" + price +
        ", token=" + token +
        ", tradeTime=" + tradeTime +
        "}";
    }
}
