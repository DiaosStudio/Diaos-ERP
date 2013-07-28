package com.diaos_erp.startup;

public class StartUpItem
{
    private String itemCode;

    private String itemName;

    private int idx;

    private String cls;

    private boolean isApp;

    private boolean ignoreErr;

    private String method;

    private String url;

    private String remark;

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public int getIdx()
    {
        return idx;
    }

    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    public boolean isApp()
    {
        return isApp;
    }

    public void setApp(boolean isApp)
    {
        this.isApp = isApp;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCls()
    {
        return cls;
    }

    public void setCls(String cls)
    {
        this.cls = cls;
    }

    public boolean isIgnoreErr()
    {
        return ignoreErr;
    }

    public void setIgnoreErr(boolean ignoreErr)
    {
        this.ignoreErr = ignoreErr;
    }

}
