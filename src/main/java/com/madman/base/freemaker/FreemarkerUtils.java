package com.madman.base.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class FreemarkerUtils {

    public static void main(String[] args) throws Exception {
        createFtl("payuser");
    }

    public static void createFtl(String... str) throws Exception {
        for (String string : str) {
            String tableName = string.toUpperCase();
            /** 生成文件目录 */
            String path = "d:\\bean/";

            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }

            Map<String, Object> map     = DBHelper.getTableInfo(tableName);
            String              clsName = map.get("clsName").toString();
            // 执行插值，并输出到指定的输出流中
            createFile("sqlxml.ftl", path + clsName + "Mapper.xml", map);
            createFile("bean.ftl", path + clsName + ".java", map);
            createFile("dao.ftl", path + clsName + "Dao.java", map);
            System.out.println("创建完成");
        }

    }

    /**
     * createFile:(这里用一句话描述这个方法的作用). <br/>
     * @param templatName templatName <br/>
     * @param fileName fileName <br/>
     * @param map map <br/>
     * @throws IOException IOException <br/>
     * @throws TemplateException TemplateException <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayIFramework 1.0 <br/>
     */
    public static void createFile(String templatName, String fileName, Map<String, Object> map) throws IOException, TemplateException {
        @SuppressWarnings("deprecation")
        Configuration cfg = new Configuration();
        System.out.println(FreemarkerUtils.class.getClass().getResource("/"));
//        String path = FreemarkerUtils.class.getResource("").getPath() + "ftl";
        String path = FreemarkerUtils.class.getResource("/").getPath() + "ftl";
        //String path = FreemarkerUtils.class.getResource("").getPath() + "ftlProject";
        cfg.setDirectoryForTemplateLoading(new File(path));
        Template t = cfg.getTemplate(templatName);
        t.process(map, new OutputStreamWriter(new FileOutputStream(fileName)));
    }

}
