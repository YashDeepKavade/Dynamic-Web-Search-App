package in.ls.bindigs;


import lombok.Data;

@Data
public class ResponsePlan {

	private Integer planId;
	
	private String planName;
	
	private String holderName;
	
	private Long holderSsn;
	
	private String planStatus;
}
