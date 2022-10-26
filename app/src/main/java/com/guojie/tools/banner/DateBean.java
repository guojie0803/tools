package com.guojie.tools.banner;

public class DateBean {

    public String titleBig;
    public String titleSmall;

    public DateBean(String titleBig,String titleSmall){
        this.titleBig=titleBig;
        this.titleSmall=titleSmall;
    }

    public void setTitleBig(String titleBig) {
        this.titleBig = titleBig;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public String getTitleBig() {
        return titleBig;
    }

    public String getTitleSmall() {
        return titleSmall;
    }
}
