package com.hitzd.his.casehistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import org.apache.commons.beanutils.PropertyUtils;

public class BeanHelper {

	// ���Ը������ų��������������
	private static String[] exceptString = { "class", "value",
			"servletWrapper", "servlet", "multipartRequestHandler" };
	/**
	 * ������ͬ�������ԣ�֧�����������Զ�ת�� String to Integer ʹ�÷��� StrToInteger(String s) Integer
	 * to String ʹ�÷��� ObjectToString(Object src) String to Long ʹ�÷���
	 * StrToLong(String s) Long to String ʹ�÷��� ObjectToString(Object src) String
	 * to Double ʹ�÷��� StrToDouble(String s) Double to String ʹ�÷���
	 * ObjectToString(Object src) Sting to Date ʹ�÷��� StringtoDate(String date)
	 * Ĭ��ת����ʽyyyy-MM-dd������ͨ��setDateformat(String dateformat)��ʽ����ת����ʽ
	 * Util.datefmt;Ĭ�ϸ�ʽ Util.datetimefmt;����ʱ���ʽ Date to String ʹ�÷���
	 * DatetoString(Date dt) ת����ʽͬ��
	 * 
	 * @param src
	 *            Դ����
	 * @param dest
	 *            Ŀ����� 1.PropertyDescriptor[] ���� Java Bean ͨ��һ�Դ洢������������һ������
	 *            2.pdsrc[i].getPropertyType() ��ȡ java Bean ����������
	 *            3.pddest[j].getName() ��ȡ java Bean ����������
	 *            4.pdsrc[i].getPropertyType().getName() ��ȡ java Bean ��������������
	 *            5.IllegalAccessException�쳣
	 *            ��Ӧ�ó�����ͼ����һ��ʵ�������������飩�����û��ȡһ���ֶΣ����ߵ���һ������
	 *            ������ǰ����ִ�еķ����޷�����ָ���ࡢ�ֶΡ��������췽���Ķ���ʱ���׳� IllegalAccessException��
	 *            6.InvocationTargetException�쳣 ��һ�ְ�װ�ɵ��÷������췽�����׳��쳣�ľ��������쳣��
	 *            7.NoSuchMethodException�쳣 �޷��ҵ�ĳһ�ض�����ʱ���׳����쳣
	 *            8.PropertyUtils.getPropertyDescriptors(����)
	 *            ����������Է��س�PropertyDescriptor[]����
	 *            9.PropertyUtils.setProperty(���󣬶����������ƣ���������ֵ)
	 *            //�������еĶ�������ֵ���������еĶ���������
	 *            10.PropertyUtils.getProperty(���󣬶�����������) //��ȡָ�������������ֵ
	 * 
	 */
	public void copySameProperties(Object src, Object dest) {
		/*
		boolean test = true;
		PropertyDescriptor[] pdsrc = PropertyUtils.getPropertyDescriptors(src);
		PropertyDescriptor[] pddest = PropertyUtils
				.getPropertyDescriptors(dest);
		// ��Դ��������ѭ��
		for (int i = 0; i < pdsrc.length; i++) {
			// ���ڸ���Ŀ���ڵ���������
			for (int es = 0; es < exceptString.length; es++) {
				// �����ǰ��������Ե��ڣ����ڸ���Ŀ���ڵġ�
				// testΪ�٣������и��Ʋ�����������ѭ������֮TESTΪ��
				if (pdsrc[i].getName().equals(exceptString[es])) {
					test = false;
					break;
				} else
					test = true;
			}
			// ���testΪ��Ļ���������ֵ���Ʋ���
			if (test) {
				// ����Ŀ������ѭ��
				for (int j = 0; j < pddest.length; j++) {
					// ���Դ������������Ŀ����������ȡ�������һ���ж�
					if (pdsrc[i].getName().equals(pddest[j].getName())) {
						// ���Դ����������Ŀ������������ȡ�������һ������
						if (pdsrc[i].getPropertyType() == pddest[j]
								.getPropertyType()) {
							// ��Դ��������ֵ���Ƶ�Ŀ���������ֵ����
							try {
								PropertyUtils.setProperty(
										dest,
										pddest[j].getName(),
										PropertyUtils.getProperty(src,
												pdsrc[i].getName()));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}
							// ���Դ����������Ŀ���������Ͳ���� ��������������ת��Ϊ���
						} else {
							try {
								// ���Դ���������������������Ͳ���Ŀ������������������ַ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.util.Date")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.String"))
									// ��ת��Ϊ�ַ����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													DatetoString((Date) PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ�����������������ַ����Ͳ���Ŀ�����������������������
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.String")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.util.Date"))
									// ��ת��Ϊ�����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													StringtoDate((String) PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ���������������Ǹ����Ͳ���Ŀ������������������ַ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.Double")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.String"))
									// ��ת��Ϊ�ַ����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													ObjectToString(PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ�����������������ַ����Ͳ���Ŀ����������������Ǹ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.String")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.Double"))
									// ��ת��Ϊ�����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													StrToDouble((String) PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ�������������������Ͳ���Ŀ������������������ַ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.Integer")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.String"))
									// ��ת��Ϊ�ַ����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													ObjectToString(PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ�����������������ַ����Ͳ���Ŀ���������������������
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.String")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.Integer"))
									// ��ת��Ϊ���͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													StrToInteger((String) PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ���������������ǳ����Ͳ���Ŀ������������������ַ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.Long")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.String"))
									// ��ת��Ϊ�ַ����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													ObjectToString(PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
								// ���Դ�����������������ַ����Ͳ���Ŀ����������������ǳ�����
								if (pdsrc[i].getPropertyType().getName()
										.equals("java.lang.String")
										&& pddest[j].getPropertyType()
												.getName()
												.equals("java.lang.Long"))
									// ��ת��Ϊ�����͵�Դ��������ֵ���Ƶ�Ŀ���������ֵ����
									PropertyUtils
											.setProperty(
													dest,
													pddest[j].getName(),
													StrToLong((String) PropertyUtils
															.getProperty(
																	src,
																	pdsrc[i].getName())));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		*/
	}

	@SuppressWarnings("rawtypes")
	public void copyMapToBean(HashMap src, Object dest) {
		/*
		PropertyDescriptor[] pddest = PropertyUtils
				.getPropertyDescriptors(dest);

		for (int j = 0; j < pddest.length; j++) {
			String value = (String) src.get(pddest[j].getName());
			if (pddest[j].getPropertyType().getName()
					.equals("java.lang.String")) {
				try {
					PropertyUtils.setProperty(dest, pddest[j].getName(), value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (pddest[j].getPropertyType().getName()
							.equals("java.util.Date"))
						PropertyUtils.setProperty(dest, pddest[j].getName(),
								StringtoDate(value));
					if (pddest[j].getPropertyType().getName()
							.equals("java.lang.Double"))
						PropertyUtils.setProperty(dest, pddest[j].getName(),
								StrToDouble(value));
					if (pddest[j].getPropertyType().getName()
							.equals("java.lang.Integer"))
						PropertyUtils.setProperty(dest, pddest[j].getName(),
								StrToInteger(value));
					if (pddest[j].getPropertyType().getName()
							.equals("java.lang.Long"))
						PropertyUtils.setProperty(dest, pddest[j].getName(),
								StrToLong(value));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		*/
	}

	/**
	 * Ĭ������ת����ʽ
	 */
	public static String datefmt = "yyyy-MM-dd";
	/**
	 * ����ʱ���ʽ
	 */
	public static String datetimefmt = "yyyy-MM-dd hh:mm:ss";

	public String dateformat = datefmt;

	/**
	 * ����ת��Ϊ�ַ���
	 * 
	 * @param dt
	 *            �����Ͳ���
	 */
	public String DatetoString(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(dateformat);
		return sf.format(dt);
	}

	/**
	 * �ַ���ת��ΪDate
	 * 
	 * @param date
	 *            String�Ͳ���
	 */
	public Date StringtoDate(String date) {
		try {
			if (date == null || date.trim().length() == 0) {
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat(dateformat);
			return df.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * �ַ���ת��Ϊ����
	 * 
	 * @param s
	 *            �ַ��Ͳ���
	 */
	public Integer StrToInteger(String s) {
		try {
			return Integer.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ַ���ת��Ϊ������
	 * 
	 * @param s
	 *            �ַ��Ͳ���
	 */
	public Long StrToLong(String s) {
		try {
			return Long.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ַ���ת��Ϊ˫������
	 * 
	 * @param s
	 *            �ַ��Ͳ���
	 */
	public Double StrToDouble(String s) {
		try {
			return Double.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * objectת��ΪSting
	 * 
	 * @param src
	 *            �������
	 */
	public String ObjectToString(Object src) {
		if (src != null)
			return String.valueOf(src);
		else
			return null;
	}

	/**
	 * ��������ת����ʽ
	 * 
	 * @param dateformat
	 */
	public String getDateformat() {
		return dateformat;
	}

	/**
	 * ��������ת����ʽ
	 * 
	 * @param dateformat
	 */
	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	/**
	 * pojo��������������ֵ���
	 * 
	 * @param obj
	 *            pojo�������pojo�����list
	 * @param wholeWord
	 *            ���������ture�ǣ�false��ѡ��falseֻ���ǰ30������
	 */
	@SuppressWarnings("rawtypes")
	public static void logOut(Object obj, boolean wholeWord) {
		// �������Ϊ�յĻ�������һ������
		if (obj != null) {
			// ��ʹ����ʶ
			boolean showTitle = true;
			// �����������������Ǽ��ϵĻ���������һ������
			if (obj.getClass().getName().equals("java.util.ArrayList")
					|| obj.getClass().getName().equals("java.util.List")) {
				showTitle = true;
				// ������ת�ɼ���
				List list = (List) obj;
				// ��������
				for (int i = 0; i < list.size(); i++) {
					// ������������Ԫ�ش���object������ȥ
					Object o = list.get(i);
					// ����organizStr��object����,���볤�ȱ�ʶ�������ʶ�������������
					organizStr(o, wholeWord, showTitle);
					showTitle = false;
				}
			}
			// ���� ������������������ map
			else if (obj.getClass().getName().equals("java.util.HashSet")) {
				showTitle = true;
				// ������ת��set����
				Set set = (Set) obj;
				// ��set����ת�� ��Ϊ it�� ������
				Iterator it = set.iterator();
				// ����������
				while (it.hasNext()) {
					// ������������Ԫ�ش���object������ȥ
					Object o = it.next();
					// ����organizStr��object����,���볤�ȱ�ʶ�������ʶ�������������
					organizStr(o, wholeWord, showTitle);
					showTitle = false;
				}
				// ���� ����organizStr��object����,���볤�ȱ�ʶ�������ʶ�������������
			} else {
				organizStr(obj, wholeWord, true);
			}
		}
	}

	/**
	 * ��֯����ַ���
	 * 
	 * @param o
	 *            Ҫ��������Ķ���
	 * @param wholeWord
	 *            ���������ture�ǣ�false��ѡ��falseֻ���ǰ30������
	 * @param showTitle
	 *            �Ƿ����������pojo����
	 */
	private static void organizStr(Object o, boolean wholeWord,
			boolean showTitle) {
		/*
		String result = "";
		String key = "";
		String value = "";
		// �����������ת������������
		PropertyDescriptor[] pdout = PropertyUtils.getPropertyDescriptors(o);
		// �����ʶΪ�档��������������ӡ������������
		//if (showTitle)
			//log.info("**********************" + o.getClass().getName()
			//		+ "*************************" + showTitle);
		// ��ʼ����ʶ
		boolean test = true;
		// ��ʼ������
		List<Object> nextlist = new ArrayList<Object>();
		// ѭ����������
		for (int j = 0; j < pdout.length; j++) {
			// ѭ�����ڴ�ӡ���Ե��ַ�������
			for (int es = 0; es < exceptString.length; es++) {
				// ����������Ƶ��ڲ���ӡ����������,��ʶΪ�ٲ����ˡ������ʶΪ��
				if (pdout[j].getName().equals(exceptString[es])) {
					test = false;
					break;
				} else
					test = true;
			}
			// �����ʶΪ��
			if (test) {
				// ��������ʶΪ��
				if (showTitle) {
					// ����������׷���ַ���������
					key += addorSubKey(pdout[j].getName(), wholeWord) + "|";
				}
				try {
					// ������ֵ �����object������
					Object getobj = PropertyUtils.getProperty(o,
							pdout[j].getName());
					// ������ֵ ׷���ַ��б�����
					value += addorSubValue(getobj, wholeWord) + "|";
					// ���object����Ϊ�յ� ������һ������
					if (getobj != null) {
						// ��object ������������Ʒ����ַ���������
						String type = getobj.getClass().getName();
						// �ַ���������ֵ������� HashSet �� HashSet�� ArrayList ������һ������
						if (type.indexOf("HashSet") != -1
								|| type.indexOf("HashSet") != -1
								|| type.indexOf("ArrayList") != -1) {
							// ���object�����Ǽ��ϵĻ������뼯����
							nextlist.add(getobj);
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}

			}
		}
		// �ַ�������
		key += "\n";
		// ��ȡ�ַ����ĳ���ֵ
		int kl = key.length();
		// ѭ���ַ����ĳ���
		for (int lenKey = 0; lenKey < kl; lenKey++) {
			// ���ַ���������׷ �� -
			key += "-";
		}
		// ��������ʶΪ��
		if (showTitle) {
			// �ַ������� ׷�� ����׷�� �������ַ������� ׷�� ���� ׷�� ����ֵ�ַ�������
			result += "\n" + key + "\n" + value;
		} else {
			// �ַ������� ׷�� ����ֵ�ַ�������
			result += value;
		}
		// System.out.println(result);
		// ��������
		for (int n = 0; n < nextlist.size(); n++) {
			// ������Ԫ�ش���object������
			Object nextobj = nextlist.get(n);
			// logOut(������ , �������Ʊ�ʶ)
			logOut(nextobj, wholeWord);
		}
		*/
	}

	/**
	 * pojo������������ӿո���ָ���
	 * 
	 * @param str
	 *            Ҫ������ַ���
	 * @param wholeWord
	 *            ���������ture�ǣ�false��ѡ��falseֻ���ǰ30������
	 */
	private static String addorSubKey(Object str, boolean wholeWord) {

		int showLength = 30;
		String result = "";

		result = String.valueOf(str);
		int len = result.length();
		if (len < showLength) {
			for (int i = 0; i < (showLength - len) / 2; i++) {
				result = " " + result;
			}

			for (int i = 0; i < (showLength - len) / 2 + 1; i++) {
				result += " ";
			}
			int oddOrEven = (showLength - len) % 2;
			if (oddOrEven == 1) {
				result += " ";
			}
		} else if (len > showLength) {
			if (!wholeWord) {
				result = result.substring(0, showLength + 1);
			}
		}

		return result;
	}

	/**
	 * pojo��������ֵ��ӿո���ָ���
	 * 
	 * @param str
	 *            Ҫ������ַ���
	 * @param wholeWord
	 *            ���������ture�ǣ�false��ѡ��falseֻ���ǰ30������
	 */
	private static String addorSubValue(Object str, boolean wholeWord) {

		int showLength = 30;
		String result = "";

		if (str != null) {
			result = String.valueOf(str);

			int len = result.length();
			if (len < showLength) {
				for (int i = 0; i <= showLength - len; i++) {
					result += " ";
				}
			} else if (len > showLength) {
				if (!wholeWord) {
					result = result.substring(0, showLength + 1);
				}
			}
		} else {
			result += "<NULL>";
			for (int i = 6; i <= showLength; i++) {
				result += " ";
			}
		}

		return result;
	}

	public static void main(String s[]) 
	{
		/*
		PatVisit pmi = new PatVisit();
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(pmi);
		for (PropertyDescriptor pd : pds)
		{
			//if (pd.isPreferred())
				System.out.println(pd.getDisplayName() + " " + pd.getName() + " " + pd.getShortDescription());
		}
		*/
	}
}