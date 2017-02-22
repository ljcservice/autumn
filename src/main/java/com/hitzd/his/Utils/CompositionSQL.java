package com.hitzd.his.Utils;


import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author tyl
 * 
 */
public class CompositionSQL {
	/**
	 * ִ�и��²�������Ҫ�ṩquery����request���󡢱������ֶ��б�
	 * 
	 * @param query
	 * @param request
	 * @param table
	 * @param fileList
	 * @return ����״̬
	 */
	public static String update(HttpServletRequest request, String tableName,
			String fileList, String identity) {

		StringBuilder sb = new StringBuilder(" update ");
		sb.append(tableName);
		sb.append(" set ");
		String id = request.getParameter(identity);
		String values = null;
		if (fileList.contains(",")) {
			String[] fileArray = fileList.split(",");
			for (int i = 0; i < fileArray.length; i++) {
				values = request.getParameter(fileArray[i].trim());
				if (i == fileArray.length - 1)
					sb.append(fileArray[i]).append("='")
							.append(getValus(values)).append("' where ")
							.append(identity).append("='").append(id)
							.append("'");
				else
					sb.append(fileArray[i]).append("='")
							.append(getValus(values)).append("',");
			}
		} else {
			values = request.getParameter(fileList);
			sb.append(fileList).append("='").append(values).append("' where ")
					.append(identity).append("='").append(id).append("'");
		}
		return sb.toString();
	}

	/**
	 * ִ�в����������Ҫ�ṩquery����request���󡢱������ֶ��б�(������ID)��������
	 * 
	 * @param query
	 * @param request
	 * @param table
	 * @param fileList
	 * @param xuLeiName
	 * @return ����״̬
	 */
	public static String insert(HttpServletRequest request, String table,
			String fileList) {
		StringBuilder sb = new StringBuilder("insert into  ");
		sb.append(table);
		sb.append(" (");
		sb.append(fileList);
		sb.append(") values(");
		String values = null;
		if (fileList.contains(",")) {
			String[] fileArray = fileList.split(",");
			for (int i = 0; i < fileArray.length; i++) {
				values = request.getParameter(fileArray[i].trim());
				if (i == fileArray.length - 1)
					sb.append("'").append(values).append("')");
				else
					sb.append("'").append(values).append("',");
			}
		} else {
			values = request.getParameter(fileList.trim());
			sb.append("'").append(values).append("')");
		}
		return sb.toString();
	}

	/***
	 * ������ɲ�ѯ��䡢�ṩ������request���ֶ��б������ַ�������
	 * 
	 * @param request
	 * @param tableName
	 * @param fieldsList
	 * @param idName
	 * @return ��ѯ�����ַ���
	 */
	public static String query(HttpServletRequest request, String tableName,String fieldsList, String fieldsWheres) {
		StringBuilder sb = new StringBuilder();
		sb.append("select ").append(fieldsList).append(" from ").append(tableName).append(" where 1=1 ");
		if (fieldsWheres.contains(",")) {
			String wid = null;
			String[] w = fieldsWheres.split(",");
			for (String s : w) {
				wid = request.getParameter("Query" + s);
				if (!"".equals(wid) && wid != null) {
					sb.append(" and ").append(s).append("='").append(wid).append("'");
				}
			}
		} else {
			sb.append(" and ").append(fieldsWheres).append("='").append(request.getParameter("Query" + fieldsWheres)).append("'");
		}
		return sb.toString();
	}

	/**
	 * ����ID���ɾ����䣬�ṩ������request����������
	 * 
	 * @param query
	 * @param request
	 * @param table
	 * @return ɾ�������ַ���
	 */
	public static String delete(HttpServletRequest request, String tableName,
			String idName) {
		String id = request.getParameter(idName);
		StringBuilder sb = new StringBuilder();
		if (!"".equals(id) && id != null) {
			sb.append("delete ").append(tableName).append(" where ")
					.append(idName).append("='").append(id).append("'");
		}
		return sb.toString();
	}

	public static String getValus(String values) {
		if (values == null || "".equals(values)) {
			return "";
		}
		return values;
	}

	/***
	 * ���ݱ�identity��ֵ ���sql��䣬����identity=userid �� where userid=ҳ�����userid��ֵ
	 * 
	 * @param request
	 * @param tableName
	 * @param fieldsList
	 * @param fieldsWheres
	 * @return
	 */
	public static String modify(HttpServletRequest request, String tableName,
			String fieldsList, String identity) {
		StringBuilder sb = new StringBuilder();
		sb.append("select ").append(fieldsList).append(" from ")
				.append(tableName).append(" where 1=1 ");
		sb.append(" and ").append(identity).append("='")
				.append(request.getParameter(identity)).append("'");
		return sb.toString();
	}
}
