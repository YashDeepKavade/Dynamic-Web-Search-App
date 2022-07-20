package in.ls.service;

import java.util.List;

import in.ls.bindigs.RequestPlan;
import in.ls.bindigs.ResponsePlan;

public interface InsurancePlanService {
			
		public List<ResponsePlan> getPlan(RequestPlan requestPlan);
		
		public List<String> getUniquePlanNames();
		
		public List<String> getUniquePlanStatus();
}
