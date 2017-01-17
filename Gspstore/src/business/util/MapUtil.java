package business.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapUtil
{
    public static List<Map<String,Object>> changeMapListToLower(List<Map<String,Object>> oldMapList){
        List<Map<String,Object>> newMapList = new ArrayList<Map<String,Object>>();
        if (oldMapList!=null&&oldMapList.size()>0){
            for (Map<String,Object> old:oldMapList){
                newMapList.add(changeMapToLower(old));
            }
        }
        return newMapList;
    }

    public static Map<String, Object> changeMapToLower(Map<String, Object> old)
    {
        Map<String, Object> newMap = new HashMap<String, Object>();
        if (old!=null){
           Iterator<String> iter = old.keySet().iterator();
           while(iter.hasNext()){
               String oldkey = iter.next();
               newMap.put(oldkey.toLowerCase(), old.get(oldkey));
           }
        }
        return newMap;
    }
    public static List<Map<String,Object>> changeMapListToUpper(List<Map<String,Object>> oldMapList){
        List<Map<String,Object>> newMapList = new ArrayList<Map<String,Object>>();
        if (oldMapList!=null&&oldMapList.size()>0){
            for (Map<String,Object> old:oldMapList){
                newMapList.add(changeMapToUpper(old));
            }
        }
        return newMapList;
    }
    
    public static Map<String, Object> changeMapToUpper(Map<String, Object> old)
    {
        Map<String, Object> newMap = new HashMap<String, Object>();
        if (old!=null){
            Iterator<String> iter = old.keySet().iterator();
            while(iter.hasNext()){
                String oldkey = iter.next();
                newMap.put(oldkey.toLowerCase(), old.get(oldkey));
            }
        }
        return newMap;
    }
}
