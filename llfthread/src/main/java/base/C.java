package base;

public class C {
    static {
        System.out.println("C static");
    }
    {
        System.out.println("C not static");
    }
    C(){
        System.out.println("C constructor");
    }
}
