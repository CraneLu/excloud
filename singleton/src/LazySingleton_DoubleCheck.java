/**
 * 懒汉单例（双重校验锁+volatile的方式）
 * 1、私有构造函数，禁止其他类创建实例
 * 2、私有静态成员变量，加volatile关键字（java1.5及以后版本才有），防止在多线程环境下的指令重排，保证可见性
 * 3、在第一次Check之后在加同步锁synchronized，提高访问效率
 * 4、在同步代码块执行前进行Double Check，保证多行程环境下不会重复创建实例
 *
 * 缺点：
 * 1、可以通过反射强行调用私有的构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）
 * 2、需要额外的工作来实现序列化，否则每次反序列化一个已序列化的对象时都会创建一个新的实例
 */
public class LazySingleton_DoubleCheck {

    private static volatile LazySingleton_DoubleCheck instance = null;

    private LazySingleton_DoubleCheck() {}

    public static LazySingleton_DoubleCheck getInstance() {
        if(instance == null){
            synchronized (LazySingleton_DoubleCheck.class){
                if(instance == null){
                    instance = new LazySingleton_DoubleCheck();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args){
        LazySingleton_DoubleCheck instance1 = LazySingleton_DoubleCheck.getInstance();
        LazySingleton_DoubleCheck instance2 = LazySingleton_DoubleCheck.getInstance();
        System.out.println(instance1==instance2);
    }
}
