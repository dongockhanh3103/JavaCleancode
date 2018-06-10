package vn.kms.launch.cleancode.Utils;
import java.lang.reflect.Field;
import java.util.Comparator;

public class SortUtil<T> implements Comparator<T> {
    String fieldName;

    public SortUtil( String fieldName){
        this.fieldName = fieldName;
    }

    private double parseStringToDouble(String strValue){
        return Double.parseDouble(strValue);
    }

    public static boolean isNumberic(String stringChecking){
        return stringChecking.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public int compare(T object1, T object2) {
        if (object1==null && object2==null) {
            return 0;
        }
        if(object1==null){
            return -1;
        }
        if(object2==null){
            return 1;
        }
        try {
            Field field1= object1.getClass().getDeclaredField(this.fieldName);
            Field field2= object2.getClass().getDeclaredField(this.fieldName);
            field1.setAccessible(true);
            field2.setAccessible(true);
            if(isNumberic(field1.get(object1).toString())){
                return Double.compare(parseStringToDouble(field1.get(object1).toString())
                        ,parseStringToDouble(field2.get(object2).toString()));
            }
            else {
                return field1.get(object1).toString().compareTo(field2.get(object2).toString());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }


}