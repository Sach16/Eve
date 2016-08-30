package eve.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class FoodMenu {
	
	@Id
	private String id;
	private Integer cost;
	private String cuisine;
	private String name;
	private List<Attachments> attachments = new ArrayList<Attachments>();
	
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Attachments> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}
	
	
}
