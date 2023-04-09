import java.util.HashMap;
import java.util.Map;

public class Enums {
    public static void main(String[] args) {
        Color red = Color.RED;
        System.out.println(red.getCode());
        Map<String, Operation> actions = new HashMap<>();
        actions.put("sum", Operation.SUM);
        actions.put("sub", Operation.SUBTRACT);
        actions.put("multi", Operation.MULTIPLY);
        System.out.println(actions.get("sum").action(3, 5));
        System.out.println(actions.get("multi").action(3,5));

    }
    enum Color{
        RED("#FF0000"), BLUE("#0000FF"), GREEN("#00FF00");
        private String code;
        Color(String code){
            this.code = code;
        }
        public String getCode(){ return code;}
    }

    enum Operation{
        SUM{
            public int action(int x, int y){ return x + y;}
        },
        SUBTRACT{
            public int action(int x, int y){ return x - y;}
        },
        MULTIPLY{
            public int action(int x, int y){ return x * y;}
        };
        public abstract int action(int x, int y);
    }
}
