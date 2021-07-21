package kr.or.ddit.various;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Slf4j
public class CollectionDIVO {
	private List<String> sampleList;
	private Set<Object> sampleSet;
	private Map<String, Object> sampleMap;
	private Properties sampleProps;
	
	private Object[] sampleArray;
	
	public void init() {
		log.info("주입 상태 : {}", this);
	}
}
