package teamProject;

import java.util.ArrayList;
import java.util.List;

public class Card {
	private String cardName;
	private List<Integer> descriptions;
	
	public Card(String cardName,List<Integer> descriptions) {
		this.cardName = cardName;
		this.descriptions = descriptions;
	}
	
	public void setCardName(String name) {
		cardName = name;
	}
	
	public void setDescriptions(List<Integer> d) {
		descriptions = d;
	}
	public String getCardName(){
		return cardName;
	}
	public List<Integer> getedscriptions(){
		return descriptions;
	}
}
