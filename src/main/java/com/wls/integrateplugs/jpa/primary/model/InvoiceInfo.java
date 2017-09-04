package com.wls.integrateplugs.jpa.primary.model;

/**
 * Created by wls on 2017/8/5.
 */
public class InvoiceInfo {
    private String invoiceTitle;
    private Integer invoiceType;

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }
}
