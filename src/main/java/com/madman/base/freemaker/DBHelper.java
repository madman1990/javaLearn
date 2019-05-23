package com.madman.base.freemaker;

import com.madman.base.util.DateUtil;
import com.madman.base.util.EmptyChecker;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.type.JdbcType;

public class DBHelper {

    /**
     * 参数
     */
    private static final String URL       = "jdbc:oracle:thin:@192.168.1.39:1521:orcl";
    /**
     * 参数
     */
    private static final String CLSDRIVER = "oracle.jdbc.OracleDriver";
    /**
     * 参数
     */
    private static final String PWD       = "qtpay";
    /**
     * 用户名
     */
    private static final String USERNME   = "qtpay";

    /**
     * getConnection:(获取数据库连接). <br/>
     * @return 返回结果：Connection <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(CLSDRIVER);
            connection = DriverManager.getConnection(URL, USERNME, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * select:(查询表信息). <br/>
     * @param sql sql <br/>
     * @return 返回结果：ResultSet <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static ResultSet select(String sql) {
        Connection conn = getConnection();
        ResultSet  rs   = null;
        try {
            Statement cs = conn.createStatement();
            rs = cs.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * getTableInfo:(获取表信息). <br/>
     * @param tableName 表名
     * @return 返回结果：Map<String,Object> <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    public static Map<String, Object> getTableInfo(String tableName) {
        Map<String, Object> item = new LinkedHashMap<String, Object>();

        StringBuffer sbsql = new StringBuffer("SELECT T.TABLE_NAME ,");
        sbsql.append("T.COLUMN_NAME ,T.DATA_TYPE ,TC.COMMENTS , T.DATA_LENGTH, T.NULLABLE,T.DATA_DEFAULT");
        sbsql.append(" FROM user_tab_columns T ,");
        sbsql.append(" user_col_comments TC WHERE T.COLUMN_NAME = TC.COLUMN_NAME AND T.TABLE_NAME = TC.TABLE_NAME AND");
        sbsql.append(" T.TABLE_NAME = '").append(tableName);
        sbsql.append("' AND T.COLUMN_NAME != 'OGG_SEQ_ID' order by column_id ");
        // sbsql.append("' AND T.COLUMN_NAME != 'OGG_SEQ_ID' ORDER BY NULLABLE DESC");

        System.out.println(sbsql.toString());
        ResultSet rs = select(sbsql.toString());
        try {

            List<Map<String, Object>> clist = new ArrayList<Map<String, Object>>();
            int                       i     = 1;
            while (rs.next()) {
                Map<String, Object> citem = new LinkedHashMap<String, Object>();
                /** 字段名 */
                String columnName = rs.getString("COLUMN_NAME");
                /** 字段描述 */
                String columnDesc = rs.getString("COMMENTS");
                columnDesc = EmptyChecker.isEmpty(columnDesc) ? "" : columnDesc;
                String columnClsName = changeClumn(columnName);
                String columnType    = changeDateType(rs.getString("DATA_TYPE"));
                String columnClsType = changeClsType(columnType);
                String isNull        = rs.getString("NULLABLE");
                /** 字段名、Java类字段、字段描述、字段类型、Java类字段类型 */
                citem.put("columnName", columnName);
                citem.put("columnClsName", columnClsName);
                citem.put("columnDesc", columnDesc);
                citem.put("columnType", columnType);
                citem.put("columnClsType", columnClsType);
                citem.put("isNull", isNull);
                citem.put("curSize", i++);
                clist.add(citem);
            }
            item.put("TABLINFO", clist);
            item.put("MAXSIZE", clist.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        item.put("tableName", tableName);
        item.put("clsName", changeClsName(tableName));
        item.put("curTime", DateUtil.getCurrTime());
        item.put("curDate", DateUtil.getCurrDate());
        return item;
    }

    /**
     * changeClsName:(获取对像名). <br/>
     * @param tableName tableName
     * @return <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static String changeClsName(String tableName) {
        StringBuffer sb        = new StringBuffer();
        String       lowerCase = tableName.toLowerCase();
        boolean      f         = false;
        int          i         = 1;
        for (char c : lowerCase.toCharArray()) {
            int a = (int) c;
            if (a == 95) {
                f = true;
                i++;
                continue;
            }
            if (f || i == 1) {
                sb.append((c + "").toUpperCase());
                f = false;
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * changeClumn:(获取字段类型). <br/>
     * @param str str
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static String changeClumn(String str) {
        StringBuffer sb        = new StringBuffer();
        String       lowerCase = str.toLowerCase();
        boolean      f         = false;
        for (char c : lowerCase.toCharArray()) {
            int a = (int) c;
            if (a == 95) {
                f = true;
                continue;
            }
            if (f) {
                sb.append((c + "").toUpperCase());
                f = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * changeDateType:(设置数据类型). <br/>
     * @param dataType dataType
     * @return <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static String changeDateType(String dataType) {

        if ("VARCHAR2".equalsIgnoreCase(dataType)) {
            return JdbcType.VARCHAR.name().toString();
        } else if ("CHAR".equalsIgnoreCase(dataType)) {
            return JdbcType.CHAR.name().toString();
        } else if ("NVARCHAR2".equalsIgnoreCase(dataType)) {
            return JdbcType.NVARCHAR.name().toString();
        } else if ("CLOB".equalsIgnoreCase(dataType)) {
            return JdbcType.CLOB.name().toString();
        } else if ("BLOB".equalsIgnoreCase(dataType)) {
            return JdbcType.BLOB.name().toString();
        } else if ("DATE".equalsIgnoreCase(dataType)) {
            return JdbcType.DATE.name().toString();
        } else if ("RAW".equalsIgnoreCase(dataType)) {
            return JdbcType.BLOB.name().toString();
        } else if ("LONG".equalsIgnoreCase(dataType)) {
            return JdbcType.BIGINT.name().toString();
        } else if ("NUMBER".equalsIgnoreCase(dataType)) {
            return JdbcType.NUMERIC.name().toString();
        } else if ("UROWID".equalsIgnoreCase(dataType)) {
            return JdbcType.BLOB.name().toString();
        } else {
            return "VARCHAR";
        }
    }

    /**
     * changeClsType:(获取java对像类蓴). <br/>
     * @param dataType dataType
     * @return <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    private static String changeClsType(String dataType) {

        if (dataType.equalsIgnoreCase(JdbcType.VARCHAR.name().toString())) {
            return String.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.CHAR.name().toString())) {
            return String.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.CLOB.name().toString())) {
            return String.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.BLOB.name().toString())) {
            return String.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.DATE.name().toString())) {
            return "java.util.Date";
        } else if (dataType.equalsIgnoreCase(JdbcType.BLOB.name().toString())) {
            return String.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.BIGINT.name().toString())) {
            return Long.class.getSimpleName();
        } else if (dataType.equalsIgnoreCase(JdbcType.NUMERIC.name().toString())) {
            return BigDecimal.class.getSimpleName();
        } else {
            return String.class.getSimpleName();
        }
    }

    /**
     * main:(这里用一句话描述这个方法的作用). <br/>
     * @param args <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    public static void main(String[] args) {

        System.out.println(String.class.getSimpleName());

        System.out.println(getTableInfo("tmp_demo"));
    }

}
