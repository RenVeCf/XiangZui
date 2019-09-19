package com.ipd.xiangzui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SendOrderDataBean implements Parcelable {

    private int sendOrderType; //1：单台 2：连台
    private OneOrderBean oneOrderBean; //单台Bean
    private List<TwoOrderBean> twoOrderBean; //连台Bean

    public SendOrderDataBean() {
        super();
    }

    public SendOrderDataBean(Parcel in) {
        sendOrderType = in.readInt();
        oneOrderBean = in.readParcelable(OneOrderBean.class.getClassLoader());
        twoOrderBean = in.createTypedArrayList(TwoOrderBean.CREATOR);
    }

    public static final Creator<SendOrderDataBean> CREATOR = new Creator<SendOrderDataBean>() {
        @Override
        public SendOrderDataBean createFromParcel(Parcel in) {
            return new SendOrderDataBean(in);
        }

        @Override
        public SendOrderDataBean[] newArray(int size) {
            return new SendOrderDataBean[size];
        }
    };

    public int getSendOrderType() {
        return sendOrderType;
    }

    public void setSendOrderType(int sendOrderType) {
        this.sendOrderType = sendOrderType;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sendOrderType);
        parcel.writeParcelable(oneOrderBean, i);
        parcel.writeTypedList(twoOrderBean);
    }

    public static class OneOrderBean implements Parcelable {
        private String surgicalName; //拟手术名称
        private String hospitalName; //医院名字
        private String prov; //手术地址-省
        private String city; //手术地址-市
        private String dist; //手术地址-区
        private String address; //手术地址-详细地址
        private String surgicalTime; //拟手术开始时间
        private String surgicalDuration; //拟手术预计时长
        private String patientName; //患者名称
        private String patientSex; //患者性别
        private String patientAge; //患者年龄
        private String patientHeight; //患者身高
        private String patientBodyWeight; //患者体重
        private String narcosisId; //麻醉ID
        private String positiveUrl; //身份证正面
        private String negativeUrl; //身份证反面
        private String insuranceConsentUrl; //保险同意书
        private String medicalRecords; //病历上传方式 3：暂无 2：填写上传 1：图片上传
        private String surgeryRelated; //手术相关病历
        private String routineBlood; //血常规
        private String ecg; //心电图
        private String cruor; //凝血功能
        private String contagion; //传染病指标
        private String minBloodPressure; //血压 最小
        private String maxBloodPressure; //血压 最大
        private String pulse; //脉搏 （次）
        private String breathe; //呼吸 （次）
        private String animalHeat; //体温
        private String diabetes; //糖尿病： 1：无 2：有
        private String cerebralInfarction; //脑梗 1：无 2：有
        private String heartDisease; //心脏疾病 1：无 2：有
        private String infectDisease; //传染性疾病 1：无 2：有
        private String breatheFunction; //呼吸功能障碍 1：无 2：有
        private String urgent; //是否加急： 1：无 2：加急
        private String expectMoney; //手术金额
        private String ahNumber; //医院联系电话
        private String adNumber; //医生联系电话
        private String adMoney; //医生端显示预计费用
        private String ahMoney; //医院端显示预计费用
        private String premiumMoney; //加价费
        private String taxMoney; //个人所得税
        private String totalMoney; //合计金额

        public OneOrderBean() {
            super();
        }

        public OneOrderBean(Parcel in) {
            surgicalName = in.readString();
            hospitalName = in.readString();
            prov = in.readString();
            city = in.readString();
            dist = in.readString();
            address = in.readString();
            surgicalTime = in.readString();
            surgicalDuration = in.readString();
            patientName = in.readString();
            patientSex = in.readString();
            patientAge = in.readString();
            patientHeight = in.readString();
            patientBodyWeight = in.readString();
            narcosisId = in.readString();
            positiveUrl = in.readString();
            negativeUrl = in.readString();
            insuranceConsentUrl = in.readString();
            medicalRecords = in.readString();
            surgeryRelated = in.readString();
            routineBlood = in.readString();
            ecg = in.readString();
            cruor = in.readString();
            contagion = in.readString();
            minBloodPressure = in.readString();
            maxBloodPressure = in.readString();
            pulse = in.readString();
            breathe = in.readString();
            animalHeat = in.readString();
            diabetes = in.readString();
            cerebralInfarction = in.readString();
            heartDisease = in.readString();
            infectDisease = in.readString();
            breatheFunction = in.readString();
            urgent = in.readString();
            expectMoney = in.readString();
            ahNumber = in.readString();
            adNumber = in.readString();
            adMoney = in.readString();
            ahMoney = in.readString();
            premiumMoney = in.readString();
            taxMoney = in.readString();
            totalMoney = in.readString();
        }

        public static final Creator<OneOrderBean> CREATOR = new Creator<OneOrderBean>() {
            @Override
            public OneOrderBean createFromParcel(Parcel in) {
                return new OneOrderBean(in);
            }

            @Override
            public OneOrderBean[] newArray(int size) {
                return new OneOrderBean[size];
            }
        };

        public String getSurgicalName() {
            return surgicalName;
        }

        public void setSurgicalName(String surgicalName) {
            this.surgicalName = surgicalName;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSurgicalTime() {
            return surgicalTime;
        }

        public void setSurgicalTime(String surgicalTime) {
            this.surgicalTime = surgicalTime;
        }

        public String getSurgicalDuration() {
            return surgicalDuration;
        }

        public void setSurgicalDuration(String surgicalDuration) {
            this.surgicalDuration = surgicalDuration;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientSex() {
            return patientSex;
        }

        public void setPatientSex(String patientSex) {
            this.patientSex = patientSex;
        }

        public String getPatientAge() {
            return patientAge;
        }

        public void setPatientAge(String patientAge) {
            this.patientAge = patientAge;
        }

        public String getPatientHeight() {
            return patientHeight;
        }

        public void setPatientHeight(String patientHeight) {
            this.patientHeight = patientHeight;
        }

        public String getPatientBodyWeight() {
            return patientBodyWeight;
        }

        public void setPatientBodyWeight(String patientBodyWeight) {
            this.patientBodyWeight = patientBodyWeight;
        }

        public String getNarcosisId() {
            return narcosisId;
        }

        public void setNarcosisId(String narcosisId) {
            this.narcosisId = narcosisId;
        }

        public String getPositiveUrl() {
            return positiveUrl;
        }

        public void setPositiveUrl(String positiveUrl) {
            this.positiveUrl = positiveUrl;
        }

        public String getNegativeUrl() {
            return negativeUrl;
        }

        public void setNegativeUrl(String negativeUrl) {
            this.negativeUrl = negativeUrl;
        }

        public String getInsuranceConsentUrl() {
            return insuranceConsentUrl;
        }

        public void setInsuranceConsentUrl(String insuranceConsentUrl) {
            this.insuranceConsentUrl = insuranceConsentUrl;
        }

        public String getMedicalRecords() {
            return medicalRecords;
        }

        public void setMedicalRecords(String medicalRecords) {
            this.medicalRecords = medicalRecords;
        }

        public String getSurgeryRelated() {
            return surgeryRelated;
        }

        public void setSurgeryRelated(String surgeryRelated) {
            this.surgeryRelated = surgeryRelated;
        }

        public String getRoutineBlood() {
            return routineBlood;
        }

        public void setRoutineBlood(String routineBlood) {
            this.routineBlood = routineBlood;
        }

        public String getEcg() {
            return ecg;
        }

        public void setEcg(String ecg) {
            this.ecg = ecg;
        }

        public String getCruor() {
            return cruor;
        }

        public void setCruor(String cruor) {
            this.cruor = cruor;
        }

        public String getContagion() {
            return contagion;
        }

        public void setContagion(String contagion) {
            this.contagion = contagion;
        }

        public String getMinBloodPressure() {
            return minBloodPressure;
        }

        public void setMinBloodPressure(String minBloodPressure) {
            this.minBloodPressure = minBloodPressure;
        }

        public String getMaxBloodPressure() {
            return maxBloodPressure;
        }

        public void setMaxBloodPressure(String maxBloodPressure) {
            this.maxBloodPressure = maxBloodPressure;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getBreathe() {
            return breathe;
        }

        public void setBreathe(String breathe) {
            this.breathe = breathe;
        }

        public String getAnimalHeat() {
            return animalHeat;
        }

        public void setAnimalHeat(String animalHeat) {
            this.animalHeat = animalHeat;
        }

        public String getDiabetes() {
            return diabetes;
        }

        public void setDiabetes(String diabetes) {
            this.diabetes = diabetes;
        }

        public String getCerebralInfarction() {
            return cerebralInfarction;
        }

        public void setCerebralInfarction(String cerebralInfarction) {
            this.cerebralInfarction = cerebralInfarction;
        }

        public String getHeartDisease() {
            return heartDisease;
        }

        public void setHeartDisease(String heartDisease) {
            this.heartDisease = heartDisease;
        }

        public String getInfectDisease() {
            return infectDisease;
        }

        public void setInfectDisease(String infectDisease) {
            this.infectDisease = infectDisease;
        }

        public String getBreatheFunction() {
            return breatheFunction;
        }

        public void setBreatheFunction(String breatheFunction) {
            this.breatheFunction = breatheFunction;
        }

        public String getUrgent() {
            return urgent;
        }

        public void setUrgent(String urgent) {
            this.urgent = urgent;
        }

        public String getExpectMoney() {
            return expectMoney;
        }

        public void setExpectMoney(String expectMoney) {
            this.expectMoney = expectMoney;
        }

        public String getAhNumber() {
            return ahNumber;
        }

        public void setAhNumber(String ahNumber) {
            this.ahNumber = ahNumber;
        }

        public String getAdNumber() {
            return adNumber;
        }

        public void setAdNumber(String adNumber) {
            this.adNumber = adNumber;
        }

        public String getAdMoney() {
            return adMoney;
        }

        public void setAdMoney(String adMoney) {
            this.adMoney = adMoney;
        }

        public String getAhMoney() {
            return ahMoney;
        }

        public void setAhMoney(String ahMoney) {
            this.ahMoney = ahMoney;
        }

        public String getPremiumMoney() {
            return premiumMoney;
        }

        public void setPremiumMoney(String premiumMoney) {
            this.premiumMoney = premiumMoney;
        }

        public String getTaxMoney() {
            return taxMoney;
        }

        public void setTaxMoney(String taxMoney) {
            this.taxMoney = taxMoney;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(surgicalName);
            parcel.writeString(hospitalName);
            parcel.writeString(prov);
            parcel.writeString(city);
            parcel.writeString(dist);
            parcel.writeString(address);
            parcel.writeString(surgicalTime);
            parcel.writeString(surgicalDuration);
            parcel.writeString(patientName);
            parcel.writeString(patientSex);
            parcel.writeString(patientAge);
            parcel.writeString(patientHeight);
            parcel.writeString(patientBodyWeight);
            parcel.writeString(narcosisId);
            parcel.writeString(positiveUrl);
            parcel.writeString(negativeUrl);
            parcel.writeString(insuranceConsentUrl);
            parcel.writeString(medicalRecords);
            parcel.writeString(surgeryRelated);
            parcel.writeString(routineBlood);
            parcel.writeString(ecg);
            parcel.writeString(cruor);
            parcel.writeString(contagion);
            parcel.writeString(minBloodPressure);
            parcel.writeString(maxBloodPressure);
            parcel.writeString(pulse);
            parcel.writeString(breathe);
            parcel.writeString(animalHeat);
            parcel.writeString(diabetes);
            parcel.writeString(cerebralInfarction);
            parcel.writeString(heartDisease);
            parcel.writeString(infectDisease);
            parcel.writeString(breatheFunction);
            parcel.writeString(urgent);
            parcel.writeString(expectMoney);
            parcel.writeString(ahNumber);
            parcel.writeString(adNumber);
            parcel.writeString(adMoney);
            parcel.writeString(ahMoney);
            parcel.writeString(premiumMoney);
            parcel.writeString(taxMoney);
            parcel.writeString(totalMoney);
        }
    }

    public static class TwoOrderBean implements Parcelable {
        private String hospitalName; //医院名字
        private String prov; //手术地址-省
        private String city; //手术地址-市
        private String dist; //手术地址-区
        private String address; //手术地址-详细地址
        private String surgicalTime; //拟手术开始时间
        private String surgicalDuration; //拟手术预计时长
        private String surgicalName; //拟手术名称
        private String patientName; //患者名称
        private String patientSex; //患者性别
        private String patientAge; //患者年龄
        private String patientHeight; //患者身高
        private String patientBodyWeight; //患者体重
        private String narcosisId; //麻醉ID
        private String positiveUrl; //身份证正面
        private String negativeUrl; //身份证反面
        private String insuranceConsentUrl; //保险同意书
        private String medicalRecords; //病历上传方式 3：暂无 2：填写上传 1：图片上传
        private String surgeryRelated; //手术相关病历
        private String routineBlood; //血常规
        private String ecg; //心电图
        private String cruor; //凝血功能
        private String contagion; //传染病指标
        private String minBloodPressure; //血压 最小
        private String maxBloodPressure; //血压 最大
        private String pulse; //脉搏 （次）
        private String breathe; //呼吸 （次）
        private String animalHeat; //体温
        private String diabetes; //糖尿病： 1：无 2：有
        private String cerebralInfarction; //脑梗 1：无 2：有
        private String heartDisease; //心脏疾病 1：无 2：有
        private String infectDisease; //传染性疾病 1：无 2：有
        private String breatheFunction; //呼吸功能障碍 1：无 2：有
        private String urgent; //是否加急： 1：无 2：加急
        private String expectMoney; //手术金额
        private String ahNumber; //医院联系电话
        private String adNumber; //医生联系电话
        private String adMoney; //医生端显示预计费用
        private String ahMoney; //医院端显示预计费用
        private String premiumMoney; //加价费
        private String taxMoney; //个人所得税
        private String totalMoney; //合计金额

        public TwoOrderBean() {
            super();
        }

        public TwoOrderBean(Parcel in) {
            hospitalName = in.readString();
            prov = in.readString();
            city = in.readString();
            dist = in.readString();
            address = in.readString();
            surgicalTime = in.readString();
            surgicalDuration = in.readString();
            surgicalName = in.readString();
            patientName = in.readString();
            patientSex = in.readString();
            patientAge = in.readString();
            patientHeight = in.readString();
            patientBodyWeight = in.readString();
            narcosisId = in.readString();
            positiveUrl = in.readString();
            negativeUrl = in.readString();
            insuranceConsentUrl = in.readString();
            medicalRecords = in.readString();
            surgeryRelated = in.readString();
            routineBlood = in.readString();
            ecg = in.readString();
            cruor = in.readString();
            contagion = in.readString();
            minBloodPressure = in.readString();
            maxBloodPressure = in.readString();
            pulse = in.readString();
            breathe = in.readString();
            animalHeat = in.readString();
            diabetes = in.readString();
            cerebralInfarction = in.readString();
            heartDisease = in.readString();
            infectDisease = in.readString();
            breatheFunction = in.readString();
            urgent = in.readString();
            expectMoney = in.readString();
            ahNumber = in.readString();
            adNumber = in.readString();
            adMoney = in.readString();
            ahMoney = in.readString();
            premiumMoney = in.readString();
            taxMoney = in.readString();
            totalMoney = in.readString();
        }

        public static final Creator<TwoOrderBean> CREATOR = new Creator<TwoOrderBean>() {
            @Override
            public TwoOrderBean createFromParcel(Parcel in) {
                return new TwoOrderBean(in);
            }

            @Override
            public TwoOrderBean[] newArray(int size) {
                return new TwoOrderBean[size];
            }
        };

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSurgicalTime() {
            return surgicalTime;
        }

        public void setSurgicalTime(String surgicalTime) {
            this.surgicalTime = surgicalTime;
        }

        public String getSurgicalDuration() {
            return surgicalDuration;
        }

        public void setSurgicalDuration(String surgicalDuration) {
            this.surgicalDuration = surgicalDuration;
        }

        public String getSurgicalName() {
            return surgicalName;
        }

        public void setSurgicalName(String surgicalName) {
            this.surgicalName = surgicalName;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientSex() {
            return patientSex;
        }

        public void setPatientSex(String patientSex) {
            this.patientSex = patientSex;
        }

        public String getPatientAge() {
            return patientAge;
        }

        public void setPatientAge(String patientAge) {
            this.patientAge = patientAge;
        }

        public String getPatientHeight() {
            return patientHeight;
        }

        public void setPatientHeight(String patientHeight) {
            this.patientHeight = patientHeight;
        }

        public String getPatientBodyWeight() {
            return patientBodyWeight;
        }

        public void setPatientBodyWeight(String patientBodyWeight) {
            this.patientBodyWeight = patientBodyWeight;
        }

        public String getNarcosisId() {
            return narcosisId;
        }

        public void setNarcosisId(String narcosisId) {
            this.narcosisId = narcosisId;
        }

        public String getPositiveUrl() {
            return positiveUrl;
        }

        public void setPositiveUrl(String positiveUrl) {
            this.positiveUrl = positiveUrl;
        }

        public String getNegativeUrl() {
            return negativeUrl;
        }

        public void setNegativeUrl(String negativeUrl) {
            this.negativeUrl = negativeUrl;
        }

        public String getInsuranceConsentUrl() {
            return insuranceConsentUrl;
        }

        public void setInsuranceConsentUrl(String insuranceConsentUrl) {
            this.insuranceConsentUrl = insuranceConsentUrl;
        }

        public String getMedicalRecords() {
            return medicalRecords;
        }

        public void setMedicalRecords(String medicalRecords) {
            this.medicalRecords = medicalRecords;
        }

        public String getSurgeryRelated() {
            return surgeryRelated;
        }

        public void setSurgeryRelated(String surgeryRelated) {
            this.surgeryRelated = surgeryRelated;
        }

        public String getRoutineBlood() {
            return routineBlood;
        }

        public void setRoutineBlood(String routineBlood) {
            this.routineBlood = routineBlood;
        }

        public String getEcg() {
            return ecg;
        }

        public void setEcg(String ecg) {
            this.ecg = ecg;
        }

        public String getCruor() {
            return cruor;
        }

        public void setCruor(String cruor) {
            this.cruor = cruor;
        }

        public String getContagion() {
            return contagion;
        }

        public void setContagion(String contagion) {
            this.contagion = contagion;
        }

        public String getMinBloodPressure() {
            return minBloodPressure;
        }

        public void setMinBloodPressure(String minBloodPressure) {
            this.minBloodPressure = minBloodPressure;
        }

        public String getMaxBloodPressure() {
            return maxBloodPressure;
        }

        public void setMaxBloodPressure(String maxBloodPressure) {
            this.maxBloodPressure = maxBloodPressure;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getBreathe() {
            return breathe;
        }

        public void setBreathe(String breathe) {
            this.breathe = breathe;
        }

        public String getAnimalHeat() {
            return animalHeat;
        }

        public void setAnimalHeat(String animalHeat) {
            this.animalHeat = animalHeat;
        }

        public String getDiabetes() {
            return diabetes;
        }

        public void setDiabetes(String diabetes) {
            this.diabetes = diabetes;
        }

        public String getCerebralInfarction() {
            return cerebralInfarction;
        }

        public void setCerebralInfarction(String cerebralInfarction) {
            this.cerebralInfarction = cerebralInfarction;
        }

        public String getHeartDisease() {
            return heartDisease;
        }

        public void setHeartDisease(String heartDisease) {
            this.heartDisease = heartDisease;
        }

        public String getInfectDisease() {
            return infectDisease;
        }

        public void setInfectDisease(String infectDisease) {
            this.infectDisease = infectDisease;
        }

        public String getBreatheFunction() {
            return breatheFunction;
        }

        public void setBreatheFunction(String breatheFunction) {
            this.breatheFunction = breatheFunction;
        }

        public String getUrgent() {
            return urgent;
        }

        public void setUrgent(String urgent) {
            this.urgent = urgent;
        }

        public String getExpectMoney() {
            return expectMoney;
        }

        public void setExpectMoney(String expectMoney) {
            this.expectMoney = expectMoney;
        }

        public String getAhNumber() {
            return ahNumber;
        }

        public void setAhNumber(String ahNumber) {
            this.ahNumber = ahNumber;
        }

        public String getAdNumber() {
            return adNumber;
        }

        public void setAdNumber(String adNumber) {
            this.adNumber = adNumber;
        }

        public String getAdMoney() {
            return adMoney;
        }

        public void setAdMoney(String adMoney) {
            this.adMoney = adMoney;
        }

        public String getAhMoney() {
            return ahMoney;
        }

        public void setAhMoney(String ahMoney) {
            this.ahMoney = ahMoney;
        }

        public String getPremiumMoney() {
            return premiumMoney;
        }

        public void setPremiumMoney(String premiumMoney) {
            this.premiumMoney = premiumMoney;
        }

        public String getTaxMoney() {
            return taxMoney;
        }

        public void setTaxMoney(String taxMoney) {
            this.taxMoney = taxMoney;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(hospitalName);
            parcel.writeString(prov);
            parcel.writeString(city);
            parcel.writeString(dist);
            parcel.writeString(address);
            parcel.writeString(surgicalTime);
            parcel.writeString(surgicalDuration);
            parcel.writeString(surgicalName);
            parcel.writeString(patientName);
            parcel.writeString(patientSex);
            parcel.writeString(patientAge);
            parcel.writeString(patientHeight);
            parcel.writeString(patientBodyWeight);
            parcel.writeString(narcosisId);
            parcel.writeString(positiveUrl);
            parcel.writeString(negativeUrl);
            parcel.writeString(insuranceConsentUrl);
            parcel.writeString(medicalRecords);
            parcel.writeString(surgeryRelated);
            parcel.writeString(routineBlood);
            parcel.writeString(ecg);
            parcel.writeString(cruor);
            parcel.writeString(contagion);
            parcel.writeString(minBloodPressure);
            parcel.writeString(maxBloodPressure);
            parcel.writeString(pulse);
            parcel.writeString(breathe);
            parcel.writeString(animalHeat);
            parcel.writeString(diabetes);
            parcel.writeString(cerebralInfarction);
            parcel.writeString(heartDisease);
            parcel.writeString(infectDisease);
            parcel.writeString(breatheFunction);
            parcel.writeString(urgent);
            parcel.writeString(expectMoney);
            parcel.writeString(ahNumber);
            parcel.writeString(adNumber);
            parcel.writeString(adMoney);
            parcel.writeString(ahMoney);
            parcel.writeString(premiumMoney);
            parcel.writeString(taxMoney);
            parcel.writeString(totalMoney);
        }
    }
}
