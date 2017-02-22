package com.hitzd.his.Utils;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * �������к�������صĴ���
 */
public class DateUtils extends Object {
    /** ϵͳ�ܵ�ʧЧ���� */
    public static final String DATE_FOREVER = "9999-12-31";
    /** ʱ���ʽ */
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    

    /** ȫʱ���ʽ */
    private static final String FORMAT_FULLTIME = "yyMMddHHmmssSSS";
    /** ���ڸ�ʽ */
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    /** ���ڸ�ʽ */
    private static final String FORMAT_YEARMONTH = "yyyy-MM";
    /** ��ʱ���ʽ */
    private static final String FORMAT_TIME = "HH:mm:ss";
    /**����ʱ���ʽ*/
    private static final String FORMAT_DATETIMEZD = "yyyy-MM-dd HH:00:00";
    /**����ʱ���ʽ*/
    private static final String NULL_DATE = "1000-01-01 00:00:00";

    /**
     * �õ���ǰ������ʱ���ַ���
     * @return ����ʱ���ַ���
     */
    public static String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        return getStringFromDate(calendar.getTime(), FORMAT_DATETIME);
    }


    /**
     * �õ���ǰ��ȫʱ���ַ�������������
     * @return ����ʱ���ַ���
     */
    public static String getFulltime() {
        Calendar calendar = Calendar.getInstance();
        return getStringFromDate(calendar.getTime(), FORMAT_FULLTIME);
    }
    
    /**
     * �õ���ǰ������ʱ���ַ���
     * @return ����ʱ���ַ���
     */
    public static String getDatetimeW3C() {
        return getDate() + "T" + getTime();
    }
    
    /**
     * �õ���ǰ������ʱ���ַ���
     * @return ����ʱ���ַ���
     */
    public static String getDatetimeZd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return getStringFromDate(calendar.getTime(), FORMAT_DATETIME);
    }

    /**
     * �õ���ǰ�����������ַ���
     * @return ���������ַ���
     */
    public static String getYearMonth() {
        Calendar calendar = Calendar.getInstance();
        return getStringFromDate(calendar.getTime(), FORMAT_YEARMONTH);
    }
    
    /**
     * �õ���ǰ�������ַ���
     * @return �����ַ���
     */
    public static String getDate() {
        return getDate(Calendar.getInstance());
    }

    /**
     * �õ�ָ�����ڵ��ַ���
     * @param calendar  ָ��������
     * @return �����ַ���
     */
    public static String getDate(Calendar calendar) {
        return getStringFromDate(calendar.getTime(), FORMAT_DATE);
    }
    
    /**
     * �õ���ǰ�Ĵ�ʱ���ַ���
     * @return ʱ���ַ���
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        return getStringFromDate(calendar.getTime(), FORMAT_TIME);
    }

    /**
     * �������ֵõ��������֡�
     * @param number    ����
     * @return  ��������
     */
    public static String getChineseNum(String number) {
        String chinese = "";
        int x = Integer.parseInt(number);
    
        switch (x) {
            case 0:
                chinese = "��";
                break;
            case 1:
                chinese = "һ";
                break;
            case 2:
                chinese = "��";
                break;
            case 3:
                chinese = "��";
                break;
            case 4:
                chinese = "��";
                break;
            case 5:
                chinese = "��";
                break;
            case 6:
                chinese = "��";
                break;
            case 7:
                chinese = "��";
                break;
            case 8:
                chinese = "��";
                break;
            case 9:
                chinese = "��";
                    break;
                default:
        }
        return chinese;
    }
    /**
     * �õ���ǰ���ڵ����������ַ���
     * @return  ���������ַ���
     */
    public static String getChineseDate() {
        return getChineseDate(getDate());
    }
    
    /**
     * ��������ֵ�õ����������ַ���
     * @param date  ����ֵ
     * @return  ���������ַ���
     */
    public static String getChineseDate(String date) {
        if (date.length() < 10) {
            return "";
        } else {
            String year = date.substring(0, 4); // ��
            String month = date.substring(5, 7); // ��
            String day = date.substring(8, 10); // ��
            String y1 = year.substring(0, 1); //�� �ַ�1
            String y2 = year.substring(1, 2); //�� �ַ�1
            String y3 = year.substring(2, 3); //�� �ַ�3
            String y4 = year.substring(3, 4); //�� �ַ�4
            String m2 = month.substring(1, 2); // �� �ַ�2
            String d1 = day.substring(0, 1); // �� 1
            String d2 = day.substring(1, 2); // �� 2
            String cy1 = getChineseNum(y1);
            String cy2 = getChineseNum(y2);
            String cy3 = getChineseNum(y3);
            String cy4 = getChineseNum(y4);
            String cm2 = getChineseNum(m2);
            String cd1 = getChineseNum(d1);
            String cd2 = getChineseNum(d2);
            String cYear = cy1 + cy2 + cy3 + cy4 + "��";
            String cMonth = "��";
    
            if (Integer.parseInt(month) > 9) {
                cMonth = "ʮ" + cm2 + cMonth;
            } else {
                cMonth = cm2 + cMonth;
            }
    
            String cDay = "��";
    
            if (Integer.parseInt(day) > 9) {
                cDay = cd1 + "ʮ" + cd2 + cDay;
            } else {
                cDay = cd2 + cDay;
            }
    
            String chineseday = cYear + cMonth + cDay;
            return chineseday;
        }
    }
    /**
     * ��������ֵ�õ����������ַ���
     * @param date ��������
     * @return 2005��09��23�ո�ʽ������
     */
    public static String getChineseTwoDate(String date) {
        if (date.length() < 10) {
            return "";
        } else {
            String year = date.substring(0, 4); // ��
            String month = date.substring(5, 7); // ��
            String day = date.substring(8, 10); // ��
        
            return year + "��" + month + "��" + day + "��";
        }
    }
    /**
     * �õ���ǰ���ڵ������� : ���� '����һ',  '���ڶ�'��
     * @return  ��ǰ���ڵ�������
     */
    public static String getChineseDayOfWeek() {
        return getChineseDayOfWeek(getDate());
    }

    /**
     * �õ�ָ�����ڵ�������
     * @param strDate ָ�������ַ���
     * @return  ָ�����ڵ�������
     */
    public static String getChineseDayOfWeek(String strDate) {
        Calendar calendar = getCalendar(strDate);
        
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String strWeek = "";

        switch (week) {
            case Calendar.SUNDAY:
                strWeek = "������";
                break;
            case Calendar.MONDAY:
                strWeek = "����һ";
                break;
            case Calendar.TUESDAY:
                strWeek = "���ڶ�";
                break;
            case Calendar.WEDNESDAY:
                strWeek = "������";
                break;
            case Calendar.THURSDAY:
                strWeek = "������";
                break;
            case Calendar.FRIDAY:
                strWeek = "������";
                break;
            case Calendar.SATURDAY:
                strWeek = "������";
                break;
            default:
                strWeek = "����һ";
                break;
        }

        return strWeek;
    }
    /**
     * compare two kinds String with format : 12:00 , 08:00; or 12:00:00, 08:00:00
     * @param firstTime     the first time string
     * @param secondTime    the second time string
     * @return  0 -- same
     *          1 -- first bigger than second
     *          -1 -- first smaller than second
     *          -2 -- invalid time format
     */
    public static int compareOnlyByTime(String firstTime, String secondTime) {
        try {
            String timeDelm = ":";

            //calculate the first time to integer
            int pos = firstTime.indexOf(timeDelm);
            int iFirst = Integer.parseInt(firstTime.substring(0, pos)) * 10000;
            firstTime = firstTime.substring(pos + 1);
            pos = firstTime.indexOf(timeDelm);

            if (pos > 0) {
                iFirst = iFirst + (Integer.parseInt(firstTime.substring(0, pos)) * 100)
                    + Integer.parseInt(firstTime.substring(pos + 1));
            } else {
                iFirst = iFirst + (Integer.parseInt(firstTime) * 100);
            }

            //calculate the second time string to integer
            pos = secondTime.indexOf(timeDelm);
            int iSecond = Integer.parseInt(secondTime.substring(0, pos)) * 10000;
            secondTime = secondTime.substring(pos + 1);
            pos = secondTime.indexOf(timeDelm);

            if (pos > 0) {
                iSecond = iSecond + (Integer.parseInt(secondTime.substring(0, pos)) * 100)
                    + Integer.parseInt(secondTime.substring(pos + 1));
            } else {
                iSecond = iSecond + (Integer.parseInt(secondTime) * 100);
            }

            //compare two
            if (iFirst == iSecond) {
                return 0;
            } else if (iFirst > iSecond) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -2;
        }
    }

    /**
     * �õ��뵱ǰ�������ָ�������������ַ���
     * @param days ǰ�����������ֵΪ�� ��ֵΪǰ
     * @return �����ַ���
     */
    public static String getCertainDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return getStringFromDate(calendar.getTime(), FORMAT_DATE);
    }

    /**
     * �õ��뵱ǰ�������ָ�������������ַ���
     * @param dif ǰ�����������ֵΪ�� ��ֵΪǰ
     * @return �����ַ���
     */
    public static String getCertainMonth(int dif) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, dif);
        return getStringFromDate(calendar.getTime(), FORMAT_DATE);
    }
    
    /**
     * �õ���ָ���������ָ�������������ַ���
     * @param dateString ָ��������
     * @param days ǰ�����������ֵΪ�� ��ֵΪǰ
     * @return �����ַ���
     */
    public static String  getCertainDate(String dateString, int days) {
        Calendar calendar = getCalendar(dateString);
        calendar.add(Calendar.DATE, days);
        return getStringFromDate(calendar.getTime(), FORMAT_DATE);
    }

    /**
     * �õ���ָ���������ָ�������������ַ���
     * @param dateString ָ��������
     * @param period ǰ�����������ֵΪ�� ��ֵΪǰ
     * @param periodType ������� �������졢�¡���
     * @return �����ַ���
     */
    public static String  getCertainDate(String dateString, int period, int periodType) {
        Calendar calendar = getCalendar(dateString);
        
        switch (periodType) { 
            case 1: //��
                calendar.add(Calendar.DATE, period);
                break;
            case 2: //��
                calendar.add(Calendar.MONTH, period);
                break;
            case 3: //��
                calendar.add(Calendar.MONTH, period * 12);
                break;
            default:
        }
        return getStringFromDate(calendar.getTime(), FORMAT_DATE);
    }
    
    /**
     * ���ݹ涨��ʽ���ַ����õ�Calendar
     * @param dateString    ���ڴ�
     * @return  ��ӦCalendar
     */
    public static Calendar getCalendar(String dateString) {
        Calendar calendar = Calendar.getInstance();
        String[] items = dateString.split("-");
        calendar.set(Integer.parseInt(items[0]), Integer.parseInt(items[1]) - 1, Integer.parseInt(items[2]));
        return calendar;
    }
    
    /**
     * �õ���������һ������
     * @return  �����ַ���
     */
    public static String getFirstDateOfWeek() {
        return getFirstDateOfWeek(getDate());
    }

    /**
     * �õ�ָ�����ڵ�����һ������
     * @param dateString �����ַ���
     * @return  ��������һ������
     */
    public static String getFirstDateOfWeek(String dateString) {
        Calendar calendar = getCalendar(dateString);
        int iCount;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            iCount = -6;
        } else {
            iCount = Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK);
        }
         
        return getCertainDate(dateString, iCount);
    }
    
    /**
     * ��ָ����ʽ���ַ�����ʽ��Ϊ����
     * @param s �ַ�������
     * @return ����
     */
    public static Date getDateFromString(String s) {
        return getDateFromString(s, FORMAT_DATE);
    }
    
    /**
     * ��ָ����ʽ���ַ�����ʽ��Ϊ����
     * @param s �ַ�������
     * @param format    �ַ�����ʽ
     * @return ����
     */
    public static Date getDateFromString(String s, String format) {
        try {
        	if(s==null || s.equals("")){
        		s=NULL_DATE;
        	}
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(s);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * �����ڸ�ʽ��Ϊָ�����ַ���
     * @param d ����
     * @param format    ����ַ�����ʽ
     * @return �����ַ���
     */
    public static String getStringFromDate(Date d, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * �õ���ǰ�����
     * @return  ��ǰ���
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * �õ���ǰ���·�
     * @return  ��ǰ�·�
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * �õ���ǰ������
     * @return  ��ǰ����
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
        
    /**
     * ���õ������������������
     *
     * @param first     ��һ������
     * @param second    �ڶ�������      
     * @return          ��������
     */
    public static int selectDateDiff(String first, String second) {
        int dif = 0;
        try {
            Date fDate = getDateFromString(first, FORMAT_DATE);
            Date sDate = getDateFromString(second, FORMAT_DATE);
            dif = (int) ((fDate.getTime() - sDate.getTime()) / 86400000);
        } catch (Exception e) {
            dif = 0;
        }
        return dif;
    }
    
    /**
     * ��ǰ������������ݵ����ڵ��������
     *
     * @param dateinfo      ָ��������
     * @return              ��������
     */
    public static int selectDateDiff(String dateinfo) {
        return selectDateDiff(dateinfo, getDate());
    }

    /**
     * ĳ���ڼ��ϼ���õ�����һ������ 
     *
     * @param addNum      Ҫ���ӵ�����
     * @param getDate     ĳ����
     * @return            ��ĳ�������addNum�������
     */
    public static String getDateAdded(int addNum, String getDate) {
        return getCertainDate(getDate, addNum);
    }

    /**
     * ĳ���ڣ���ʱ�䣩���ϼ���õ�����һ������ ����ʱ�䣩
     *
     * @param datetime      ��Ҫ���������ڣ���ʱ�䣩
     * @param days          ��������
     * @return              ����������ڣ���ʱ�䣩
     */
    public static String getCertainDatetime(String datetime, int days) {
        Date curDate = getDateFromString(datetime, FORMAT_DATETIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DATE, days);
        return getStringFromDate(calendar.getTime(), FORMAT_DATETIME);
    }
    
    /**
     * �õ���ǰ���ڵ������µĵ�һ�������
     * @param date ��ǰ����
     * @return String ���ص�����
     * 
     */
    public static String getMonthFirstDay(String date) { 
        Calendar cal = getCalendar(date);
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        return year + "-" + month + "-01";
    }

    /**
     * �õ���ǰ���ڵ������µ����һ�������
     * @param date ��ǰ����
     * @return String ���ص�����
     * 
     */
    public static String getMonthLastDay(String date) {  
        Calendar cal = getCalendar(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int nextMonth = month + 1;
        int nextYear = year;
        if (nextMonth == 13) {
            nextMonth = 1;
            nextYear = nextYear + 1;
        }
        String nextMonthFirstDay = nextYear + "-" + nextMonth + "-01";
        return getCertainDate(nextMonthFirstDay, -1);
    }
    
    /**
     * ȡ�������ڼ���·ݲ���
     * @param startDate ��ʼ����
     * @param endDate   ��������
     * @return          �·ݲ���
     */
    public static int getMonthDiff(String startDate, String endDate) {
        String[] startArray = startDate.split("-");
        String[] endArray = endDate.split("-");
        int startYear = Integer.parseInt(startArray[0]);
        int startMonth = Integer.parseInt(startArray[1]);
        int endYear = Integer.parseInt(endArray[0]);
        int endMonth = Integer.parseInt(endArray[1]);
        return Math.abs((endYear - startYear) * 12 + endMonth - startMonth);
    }
    
    /**
     * �����ǰ�����������������գ��򷵻�����һ������
     * @param date ��ǰ����
     * @return String ����һ����
     */
    public static String getWorkDate(String date) {
        Date curDate = getDateFromString(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        
        if (week == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 2);
        } else if (week == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        return getDate(calendar);
        
    }
    
    /**
     * �÷������ڶ�����Ŀ�ʼ���������ڽ��г���У��
     * @param stime      request����
     * @param etime     ����bean
     * @throws Exception ��������ʱ
     * @throws Exception ��ʾ��Ϣ
     * @throws ParseException �����쳣����ʱ
     */
    public static  void seTimeVerify(String stime, String etime) {
        //ת�����������ͱ���
        Date starttime =  getDateFromString(stime, FORMAT_DATETIMEZD);
        Date endtime = getDateFromString(etime, FORMAT_DATETIMEZD);

        if (endtime.getTime() - starttime.getTime() < 0) {
            return;
        }
     }
    
    /**
     * ��������ʱ������Сʱ��
     * @param beginTime                  ��ʼʱ��
     * @param endTime                    ����ʱ��
     * @return                           ����ֵ
     * @throws Exception               ��������ϵͳ��
     * @throws Exception              ��������ҵ��
     */
    public static double getDiffHoure(String beginTime, String endTime) {
        //
        double dif = 0;
        try {
            Date eDatetime = getDateFromString(endTime, FORMAT_DATE);
            Date bDatetime = getDateFromString(beginTime, FORMAT_DATE);
            dif = (eDatetime.getTime() - bDatetime.getTime()) / 1000 / 3600;
        } catch (Exception e) {
            dif = 0;
        }
        return dif;
    }
    /**
     * ��������ʱ������Сʱ�� ������λС��
     * @param beginTime                  ��ʼʱ��
     * @param endTime                    ����ʱ��
     * @return                           ����ֵ
     * @throws Exception               ��������ϵͳ��
     * @throws Exception              ��������ҵ��
     */
    public static double getDiffHoureFormatDateTime(String beginTime, String endTime) {
        //
        double dif = 0;
        try {
            Date eDatetime = getDateFromString(endTime, FORMAT_DATETIME);
            Date bDatetime = getDateFromString(beginTime, FORMAT_DATETIME);
            dif = (eDatetime.getTime() - bDatetime.getTime()) / 1000 / 3600.00;
            DecimalFormat df=new DecimalFormat("#.00"); 
            return Double.parseDouble(df.format(dif));
        } catch (Exception e) {
           return 0;
        }
    }
    /**
     * ���ؽ��������뿪ʼ����֮����������
     * @param beginDate ��ʼ����
     * @param endDate ��������
     * @return �������
     */
    
    public static long getDiffDay(String beginDate, String endDate) {
      
        
            Date eDate = getDateFromString(endDate, FORMAT_DATE);
            Date bDate = getDateFromString(beginDate, FORMAT_DATE);
            
            
            return (eDate.getTime()-bDate.getTime())/(24*60*60*1000);
       
    }
    /**
     * ����ĳ�µ�����
     * @param year ��
     * @param month ��
     * @return ����
     */
    
    public static int getMonthDays(String year, String month) {
    	
    	String beginDate=year;
    	if(Integer.valueOf(month)<10){
    		beginDate=beginDate+"-0"+month+"-01";
    	}else{
    		beginDate=beginDate+"-"+month+"-01";
    	}
    	String endDate =getMonthLastDay(beginDate);   
        return (int) getDiffDay(beginDate, endDate);
       
    }
    /**
     * ����ʱ����������ڸ�ʽ
     * @param mills ʱ������ 
     */
    public static String getFormattedDuration(long mills)
    {
        long days = mills / 86400000;
        mills = mills - (days * 86400000);
        long hours = mills / 3600000;
        mills = mills - (hours * 3600000);
        long mins = mills / 60000;
        mills = mills - (mins * 60000);
        long secs = mills / 1000;
        mills = mills - (secs * 1000);

        StringBuffer bf = new StringBuffer(60);
        bf.append(days).append("��");
        bf.append(hours).append("Сʱ");
        bf.append(mins).append("����");
        bf.append(secs).append("��");
        return bf.toString();
    }
    public static boolean compareTo(String t1, String t2)
    {
      int i = 0;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try
      {
        Date time1 = sdf.parse(t1);
        Date time2 = sdf.parse(t2);
        i = time1.compareTo(time2);
      }
      catch (Exception e)
      {
        i = 0;
      }

      return i < 0;
    }

    public static String formatDate(Date basicDate, String strFormat)
    {
      SimpleDateFormat df = new SimpleDateFormat(strFormat);
      return df.format(basicDate);
    }

    public static String formatDate(String strFormat)
    {
      return formatDate(new Date(), strFormat);
    }

    public static String formatDate(String basicDate, String strFormat)
    {
      SimpleDateFormat df = new SimpleDateFormat(strFormat);
      Date tmpDate = null;
      try
      {
        tmpDate = df.parse(basicDate);
      }
      catch (Exception e)
      {
        tmpDate = new Date();
      }
      return df.format(tmpDate);
    }

    public static int daysBetweenTwoDate(Date dt1, Date dt2)
    {
      int days = hoursBetweenTwoDate(dt1, dt2) / 24;
      return days;
    }

    public static int daysBetweenTwoDate(String dt1, String dt2)
    {
      int days = hoursBetweenTwoDate(dt1, dt2) / 24;
      return days;
    }

    public static int hoursBetweenTwoDate(Date dt1, Date dt2)
    {
      int hours = minutesBetweenTwoDate(dt1, dt2) / 60;
      return hours;
    }

    public static int hoursBetweenTwoDate(String dt1, String dt2)
    {
      int hours = minutesBetweenTwoDate(dt1, dt2) / 60;
      return hours;
    }

    public static int minutesBetweenTwoDate(Date dt1, Date dt2)
    {
      int minutes = secondsBetweenTwoDate(dt1, dt2) / 60;
      return minutes;
    }

    public static int minutesBetweenTwoDate(String dt1, String dt2)
    {
      int minutes = secondsBetweenTwoDate(dt1, dt2) / 60;
      return minutes;
    }

    public static int secondsBetweenTwoDate(Date dt1, Date dt2)
    {
      int secondsBetweenTwoDate = (int)((dt2.getTime() - dt1.getTime()) / 1000L);
      return secondsBetweenTwoDate;
    }

    public static int secondsBetweenTwoDate(String dt1, String dt2)
    {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date firstDate = null;
      Date secondDate = null;
      try
      {
        firstDate = df.parse(dt1);
        secondDate = df.parse(dt2);
        return secondsBetweenTwoDate(firstDate, secondDate);
      }
      catch (Exception e)
      {
      }
      return 0;
    }

    public static long getLongTime()
    {
      return System.currentTimeMillis();
    }

    public static String dateToStr(Date date)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sdf.format(date);
    }

    public static Date strToDate(String str)
    {
      DateFormat df = DateFormat.getDateInstance();
      Date date = null;
      try
      {
        date = df.parse(str);
      }
      catch (Exception e)
      {
        date = new Date();
      }
      return date;
    }

    public static String calDateTime(String date, int dateNum, int hourNum, int minuteNum, int secondNum)
    {
      String result = "";
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Calendar cal = Calendar.getInstance();
      try
      {
        cal.setTime(sd.parse(date));
        cal.add(5, dateNum);
        cal.add(10, hourNum);
        cal.add(12, minuteNum);
        cal.add(13, secondNum);
        result = sd.format(cal.getTime());
      }
      catch (Exception e)
      {
        result = date;
      }
      return result;
    }

    public static String calDateTime(int minuteNum)
    {
      return calDateTime(getLocalDate(), 0, 0, minuteNum, 0);
    }

    public static String calDate(String date, int yearNum, int monthNum, int dateNum)
    {
      String result = "";
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      try
      {
        cal.setTime(sd.parse(date));
        cal.add(2, monthNum);
        cal.add(1, yearNum);
        cal.add(5, dateNum);
        result = sd.format(cal.getTime());
      }
      catch (Exception e)
      {
        result = date;
      }
      return result;
    }

    public static String calDate(int yearNum, int monthNum, int dateNum)
    {
      return calDate(getLocalDate(), yearNum, monthNum, dateNum);
    }

    public static String calDate(int monthNum, int dateNum)
    {
      return calDate(getLocalDate(), 0, monthNum, dateNum);
    }

    public static String calDate(int dateNum)
    {
      return calDate(getLocalDate(), 0, 0, dateNum);
    }

    public static String getLocalDate()
    {
      return formatDate("yyyy-MM-dd HH:mm:ss");
    }

    public static String getSimpleDate()
    {
      return formatDate("yyyyMMddHHmmss");
    }

    public static String parseCnDate(String localDate)
    {
      String sYear = localDate.substring(0, 4);
      String sMonth = delFrontZero(localDate.substring(5, 7));
      String sDay = delFrontZero(localDate.substring(8, 10));
      return sYear + "��" + sMonth + "��" + sDay + "��";
    }

    public static String parsePointDate(String localDate)
    {
      String sMonth = delFrontZero(localDate.substring(5, 7));
      String sDay = delFrontZero(localDate.substring(8, 10));
      return sMonth + "." + sDay;
    }

    public static String delFrontZero(String mord)
    {
      int i = 0;
      try
      {
        i = Integer.parseInt(mord);
        return String.valueOf(i);
      }
      catch (Exception e) {
      }
      return mord;
    }
    
    /**
     * ���ָ�����ڵ�ǰһ���� 
     * @param aDate
     * @return
     */
	@SuppressWarnings("static-access")
	public static String getNowDateBeforeMonth(String aDate)
    {
		Calendar calendar =  null;
		if(aDate != null)
			calendar = getCalendar(aDate);
		else
			calendar = Calendar.getInstance();
    	calendar.add(calendar.MONTH, -1);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
    	return sdf.format(calendar.getTime());
    }
	
	/**
     * ���㻼����������
     * @param birthDate
     * @param someDate
     * @param strFormat
     * @return
     */
    public static String calcuateAgeByTwoDates(String birthDate,String someDate, String strFormat) 
    {
        if (null == birthDate || "".equals(birthDate)) 
        {
            return null;
        }
        String result = "";
        try 
        {
            if (null == someDate || "".equals(someDate)) 
            {
                result = calcuateAge(parseDate(birthDate, strFormat),new Date());
            } 
            else
            {
                result = calcuateAge(parseDate(birthDate, strFormat),parseDate(someDate, strFormat));
            }
        } 
        catch (Exception e)
        { 
            e.printStackTrace();
        }
        return result;
    }
    
    private static Date parseDate(String dateValue, String strFormat)
    {
        if (dateValue == null || "".endsWith(dateValue))
            return null;
        if (strFormat == null || "".equals(strFormat))
            strFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;
        try 
        {
            newDate = dateFormat.parse(dateValue);
        } 
        catch (ParseException pe) 
        {
            pe.printStackTrace();
            newDate = null;
        }
        return newDate;
    }
    
    /**
     * 
     * @param birthDay
     * @param someDay
     * @return
     * @throws Exception
     */
    private static String calcuateAge(Date birthDay, Date someDay)  throws Exception
    {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay))
        {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        cal.setTime(someDay);
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) 
        {
            if (monthNow == monthBirth) 
            {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                }
            } 
            else
            {
                // monthNow>monthBirth
                age--;
            }
        }
        return age + "";
    }
}
