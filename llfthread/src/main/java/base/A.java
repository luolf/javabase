package base;

/**
 * 测试加载顺序
 */
public class A {
    static {
        System.out.println("A static1"+Thread.currentThread().getName());
    }
    {
        System.out.println("A not static1");
    }
    B b = new B();
    {
        System.out.println("A not static2");
    }
    static C c = new C();
    {
        System.out.println("A not static3");
    }
    static {
        System.out.println("A static2");
    }
    A(){
        System.out.println("A constructor");
    }
    public static void main(String[] args) {
        System.out.println("Hello World!"+Thread.currentThread().getName());
        new B();
        A m = new A();
    }
}

class B{
    static {
        System.out.println("B static");
    }
    {
        System.out.println("B not static");
    }
    B(){
        System.out.println("B constructor");
    }
}