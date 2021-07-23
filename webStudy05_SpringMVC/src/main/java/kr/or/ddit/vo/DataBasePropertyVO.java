package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "propertyName")
@ToString
public class DataBasePropertyVO implements Serializable {
	private String propertyName;
	private String propertyValue;
	private String description;
	
	
}
