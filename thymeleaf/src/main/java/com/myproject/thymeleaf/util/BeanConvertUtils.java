package com.myproject.thymeleaf.util;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Zhanglei Wang
 * 基于 Orika 的 bean 转换工具，动态添加类映射定义
 */
public class BeanConvertUtils {
    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private static <S, D> BoundMapperFacade<S, D> getBoundMapperFacade(Class<S> srcClazz, Class<D> dstClazz) {
        return mapperFactory.getMapperFacade(srcClazz, dstClazz);
    }

    /**
     * 将类型为 S 的 src 对象转换成类型为 D 的对象
     *
     * @param src
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D map(S src, Class<D> dstClazz) {
        if (src == null) {
            return null;
        }
        return getBoundMapperFacade((Class<S>) src.getClass(), dstClazz).map(src);
    }

    /**
     * 将类型为 S 的 src 对象集合，转换成类型为 D 的对象列表
     *
     * @param srcCollection
     * @param srcClazz
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> List<D> listMap(Collection<S> srcCollection, Class<S> srcClazz, Class<D> dstClazz) {
        if (srcCollection == null) {
            return null;
        }

        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade(srcClazz, dstClazz);

        List<D> dstList = new ArrayList<>(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            dstList.add(d);
        }
        return dstList;
    }

    /**
     * 将类型为 S 的 src 对象列表，转换成类型为 D 的对象列表
     *
     * @param srcCollection
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> List<D> listMap(List<S> srcCollection, Class<D> dstClazz) {
        if (srcCollection == null) {
            return null;
        }

        if (srcCollection.size() == 0) {
            return Collections.emptyList();
        }

        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade((Class<S>) srcCollection.get(0).getClass(), dstClazz);

        List<D> dstList = new ArrayList<>(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            dstList.add(d);
        }
        return dstList;
    }

    /**
     * 将类型为 S 的 src 对象集合，转换成类型为 <K, D> 的Map, 主键的值使用每个对象的 keyFieldName 域的值，例如 refId
     *
     * @param srcCollection
     * @param srcClazz
     * @param dstClazz
     * @param keyFieldName
     * @param <S>
     * @param <D>
     * @param <K>
     * @return
     */
    public static <S, D, K> Map<K, D> mapByFieldMap(Collection<S> srcCollection, Class<S> srcClazz, Class<D> dstClazz, String keyFieldName) throws NoSuchFieldException, IllegalAccessException {
        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade(srcClazz, dstClazz);
        Field keyField = dstClazz.getDeclaredField(keyFieldName);
        keyField.setAccessible(true);

        Map<K, D> dstMap = new HashMap(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            K keyValue = (K) keyField.get(d);
            dstMap.put(keyValue, d);
        }
        return dstMap;
    }

    /**
     * 忽略 id created updated deleted version
     *
     * @param src
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D mapIgnoreGenericKey(S src, Class<D> dstClazz) {
        if (src == null) {
            return null;
        }
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(src.getClass(), dstClazz)
                .exclude("id")
                .exclude("created")
                .exclude("updated")
                .exclude("deleted")
                .exclude("version")
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade().map(src, dstClazz);
    }

    private final static String GET_PREFIX = "get";

    /**
     * 将对象转换成Map
     *
     * @param obj 需要转换的对象
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> convert2Map(final T obj) {
        Map<String, Object> map = new HashMap<>();
        if (null == obj) return map;
        Class<?> clazz = obj.getClass();
        //循环父类属性
        while (!clazz.equals(Object.class)) {
            Field[] fields = getAllDeclaredFields(clazz);
            //循环类型属性
            for (Field f : fields) {
                int modifer = f.getModifiers();
                //排除final和static修饰的属性
                if (Modifier.isFinal(modifer) || Modifier.isStatic(modifer)) continue;
                String fieldName = f.getName();
                Method method = null;
                try {


                    String fName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    method = clazz.getMethod(GET_PREFIX + fName);
                    if (null == method) continue;
                    if (null != method.invoke(obj)) {
                        map.put(fieldName, method.invoke(obj));
                    }
                } catch (Exception e) {

                }
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

    public static <T> Field[] getAllDeclaredFields(Class<T> clazz) {

        List<Field[]> fieldArrayList = new ArrayList<Field[]>();

        while (clazz != null) {

            fieldArrayList.add(clazz.getDeclaredFields());

            clazz = (Class<T>) clazz.getSuperclass();
        }

        int fieldCount = 0;
        int fieldIndex = 0;
        for (Field[] fieldArray : fieldArrayList) {

            fieldCount = fieldCount + fieldArray.length;
        }

        Field[] allFields = new Field[fieldCount];

        for (Field[] fieldArray : fieldArrayList) {

            for (Field field : fieldArray) {

                allFields[fieldIndex++] = field;
            }
        }

        return allFields;
    }


}
