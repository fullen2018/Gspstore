package business.util;

public class FormatUtil
{
    /**
     * 把数字转换为有前缀的
     * @param maxLength
     * @param current
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String  convertIntToString(int maxLength,int current){
        String temp = String.valueOf(current);
        while (temp.length()<maxLength){
            temp = "0"+temp;
        }
        return temp;
    }
}
