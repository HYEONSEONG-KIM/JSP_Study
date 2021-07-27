package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="lprodId")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LprodVO {

	private Integer lprodId;
	private String lprodGu;
	private String lprodNm;
	
}
