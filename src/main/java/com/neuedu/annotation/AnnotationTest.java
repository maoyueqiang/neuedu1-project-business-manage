package com.neuedu.annotation;

import com.neuedu.pojo.UserInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zs");
        userInfo.setPassword("123");
        userInfo.setRole(1);
        new AnnotationTest().query(userInfo);

    }

    public void test(){
        try {
            //获取类的实例
            Class c = Class.forName("com.neuedu.pojo.UserInfo");

            //判断该类是否有@Table注解
            boolean isexistsTableAnnotation = c.isAnnotationPresent(Table.class);

            if(isexistsTableAnnotation){
                //获取注解的实例
                Table table = (Table)c.getAnnotation(Table.class);
                //打印注解内的名字
                System.out.println(table.value());

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String query(UserInfo userinfo){

        StringBuffer buffer = new StringBuffer("select ");



        //获取类对象
        Class c = userinfo.getClass();

        //获取表名
        boolean isexistsTableAnnotation = c.isAnnotationPresent(Table.class);
        Table table=null;
        String tableName=null;
        if(isexistsTableAnnotation){
            table = (Table)c.getAnnotation(Table.class);
            tableName=table.value();
        }

        //获取列名
        Field[] fields= c.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            if(i!=0){
                buffer.append(",");
            }
            Field field = fields[i];
            boolean isexistsColumnAnnotation = field.isAnnotationPresent(Column.class);
            if(isexistsColumnAnnotation){
                Column column= (Column) field.getAnnotation(Column.class);
                String columnName = column.value();
                buffer.append(columnName);
            }
        }
        buffer.append(" from ").append(tableName);
        buffer.append(" where 1=1");
        for(int i=0;i<fields.length;i++){
            Field field = fields[i];
            String fieldName=field.getName();
            String methoedName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            try {
                Method method = c.getMethod(methoedName);
                Object o = method.invoke(userinfo);
                if(o!=null){
                    boolean isexistsColumnAnnotation = field.isAnnotationPresent(Column.class);
                    if(isexistsColumnAnnotation){
                        Column column= (Column) field.getAnnotation(Column.class);
                        String columnName = column.value();
                        if(o instanceof Integer){
                            buffer.append(" and ").append(columnName).append("=").append(o);
                        }
                        else if(o instanceof String)
                        buffer.append(" and ").append(columnName).append("=").append("'").append(o).append("'");
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(buffer);
        return null;
    }


}
