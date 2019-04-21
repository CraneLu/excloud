/**
 * 懒汉单例（内部类方式）
 * 1、构建静态内部类，并在内部类中创建目标实例
 * 2、利用类加载机制，只要没用到内部类，JVM就不会加载目标实例，保证了懒汉单例的延时加载和线程安全
 *
 *  缺点：
 *  1、可以用反射机制强行执行私有的构造器
 *  2、需要额外进行序列化处理
 *
 */
public class LazySingleton_InnerClass {

    private static class SingletonHolder {
        public static LazySingleton_InnerClass instance =
                new LazySingleton_InnerClass();
    }

    private LazySingleton_InnerClass() { }

    public static LazySingleton_InnerClass getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args){
        LazySingleton_InnerClass instance1 = LazySingleton_InnerClass.getInstance();
        LazySingleton_InnerClass instance2 = LazySingleton_InnerClass.getInstance();
        System.out.println(instance1==instance2);
    }

}
