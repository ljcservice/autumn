package com.hitzd.his.Beans;

/**
 * his�� ordadm.Vital_Signs_Rec�� ���� 
 * @author Administrator
 *
 */
public class TPatVitalSigns extends TBaseBean
{
    private String patid ;
    private String visitid ;
    /* ���� */
    private String pulse;
    private String pulseValue;
    /* ���� */
    private String temperature;
    private String temperatureValue;
    /* ��Ѫѹ */
    private String bloodLow;
    private String bloodLowValue;
    /* ��Ѫѹ */
    private String bloodhigh;
    private String bloodhighValue;
    /* ���� */
    private String SVDate;
    
    public String getPatid()
    {
        return patid;
    }
    public void setPatid(String patid)
    {
        this.patid = patid;
    }
    public String getVisitid()
    {
        return visitid;
    }
    public void setVisitid(String visitid)
    {
        this.visitid = visitid;
    }
    public String getPulse()
    {
        return pulse;
    }
    public void setPulse(String pulse)
    {
        this.pulse = pulse;
    }
    public String getTemperature()
    {
        return temperature;
    }
    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }
    public String getBloodLow()
    {
        return bloodLow;
    }
    public void setBloodLow(String bloodLow)
    {
        this.bloodLow = bloodLow;
    }
    public String getBloodhigh()
    {
        return bloodhigh;
    }
    public void setBloodhigh(String bloodhigh)
    {
        this.bloodhigh = bloodhigh;
    }
    public String getSVDate()
    {
        return SVDate;
    }
    public void setSVDate(String sVDate)
    {
        SVDate = sVDate;
    }
    public String getPulseValue()
    {
        return pulseValue;
    }
    public void setPulseValue(String pulseValue)
    {
        this.pulseValue = pulseValue;
    }
    public String getTemperatureValue()
    {
        return temperatureValue;
    }
    public void setTemperatureValue(String temperatureValue)
    {
        this.temperatureValue = temperatureValue;
    }
    public String getBloodLowValue()
    {
        return bloodLowValue;
    }
    public void setBloodLowValue(String bloodLowValue)
    {
        this.bloodLowValue = bloodLowValue;
    }
    public String getBloodhighValue()
    {
        return bloodhighValue;
    }
    public void setBloodhighValue(String bloodhighValue)
    {
        this.bloodhighValue = bloodhighValue;
    }    
}
