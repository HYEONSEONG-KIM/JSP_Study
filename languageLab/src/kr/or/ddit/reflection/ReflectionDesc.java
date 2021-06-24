package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;
import kr.or.ddit.vo.MemberVO;

public class ReflectionDesc {
	
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		Class type =  obj.getClass();
		System.out.println(type);
		Field[] fields = type.getDeclaredFields();
		for(Field fld : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fld.getName(), type);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				System.out.printf("%s = %s\n", pd.getName(), getter.invoke(obj));
				
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void reflect1(Object obj) {
		Class type =  obj.getClass();
		System.out.println(type);
		
		Field[] fields = type.getDeclaredFields();
		
		for(Field fld : fields) {
//			fld.setAccessible(true);
			String fldName = fld.getName();
			String getterName ="get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1);
			
			
			
			try {
				Method getter =  type.getMethod(getterName);
				System.out.printf("%s = %s\n",fldName , getter.invoke(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
