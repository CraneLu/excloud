/**
 * 饿汉单例（初始化就加载）
 */
public class HungrySingleton {

    private HungrySingleton(){}

    private static HungrySingleton instance =
            new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args){
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance1==instance2);
    }

}
