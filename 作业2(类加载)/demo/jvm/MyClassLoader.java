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
	            // ����Ҫ�����ֽ���
	            byte[] deBytes = handleByte(bytes);
	            // ʹ���µ��ֽ����鶨����
	            return defineClass(name, deBytes, 0, bytes.length);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return super.findClass(name);
	}
	
	/**
     * �����Զ����Ҫ�����ֽ���
     *
     * @param oldBytes ԭ�����ֽ�����
     * @return �Żش���������
     */
    private byte[] handleByte(byte[] oldBytes) {
        byte[] newBytes = new byte[oldBytes.length];
        for (int i = 0; i < oldBytes.length; i++) {
            newBytes[i] = (byte) (DIGITAL_255 - oldBytes[i]);
        }
        return newBytes;
    }

    /**
     * ��ȡ�ֽ�����
     * @param filePath class�ļ�·��
     * @return �ֽ�����
     * @throws Exception
     */
    private byte[] getClassBytes(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

	
}
