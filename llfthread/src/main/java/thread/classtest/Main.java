package thread.classtest;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试结果表明，子类方法覆盖父类方法时，子类的方法访问权限必须大于等于父类。
 * 更小的访问权限是无法覆盖父类的
 * User: luolifeng
 * Date: 2019-02-15
 * Time: 16:57
 */
public class Main {
    public static void main(String[] args){
        new SubClass().test();
    }
}
