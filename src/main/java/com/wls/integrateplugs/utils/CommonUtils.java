/**
 * @Project : offical-website;
 * @Program Name  : com.ehaoyao.ordercenter.utils.CommentUtils.java;
 * @Class Name    : CommentUtils;
 * @Description : ;
 * @Author : longshanw;
 * @Creation Date : 2016年7月27日 下午4:36:13 ;
 * @ModificationHistory ;
 * Who        When          What ;
 * --------   ----------    -----------------------------------;
 * username   2016年7月27日       TODO;
 */

package com.wls.integrateplugs.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project : offical-website;
 * @Program Name : com.ehaoyao.ordercenter.utils.CommentUtils.java;
 * @Class Name : CommentUtils;
 * @Description : 公共类;
 * @Author : longshanw;
 * @Creation Date : 2016年7月27日 下午4:36:13 ;
 * @ModificationHistory ; Who When What ; -------- ----------
 *                      -----------------------------------; username 2016年7月27日
 *                      TODO;
 */

public class CommonUtils {
    private static Logger logger = LoggerFactory.getLogger ( CommonUtils.class );

    public static void closeThreadPool(ExecutorService excutor) {
        excutor.shutdown ( );
        while (true) {
            if (excutor.isTerminated ( )) {
                break;
            }
            try {
                Thread.sleep ( 5000 );
            } catch (InterruptedException e) {
                e.printStackTrace ( );
            }
        }
    }

    /**
     * 获取对象及字段名称获取字段值
     *
     * @param obj
     *            对象
     * @param filed
     *            字段名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getPropertyVal(Object obj, String filed) {
        Object propertyVal = null;
        try {
            Class clazz = obj.getClass ( );
            PropertyDescriptor pd = new PropertyDescriptor ( filed, clazz );
            Method getMethod = pd.getReadMethod ( );// 获得get方法

            if (pd != null) {
                propertyVal = getMethod.invoke ( obj );// 执行get方法返回一个Object
            }
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return propertyVal;
    }

    /**
     * @Description : 判断字符串是否为数字;
     * @Method_Name : isNumeric;
     * @param str
     * @return :
     * @Creation Date : 2016年8月12日 下午2:38:52 ;
     * @Author : longshanw;
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.trim ( ).length ( ) == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile ( "[0-9]*" );
        return pattern.matcher ( str ).matches ( );
    }

    /**
     * 去掉bean中所有属性为字符串的前后空格
     *
     * @param bean
     * @throws Exception
     */
    public static void beanAttributeValueTrim(Object bean) throws Exception {
        if (bean != null) {
            // 获取所有的字段包括public,private,protected,private
            Field[] fields = bean.getClass ( ).getDeclaredFields ( );
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                if (f.getType ( ).getName ( ).equals ( "java.lang.String" )) {
                    String key = f.getName ( );// 获取字段名
                    Object value = getFieldValue ( bean, key );

                    if (value == null)
                        continue;

                    setFieldValue ( bean, key, value.toString ( ).trim ( ) );
                }
            }
        }
    }

    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     *
     * @param bean
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object getFieldValue(Object bean, String fieldName)
            throws Exception {
        StringBuffer result = new StringBuffer ( );
        String methodName = result.append ( "get" )
                .append ( fieldName.substring ( 0, 1 ).toUpperCase ( ) )
                .append ( fieldName.substring ( 1 ) ).toString ( );

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        method = bean.getClass ( ).getMethod ( methodName, classArr );
        rObject = method.invoke ( bean, new Object[0] );

        return rObject;
    }

    /**
     * 利用发射调用bean.set方法将value设置到字段
     *
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        StringBuffer result = new StringBuffer ( );
        String methodName = result.append ( "set" )
                .append ( fieldName.substring ( 0, 1 ).toUpperCase ( ) )
                .append ( fieldName.substring ( 1 ) ).toString ( );

        /**
         * 利用发射调用bean.set方法将value设置到字段
         */
        Class[] classArr = new Class[1];
        classArr[0] = "java.lang.String".getClass ( );
        Method method = bean.getClass ( ).getMethod ( methodName, classArr );
        method.invoke ( bean, value );
    }

    /**
     * 功能：检测当前URL是否可连接或是否有效, 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
     *
     * @param urlStr
     *            指定URL网络地址
     * @return URL
     */
    public static boolean isConnect(String urlStr) {
        boolean flag = false;
        URL url = null;
        HttpURLConnection con;
        int state;
        int counts = 0;
        if (urlStr == null || urlStr.length ( ) <= 0) {
            return flag;
        }
        while (counts < 5) {
            try {
                url = new URL ( urlStr );
                con = (HttpURLConnection) url.openConnection ( );
                state = con.getResponseCode ( );
                logger.info ( "【判断url是否有效】，返回状态码：{}", state );
                if (state == 200) {
                    flag = true;
                }
                break;
            } catch (Exception ex) {
                counts++;
                urlStr = null;
                continue;
            }
        }
        return flag;
    }

    /**
     *
     *  @Description    : 检查网络地址是否可用;
     *  @Method_Name    : testUrlWithTimeOut;
     *  @param urlString
     *  @param timeOutMillSeconds
     *  @return         : ;
     *  @Creation Date  : 2017年6月9日 下午4:40:43 ;
     *  @Author         : dell;
     */
    public static boolean testUrlWithTimeOut(String urlString, int timeOutMillSeconds) {
        URL url;
        boolean flag = false;
        try {
            url = new URL ( urlString );
            URLConnection co = url.openConnection ( );
            co.setConnectTimeout ( timeOutMillSeconds );
            co.connect ( );
            flag = true;
            logger.info ( "【判断url是否有效】，地址：{},连接可用", urlString );
        } catch (Exception e1) {
            flag = false;
            logger.info ( "【判断url是否有效】，地址：{},连接不可用", urlString );
            url = null;
        }
        return flag;
    }


    public static File inputstreamtofile(InputStream ins) throws Exception {
        File file = new File ( "1.jpg" );
        OutputStream os = new FileOutputStream ( file );
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read ( buffer, 0, 8192 )) != -1) {
            os.write ( buffer, 0, bytesRead );
        }
        os.close ( );
        ins.close ( );
        return file;
    }


    /**
     * 修改程序。<br>
     * 内部递归调用，进行子目录的更名
     *
     * @param path
     *          路径
     * @param from
     *          原始的后缀名，包括那个(.点)
     * @param to
     *          改名的后缀，也包括那个(.点)
     */
    public void reName(String path, String from, String to) throws Exception {
        File f = new File ( path );
        File[] fs = f.listFiles ( );
        for (int i = 0; i < fs.length; ++i) {
            File f2 = fs[i];
            if (f2.isDirectory ( )) {
                reName ( f2.getPath ( ), from, to );
            } else {
                String name = f2.getName ( );
                if (name.endsWith ( from )) {
                    f2.renameTo ( new File ( f2.getParent ( ) + "/"
                                             + name.substring ( 0, name.indexOf ( from ) ) + to ) );
                }
            }
        }
    }

    /**
     * 使用正则表达式提取中括号中的内容
     *
     * @param msg
     * @return
     */
    public static List <String> extractMessageByRegular(String msg) {
        List <String> list = new ArrayList <String> ( );
        Pattern p = Pattern.compile ( "(\\[[^\\]]*\\])" );
        Matcher m = p.matcher ( msg );
        while (m.find ( )) {
            list.add ( m.group ( ).substring ( 1, m.group ( ).length ( ) - 1 ) );
        }
        return list;
    }

    /**
     * 提取中括号中的内容
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public static String matchMessage(String msg) throws Exception {
        String regex = "(\\[[^\\]]*\\])";
        Pattern pattern = Pattern.compile ( regex );
        Matcher matcher = pattern.matcher ( msg );
        List <String> list = new ArrayList <String> ( );
        while (matcher.find ( )) {
            list.add ( matcher.group ( ).substring ( 1, matcher.group ( ).length ( ) - 1 ) );
        }
        if (!list.isEmpty ( )) {
            return list.get ( 0 );
        }
        return null;
    }

    /**
     * List去重
     *
     * @param list
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List removeDuplicate(List list) {
        Set set = new HashSet ( );
        List newList = new ArrayList ( );
        for (Iterator <Object> iter = list.iterator ( ); iter.hasNext ( ); ) {
            Object element = (Object) iter.next ( );
            if (set.add ( JSONObject.toJSONString ( element ) ))
                newList.add ( element );
        }
        return newList;
    }

    public static boolean isBlankOrNull(String str) throws Exception {
        if (null == str)
            return true;
        return str.trim ( ).length ( ) == 0;
    }

    /**
     * 特殊符号替换，默认为空字符串
     *
     * @param str
     * @param pattern
     * @param replace
     * @return
     */
    public static String replaceSpecialtyStr(String str, String pattern,
                                             String replace) throws Exception {
        if (StringUtils.isEmpty ( str ))
            return "";
        if (isBlankOrNull ( pattern )) {
            pattern = "\\s*|\t|\r|\n";// 去除字符串中空格、换行、制表}
        }
        if (isBlankOrNull ( replace )) {
            replace = "";
        }
        return Pattern.compile ( pattern ).matcher ( str ).replaceAll ( replace );
    }

}
