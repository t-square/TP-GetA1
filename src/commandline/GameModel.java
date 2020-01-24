package commandline;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameModel {
	private List<Player> playerList = new ArrayList<>();
	private List<Card> cardList = new ArrayList<>();
	private String[] description;
	private String[] cardAttribute;
	private int numOfPlayer;
	private Card[] cardOnDeck;
	private String status;
	private int round = 0;
	private Card winCard;
	private Player roundWinner;
	private Player gameWinner;
	private List<Card> commonPile = new ArrayList<>();
	private Player activePlayer;
	private int numberOfDraws = 0;
	private int numberOfDrew = 0;

	//wei part
	private int activePlayerIndex;
	private String gameInfo;
	private int roundSelectIndex;
	private int gameIsOver = -1;

	
	
	public static void main(String[] args) {
		GameModel model = new GameModel(5);
		while(model.getGameIsOver()!=0) {
			model.decideActivePlayers();
			System.out.println(model.getGameInfo());
			model.draw();
			System.out.println(model.getStatus());
			System.out.println(model.getGameInfo());
			model.humanSelect(5);
			System.out.println(model.getStatus());
			System.out.println(model.getGameInfo());
			model.showWinner();
			model.gameIsOver();
			System.out.println(model.getStatus());

		}
	}
	

	public void initialiseGame(int num) {
	    playerList = new ArrayList<>();
		status = "";
		cardList = new ArrayList<>();
		round = 0;
		commonPile = new ArrayList<>();
		numberOfDraws = 0;
		cardOnDeck = null;
		winCard = null;
		gameWinner = null;
		activePlayer = null;
		activePlayerIndex=-1;
		roundSelectIndex=-1;
		gameInfo="";
		gameIsOver = -1;
		setNumOfPlayer(num);
		cardOnDeck= new Card[num];
		defaultPlayer();
		defaultCard();
		defaultSelectPlayer();
	}
	
	
	
	
	public GameModel(int num){
		initialiseGame(num);
	}

	

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
			String fN="C:\\TeamProject\\WorkSpace\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
			fr = new FileReader(fN);
		}catch(FileNotFoundException e) {
			e.printStackTrace();		
			}
		Scanner s = new Scanner(fr);
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(numLine ==0) {
				cardAttribute = line.split(" ");
				numLine++;
			}
			else {
				String[] card = line.split(" ");
				List<Integer> cardD = new ArrayList<>(); 
				
				for(int i=1;i<card.length;i++) {
					cardD.add(Integer.parseInt(card[i]));
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
			}		
			gameInfo = "The active player is "+activePlayer.getPlayerName()+ ".";
			
			status = roundString() +"Players have drawn their cards.";
		
			for(int i=0 ; i< 5 ; i++) {
				for(int j=0 ; j< 5 ;j++) {
					if(cardOnDeck[i]!=null) {
				System.out.print(cardOnDeck[i].getDescriptions().get(j)+", ");
					}else System.out.print("null");
				}
				System.out.println();
			}
	}
	
	
	
	
	//wei part
	
	public String roundString() {
		return "Round "+round+": ";
	}
	
	
	public void decideActivePlayers() {
		if(roundWinner==null) {
			Random r = new Random();
			activePlayer = playerList.get(r.nextInt(numOfPlayer));
			gameInfo = "The active player is "+activePlayer.getPlayerName()+ ".";
		}else {
			activePlayer = roundWinner;
		}
		if(activePlayer.equals(playerList.get(0))){
			status =roundString() + "waiting on you to select a category ~ ";
		}
		else {
		}
	}
	
	
	public void humanSelect(int num) {
		if(!activePlayer.equals(playerList.get(0))){
			AISelect();
		}else {
			roundSelectIndex = num;
			status = roundString() +"You selected " + cardAttribute[num] + ".";
		}
	}
	private void AISelect() {
		if(activePlayer.equals(playerList.get(0))){
			System.out.println("Error selecet!");
		}
		int activePlayerIndex = 0;
		int maxValue = 0;
		int bestChoice = -1;
		for(int i=1 ; i< playerList.size() ; i++) {
			if(activePlayer.equals(playerList.get(i)))
			{activePlayerIndex=i;}
		}
		System.out.println("a"+activePlayerIndex);
		for(int i=1;i< cardAttribute.length-1 ; i++) {
			int currentValue = cardOnDeck[activePlayerIndex].getDescriptions().get(i);
			if(currentValue>maxValue) {
				maxValue = currentValue;
				bestChoice = i;
			}
		}
		roundSelectIndex=bestChoice+1;
		status = roundString() +activePlayer.getPlayerName() +" selected " + cardAttribute[bestChoice] + ".";
	}


//	public void selectDescription() {
//			int maxValue = activePlayer.getCardList().get(0).getedscriptions().get(0);
//			desSelect = 0;
//			for(int i=1;i<description.length;i++) {
//				if(activePlayer.getCardList().get(0).getedscriptions().get(i)> maxValue) {
//					desSelect =i;
//				}
//			}
//		}
//		
//	}
	
	
	public void showWinner() {
		int winnerIndex = 0;
		int maxValue = 0;
		boolean drew = false;
	
		for(int i=0;i< cardOnDeck.length ; i++) {
			if(cardOnDeck[i]!=null){
				int currentValue = cardOnDeck[i].getDescriptions().get(roundSelectIndex-1);
				System.out.println(currentValue+" "+maxValue);
				if(currentValue>maxValue) {
					maxValue = currentValue;
					winnerIndex = i;
					drew = false;
				}else if(currentValue == maxValue) {
					drew = true;
				}
			}
		}
		
		winCard = cardOnDeck[winnerIndex];//For command mode
		
		if(drew) {
			for(int i=0;i<cardOnDeck.length;i++) {
				if(cardOnDeck[i]!=null){
					commonPile.add(cardOnDeck[i]);
				}

			}
			status = roundString()+"This round was a draw, common pile now has " +
			commonPile.size() + "cards.";
			roundWinner=activePlayer;
		}
		else {// has winner
			if(winnerIndex==0) {
				status = roundString()+"Congratulation, you won this round!!!";
				roundWinner = playerList.get(winnerIndex);
				
			}else {
				status = roundString()+"Hhhhhhh, "+ playerList.get(winnerIndex).getPlayerName() +" won this round.";
				roundWinner = playerList.get(winnerIndex);
			}
			activePlayer = roundWinner;
			if(!commonPile.isEmpty()) {
				for(int i=0; i<commonPile.size() ; i++) {
					playerList.get(winnerIndex).getCardList().add(commonPile.get(i));
				}
				commonPile.clear();
			}
			
			for(int i=0; i<cardOnDeck.length ; i++) {
				if(cardOnDeck[i]!=null){
					playerList.get(winnerIndex).getCardList().add(cardOnDeck[i]);
				}
			}
			
		}
	}
		

	public boolean gameIsOver() {
		int aliveNum = 0;
		int winnerIndex = -1;
		for(int i = 0 ; i<numOfPlayer;i++) {
			if(playerList.get(i).aliveJudge()) {
				aliveNum++;
				winnerIndex=i;
			}
		}
		if(aliveNum == 1) {
			if(winnerIndex==0) {
				status = roundString()+"Congratulation, you won this game!!!";
			}else{
				status = roundString()+"Hhhhhhh, "+ playerList.get(winnerIndex).getPlayerName() +" won the game.";
			}
			gameInfo = "Sorry, the game is over.";
			gameIsOver = 0;
			return true;
		}
		else if(aliveNum == 0 || !roundWinner.aliveJudge()){
			status = roundString()+"Hhhhhhh, someone won but now has no card bye!!!";	
			gameIsOver = 0;
			return true;
		}
		else {
			return false;
		}
	}
	public int getGameIsOver() {
		return gameIsOver;
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
	public String getGameInfo() {
		return gameInfo;
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
