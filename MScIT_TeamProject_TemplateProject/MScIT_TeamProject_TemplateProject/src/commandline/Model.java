package teamProject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Model {
	private List<Player> playerList = new ArrayList<>();
	private List<Card> cardList = new ArrayList<>();
	private String[] description;
	private int numOfPlayer;
	private Card[] cardOnDeck = new Card[numOfPlayer];
	private String status;
	private int round = 0;
	private Card winCard;
	private Player roundWinner;
	private List<Card> commonPile = new ArrayList<>();
	private Player activePlayer;
	private int desSelect;
	private int numberOfDraws = 0;
	
	public void defaultPlayer() {
		for(int i=0;i<numOfPlayer;i++) {
			if (i == 0) {
				Player player = new Player("YOU");
				playerList.add(player);
			}
			else {
				Player player = new Player("AI Player"+i);
				playerList.add(player);
			}
		}
	}
	public void readCard() {
		FileReader fr = null;
		int numLine = 0;
		
		try {
			String fN="C:\\Users\\PO\\Desktop\\team project\\workspace\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
			fr = new FileReader(fN);
		}catch(FileNotFoundException e) {
			e.printStackTrace();		
			}
		Scanner s = new Scanner(fr);
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(numLine ==0) {
				description = line.split(" ");
				numLine++;
			}
			else {
				String[] card = line.split(" ");
				List<Integer> cardD = new ArrayList<>(); 
				
				for(int i=0;i<cardD.size();i++) {
					cardD.add(Integer.parseInt(card[i+1]));
				}
				Card cardArray = new Card(card[0],cardD);
				cardList.add(cardArray);
			}
		}
	}
	
	public  void defaultCard() {
		readCard();
		Collections.shuffle(cardList);
		while(!cardList.isEmpty()) {
			for(int i=0;i<numOfPlayer && !cardList.isEmpty();i++) {
				playerList.get(i).getCardList().add(cardList.get(0));
				cardList.remove(cardList.get(0));  
			}
		}
	}
	
	public void defaultSelectPlayer() {
		Random r = new Random();
		activePlayer = playerList.get(r.nextInt(numOfPlayer));
	}
	
	public void draw() {
		Random r =new Random();
		round ++;
		
		for(int i=0;i<numOfPlayer;i++) {
			if(playerList.get(i).aliveJudge()) {
				int number = r.nextInt(playerList.get(i).getCardList().size());
				cardOnDeck[i] = playerList.get(i).getCardList().get(number);
				playerList.get(i).getCardList().remove(number);
			}else {
				cardOnDeck[i] = null;
			}
//		status = "Round "+round+": Players have drawn their cards.";
		}		
	}
	public void selectDscription() {
		if(activePlayer == playerList.get(0)) {
			Scanner s =new Scanner(System.in);
			System.out.println("Please select the description (0-"+(description.length-1)+")");
			desSelect =s.nextInt();
		}else {
			int maxValue = activePlayer.getCardList().get(0).getedscriptions().get(0);
			desSelect = 0;
			for(int i=1;i<description.length;i++) {
				if(activePlayer.getCardList().get(0).getedscriptions().get(i)> maxValue) {
					desSelect =i;
				}
			}
		}
		
	}
	public void cardCompare() {
//		status = "Round "+round+": "+activePlayer.getPlayerName()+" selected "+description[desSelect];
		int pos = 0;
		int max = 0;
		
		for(int i=0;i<numOfPlayer && cardOnDeck[i] != null;i++) {
			int value = cardOnDeck[i].getedscriptions().get(desSelect);
			if(value>max) {
				pos = i;
				max = value;
			}else if (value == max) {
				for(int p=0;p<cardOnDeck.length;p++) {
					commonPile.add(cardOnDeck[p]);
					}
				numberOfDraws++;
				draw();
				cardCompare();
			}
		}
		winCard = cardOnDeck[pos];
		roundWinner = playerList.get(pos);
		roundWinner.addWin();
		activePlayer = roundWinner;
//		status = "";
		
		for(int j=0;j<cardOnDeck.length;j++) {
		playerList.get(pos).getCardList().add(cardOnDeck[j]);
		}
	}
	public boolean checkGanmeOver() {
		int aliveNum = 0;
		for(int i=0;i<numOfPlayer;i++) {
			if(playerList.get(i).aliveJudge()) {
				aliveNum++;
			}
		}
		if(aliveNum == 1) return true;
		else return false;
	}
	public Player Winner() {
		int pos = 0;
		for(int i=0;i<numOfPlayer;i++) {
			if(playerList.get(i).aliveJudge()) {
				pos = i; 
				break;
			}
		}
			return playerList.get(pos);
	}
	public void Reset() {
	    playerList = new ArrayList<>();;
		status = "";
		cardList = new ArrayList<>();
		round = 0;
		commonPile = new ArrayList<>();
		numberOfDraws = 0;
	}
	public void setNumOfPlayer(int number) {
		numOfPlayer = number;
	}
	public int getNumOfPlayer() {
		return numOfPlayer;
	}
	public List<Player> getPlayerList(){
		return playerList;
	}
	public String[] getDescription() {
		return description;
	}
	public Card[] getCardOnDeck(){
		return cardOnDeck;
	}
	public int getRound() {
		return round;
	}
	public String getStatus() {
		return status;
	}
	
	public Card getWinCard() {
		return winCard;
	}
	public Player getRoundWinner() {
		return roundWinner;
	}
	public List<Card> getCommonPile(){
		return commonPile;
	}
	public int getNumberOfDraws() {
		return numberOfDraws;
	}
	public Player getActivePlayer() {
		return activePlayer;
	}
}
