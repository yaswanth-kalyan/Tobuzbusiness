package beans;

import models.State;

public class StateBean {
	
	public Long stateId;
	
	public String name;
	
	
	public static StateBean toStateBean(State state) {
		StateBean bean = new StateBean();
		bean.name = state.getName();
		bean.stateId = state.getId();
		return bean;
	}
	

}
