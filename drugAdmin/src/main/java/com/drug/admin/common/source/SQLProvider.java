package com.drug.admin.common.source;


import com.drug.admin.common.spring.ApplicationFactory;

public class SQLProvider {

	public static ISqlSource xmlSqlSource;

	/**
	 * 从xml中读取sql文件
	 */
	public static String getFromXml(String sqlId) {
		return getXmlSource().getSql(sqlId);
	}


	/**
	 * xml代表从xml文件中读sql，db代表从数据库红读sql <code>{@link SourceType}</code>
	 * 
	 * @param key
	 * @return
	 */
	public static ISqlSource get(String key) {
		if (key.equals(SourceType.xml)) {
			return getXmlSource();
		}  else {
			throw new SqlSourceException(String.format("不支持%s，只支持xml", key));
		}
	}

	private static ISqlSource getXmlSource() {
		if (xmlSqlSource == null) {
			xmlSqlSource = ApplicationFactory.getBean("xmlSqlSource", ISqlSource.class);
		}
		return xmlSqlSource;
	}

	public class SourceType {
		public final static String xml = "xml";
	}

}
