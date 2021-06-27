package demo.jvm;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{
	public static final byte DIGITAL_255 = (byte) 255;
    private String filePath;

    public MyClassLoader(String filePath) {
        this.filePath = filePath;
    }

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		 try {
	            byte[] bytes = getClassBytes(filePath);
	            // 根据要求处理字节码
	            byte[] deBytes = handleByte(bytes);
	            // 使用新的字节数组定义类
	            return defineClass(name, deBytes, 0, bytes.length);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return super.findClass(name);
	}
	
	/**
     * 根据自定义的要求处理字节码
     *
     * @param oldBytes 原来的字节数组
     * @return 放回处理后的数组
     */
    private byte[] handleByte(byte[] oldBytes) {
        byte[] newBytes = new byte[oldBytes.length];
        for (int i = 0; i < oldBytes.length; i++) {
            newBytes[i] = (byte) (DIGITAL_255 - oldBytes[i]);
        }
        return newBytes;
    }

    /**
     * 获取字节数组
     * @param filePath class文件路径
     * @return 字节数组
     * @throws Exception
     */
    private byte[] getClassBytes(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

	
}
