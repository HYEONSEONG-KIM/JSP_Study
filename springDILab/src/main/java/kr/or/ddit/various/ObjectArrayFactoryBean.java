package kr.or.ddit.various;

import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class ObjectArrayFactoryBean implements FactoryBean<Object[]>{

	@Override
	public Object[] getObject() throws Exception {
		return new Object[] {"arrayValue1", new Date()};
	}

	@Override
	public Class<?> getObjectType() {
		return Object[].class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
