package Spring注入集合属性;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 集合属性注入
 * Created by lizhen on 2018/10/29.
 */
public class Cat {
    /** 注入List集合 **/
    private List<String> listNames;

    /***注入Set集合*/
    private Set<String> setNames;

    /** 注入Properties **/
    private Properties propertiesNames;

    /** 注入Map集合 **/
    private Map<String, String> mapNames;

    /** 注入数组 **/
    private String[] arrayNames;


    /**
     * 打印数组集合
     */
    public void sayArrayNames() {
        System.out.println("注入数组-->");
        if (arrayNames != null && arrayNames.length > 0) {
            for (int i = 0; i < arrayNames.length; i++) {
                System.out.println(arrayNames[i]);
            }
        }
        System.out.println();
    }

    /**
     * 打印Map集合
     */
    public void sayMapNames() {
        if (null != mapNames && mapNames.size() > 0) {
            System.out.println("注入Map集合-->");
            for (Map.Entry<String, String> entry : mapNames.entrySet()) {
                System.out.println("key= " + entry.getKey() + " value= " + entry.getValue());
            }
            System.out.println();
        }
    }

    /**
     * 打印Properties属性
     */
    public void sayPropertiesNames() {
        if (null != propertiesNames) {
            System.out.println("注入properties属性-->");
            System.out.println(propertiesNames.get("name"));
            System.out.println(propertiesNames.get("age"));
            System.out.println();
        }
    }

    /**
     * 打印List集合
     */
    public void sayListNames() {
        if (!CollectionUtils.isEmpty(listNames)) {
            System.out.println("注入List集合-->");
            for (String string : listNames) {
                System.out.println(string);
            }
            System.out.println();
        }
    }

    /**
     * 打印Set集合
     */
    public void saySetNames() {
        if (!CollectionUtils.isEmpty(setNames)) {
            System.out.println("注入Set集合-->");
            Iterator<String> iterator = setNames.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println();
        }
    }

    public List<String> getListNames() {
        return listNames;
    }

    public void setListNames(List<String> listNames) {
        this.listNames = listNames;
    }

    public Set<String> getSetNames() {
        return setNames;
    }

    public void setSetNames(Set<String> setNames) {
        this.setNames = setNames;
    }

    public Properties getPropertiesNames() {
        return propertiesNames;
    }

    public void setPropertiesNames(Properties propertiesNames) {
        this.propertiesNames = propertiesNames;
    }

    public Map<String, String> getMapNames() {
        return mapNames;
    }

    public void setMapNames(Map<String, String> mapNames) {
        this.mapNames = mapNames;
    }

    public String[] getArrayNames() {
        return arrayNames;
    }

    public void setArrayNames(String[] arrayNames) {
        this.arrayNames = arrayNames;
    }

}
