package kr.or.ddit.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operatorInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperatorInfoVO {
	
	@XmlElementWrapper
	@XmlElementRefs(@XmlElementRef)
	private List<OperatorVO> operatorList;

	public List<OperatorVO> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<OperatorVO> operatorList) {
		this.operatorList = operatorList;
	}

	@Override
	public String toString() {
		return "OperatorInfoVO [operatorList=" + operatorList + "]";
	}
	
	
	
}
