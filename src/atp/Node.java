package atp;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Node {
	
	private String name = "";
	private Set<String> labels = new LinkedHashSet<String>();
	private Map<String,String> properties = new LinkedHashMap<String,String>();
	
	public Node(String name) {
		this.name = name;
	}
	
	private Set<String> cloneLabels(Set<String> labels){
		Set<String> clone = new LinkedHashSet<String>();
		for(String each_label : labels) {
			String each_clone = new String(each_label);
			clone.add(each_clone);
		}
		return clone;
	}
	
	public void setLabels(Set<String> labels) {
		if(labels == null) {
			return;
		}
		Set<String> clone = this.cloneLabels(labels);
		this.labels = clone;
	}
	
	public void addLabel(String label) {
		if(label == null || label.length() == 0) {
			return;
		}
		this.labels.add(label);
	}
	
	public Set<String> getLabels(){
		Set<String> clone = this.cloneLabels(this.labels);
		return clone;
	}
	
	
 	
	

}
