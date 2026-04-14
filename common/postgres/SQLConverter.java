package postgres;

public class SQLConverter {
    public static String toSQL(Object[] list) {
        String s = "";
        for (int i = 0; i<list.length-1; i++) {
            Object o = list[i];
            s+=o.toString()+", ";
        }
        return s+list[list.length-1].toString();
    }
}
