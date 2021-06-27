package demo.jvm;

import java.lang.reflect.Method;

public class TestMyClassloader {

	public static void main(String[] args) throws Exception {
		String filePath = "F:\\极客时间\\进阶训练营\\jvm进阶\\作业相关\\Hello.xlass";
        //使用自定义类加载器加载类
        MyClassLoader myClassLoader = new MyClassLoader(filePath);
        Class<?> clazz = myClassLoader.loadClass("Hello");

        //实例化对象
        Object obj = clazz.newInstance();
        //获取声明的方法
        Method method = clazz.getDeclaredMethod("hello");
        //方法调用
        method.invoke(obj);

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
	}
}
