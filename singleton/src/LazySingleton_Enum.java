/**
 * 懒汉单例（Enum方式）
 * 1、由于枚举的实例都是static final的，只能实例化一次，因此保证了枚举实例只有一个，
 *    从而保证了枚举构造器中创建的目标实例也只有一个
 *
 * 优点：
 * 1、由于Enum类实现了Serializable接口，在传输单例对象的时候可以对序列化提供帮助
 *      public abstract class Enum<E extends Enum<E>>
 *          implements Comparable<E>, Serializable
 * 2、由于枚举的实例都是static final的，不会出现其他实现方式中的问题（利用反射可以强制调用私有构造器实例化）
 */
public class LazySingleton_Enum {

    private LazySingleton_Enum(){}
    private static LazySingleton_Enum getInstance(){
        return EnumSingleton.INSTENCE.getInstance();
    }

    private enum EnumSingleton {
        INSTENCE;
        private LazySingleton_Enum instance;
        EnumSingleton() {
            instance = new LazySingleton_Enum();
        }
        public LazySingleton_Enum getInstance(){
            return instance;
        }
    }

    public static void main(String[] args){
        LazySingleton_Enum instance1 = LazySingleton_Enum.getInstance();
        LazySingleton_Enum instance2 = LazySingleton_Enum.getInstance();
        System.out.println(instance1==instance2);
    }

}
