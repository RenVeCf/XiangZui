package com.ipd.xiangzui.bean;

import java.util.List;

public class SendOrderDataBean {

    private OneOrderBean oneOrderBean;
    private List<TwoOrderBean> twoOrderBean;

    public OneOrderBean getOneOrderBean() {
        return oneOrderBean;
    }

    public void setOneOrderBean(OneOrderBean oneOrderBean) {
        this.oneOrderBean = oneOrderBean;
    }

    public List<TwoOrderBean> getTwoOrderBean() {
        return twoOrderBean;
    }

    public void setTwoOrderBean(List<TwoOrderBean> twoOrderBean) {
        this.twoOrderBean = twoOrderBean;
    }

    public static class OneOrderBean {
        private int sendOrderType;
        private String surgicalName;
        private String hospitalName;
        private String surgicalAddress;
        private String surgicalTime;
        private String surgicalDuration;
        private String patientName;
        private String patientSex;
        private String patientAge;
        private String patientHeight;
        private String patientBodyWeight;
        private String narcosisId;
        private String positiveUrl;
        private String negativeUrl;
        private String insuranceConsentUrl;

    }

    public static class TwoOrderBean {

    }
}
