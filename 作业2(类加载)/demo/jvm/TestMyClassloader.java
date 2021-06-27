package demo.jvm;

import java.lang.reflect.Method;

public class TestMyClassloader {

	public static void main(String[] args) throws Exception {
		String filePath = "F:\\����ʱ��\\����ѵ��Ӫ\\jvm����\\��ҵ���\\Hello.xlass";
        //ʹ���Զ����������������
        MyClassLoader myClassLoader = new MyClassLoader(filePath);
        Class<?> clazz = myClassLoader.loadClass("Hello");

        //ʵ��������
        Object obj = clazz.newInstance();
        //��ȡ�����ķ���
        Method method = clazz.getDeclaredMethod("hello");
        //��������
        method.invoke(obj);

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
	}
}
