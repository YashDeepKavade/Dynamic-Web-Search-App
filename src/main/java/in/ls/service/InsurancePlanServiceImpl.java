package in.ls.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ls.bindigs.RequestPlan;
import in.ls.bindigs.ResponsePlan;
import in.ls.entity.InsurancePlanEntity;
import in.ls.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService{
	
	@Autowired
	private InsurancePlanRepository repo;
	
	@Override
	public List<ResponsePlan> getPlan(RequestPlan requestPlan) {
		
		InsurancePlanEntity entity=new InsurancePlanEntity();
		
		if(requestPlan!=null && requestPlan.getPlanName()!=null && !requestPlan.getPlanName().equals("")) {
			entity.setPlanName(requestPlan.getPlanName());
		}
		
		if(requestPlan!=null && requestPlan.getPlanStatus() !=null && !requestPlan.getPlanStatus().equals("")) {
			entity.setPlanStatus(requestPlan.getPlanStatus());
		}
		
		
		Example<InsurancePlanEntity> of = Example.of(entity);
		List<InsurancePlanEntity> findAll = repo.findAll(of);
		List<ResponsePlan> resPlan=new ArrayList<>();
		for(InsurancePlanEntity plan:findAll) {
			ResponsePlan respln = new ResponsePlan();
			BeanUtils.copyProperties(plan, respln);
			resPlan.add(respln);
		}
		
	
		return resPlan;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return repo.getPlanStatus();
	}

}
