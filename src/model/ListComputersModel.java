package model;

import java.util.ArrayList;
import java.util.List;

public class ListComputersModel {
	
	private List<ComputerModel> listComputers;
	
	public ListComputersModel() {
		listComputers = new ArrayList<ComputerModel>();
	}
	
	public void addComputer(ComputerModel computer) {
		listComputers.add(computer);
	}
	
	public void removeComputer(ComputerModel computer) {
		listComputers.remove(computer);
	}
	
}
