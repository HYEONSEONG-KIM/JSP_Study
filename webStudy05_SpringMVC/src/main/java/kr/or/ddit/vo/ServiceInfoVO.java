package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceInfoVO implements Serializable{

	// 요소가 여러개 일때 하나로
	/* 없을때
			 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
		<serviceInfo>
		    <menuList>
		        <code>STANDARD</code>
		        <link>/02/standard.jsp</link>
		    </menuList>
		    <menuList>
		        <code>STANDARD</code>
		        <link>/02/standard.jsp</link>
		    </menuList>
		</serviceInfo>
	 * 
	 */
	@XmlElementWrapper
	@XmlElementRefs(@XmlElementRef)
	private List<MenuVO> menuList;
	
	@Override
	public String toString() {
		return "ServiceInfoVO [menuList=" + menuList + "]";
	}



	public List<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVO> menuList) {
		this.menuList = menuList;
	}
	
	
}
