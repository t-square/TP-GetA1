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
	private int roundWinnerIndex;
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
	private int finalWinnerIndex =-1;
	private int humanLose = -1;
	
	
	public static void main(String[] args) {
		GameModel model = new GameModel(5);
		while(model.getGameIsOver()!=0) {
			model.decideActivePlayers();
			model.draw();
			System.out.println(model.getGameInfo());
			System.out.println(model.getGameStatus());
			System.out.println(model.activePlayer.getPlayerName());
			if(model.humanIsActivePlayer()==0) {
				model.humanSelect(5);
			}else {
				model.AISelect();
			}
		
			
			for(int i=0;i<model.getCardStringOnDeck().length;i++) {
				if(model.getCardStringOnDeck()[i]!=null) {
					System.out.println(model.getCardStringOnDeck()[i]);
				}else {
					System.out.println("null");
				}
			}
			
			System.out.println(model.getGameStatus());
			System.out.println(model.getGameInfo());
			model.showWinner();

			model.gameIsOver();
			System.out.println(model.getGameStatus());

		}
		
		for(int i=0;i<model.getGameResult().length;i++) {
			System.out.println(model.getGameResult()[i]);
		}
		
	}

	public void initialiseGame(int num) {
		System.out.println("initialiseGame");
		System.out.println(status);
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
		roundSelectIndex=0;
		gameInfo="";
		gameIsOver = -1;
		setNumOfPlayer(num);
		cardOnDeck= new Card[num];
		defaultPlayer();
		defaultCard();
		decideActivePlayers();
		humanLose = -1;
	}
	
	
	public GameModel(int num){
		initialiseGame(num);
	}


	public void defaultPlayer() {
		for(int i=0;i<numOfPlayer;i++) {
			if (i == 0) {
				Player player = new Player("Player");
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
	
	
	public void draw() {
		Random r =new Random();
		System.out.println("draw!!");
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
			
	}
	
	
	public String roundString() {
		return "Round "+round+": ";
	}
	
	
	public void decideActivePlayers() {
		System.out.println("decideActivePlayers!!");
		if(roundWinner==null) {
			Random r = new Random();
			activePlayer = playerList.get(r.nextInt(numOfPlayer));
			gameInfo = "The active player is "+activePlayer.getPlayerName()+ ".";
		}else {
			activePlayer = roundWinner;
		}
	}
	
	public int humanIsActivePlayer() {
		System.out.println("humanIsActivePlayer!!");
		if(!activePlayer.equals(playerList.get(0))){
			status =roundString() + "Waiting on "+activePlayer.getPlayerName() +" to select a category ";
			return -1;
		}
		status =roundString() + "Waiting on you to select a category ~ ";
		System.out.println("!!!"+status);
		return 0;		
	}
	
	
	public void humanSelect(int num) {
		System.out.println("humanSelect");
		roundSelectIndex = num;
		status = roundString() +"You selected " + cardAttribute[num] + ".";
	}
	
	public void AISelect() {
		System.out.println("AISelect");
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
		for(int i=0;i< cardAttribute.length-1 ; i++) {
			int currentValue = cardOnDeck[activePlayerIndex].getDescriptions().get(i);

			if(currentValue>maxValue) {
				maxValue = currentValue;
				bestChoice = i;

			}
		}
		roundSelectIndex=bestChoice+1;
		status = roundString() +activePlayer.getPlayerName() +" selected " + cardAttribute[bestChoice+1] + ".";
	}


	
	
	public void showWinner() {
		System.out.println("showWinner");
		roundWinnerIndex = -1;
		int maxValue = 0;
		boolean drew = false;
	
		for(int i=0;i< cardOnDeck.length ; i++) {
			if(cardOnDeck[i]!=null){
				int currentValue = cardOnDeck[i].getDescriptions().get(roundSelectIndex-1);
				if(currentValue>maxValue) {
					maxValue = currentValue;
					roundWinnerIndex = i;
					drew = false;
				}else if(currentValue == maxValue) {
					roundWinnerIndex = i;
					drew = true;
				}
			}
		}
		
		winCard = cardOnDeck[roundWinnerIndex];//For command mode
		
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
			playerList.get(roundWinnerIndex).addWin();
			if(roundWinnerIndex==0) {
				status = roundString()+"Congratulation, you won this round!!!";
				roundWinner = playerList.get(roundWinnerIndex);
			}else {
				status = roundString()+"Hhhhhhh, "+ playerList.get(roundWinnerIndex).getPlayerName() +" won this round.";
				roundWinner = playerList.get(roundWinnerIndex);
			}
			activePlayer = roundWinner;
			if(!commonPile.isEmpty()) {
				for(int i=0; i<commonPile.size() ; i++) {
					playerList.get(roundWinnerIndex).getCardList().add(commonPile.get(i));
				}
				commonPile.clear();
			}
			
			for(int i=0; i<cardOnDeck.length ; i++) {
				if(cardOnDeck[i]!=null){
					playerList.get(roundWinnerIndex).getCardList().add(cardOnDeck[i]);
				}
			}
			
		}
		System.out.println(status);
	}
		

	public void gameIsOver() {
		int aliveNum = 0;
		int winnerIndex = -1;
		for(int i = 0 ; i<numOfPlayer;i++) {
			if(playerList.get(i).aliveJudge()) {
				aliveNum++;
				winnerIndex=i;
			}
		}
		System.out.println(aliveNum);
		if(aliveNum == 1) {
			if(winnerIndex == 0) {
				status = roundString()+"Congratulation, you won this game!!!";
				finalWinnerIndex=0;
			}else{
				status = roundString()+"Hhhhhhh, "+ playerList.get(winnerIndex).getPlayerName() +" won the game.";
				finalWinnerIndex=winnerIndex;
			}
			gameInfo = "Sorry, the game is over.";
			gameIsOver = 0;
		}
		else if(aliveNum == 0 || !roundWinner.aliveJudge()){
			status = roundString()+"Hhhhhhh, someone won but now has no card bye!!!";	
			gameIsOver = 0;
		}
		if(humanLose()&&humanLose != 0){
			humanLose = 0;
			System.out.println("humanFuck");
			autoPlay();
		}
		System.out.println("gameisover");
	}
	
//	
//	public void AIgameIsOver() {
//		int aliveNum = 0;
//		int winnerIndex = -1;
//		for(int i = 0 ; i<numOfPlayer;i++) {
//			if(playerList.get(i).aliveJudge()) {
//				aliveNum++;
//				winnerIndex=i;
//			}
//		}
//		System.out.println("alivenum"+aliveNum);
//		if(aliveNum == 1) {
//			if(winnerIndex == 0) {
//				status = roundString()+"Congratulation, you won this game!!!";
//				finalWinnerIndex=0;
//			}else{
//				status = roundString()+"Hhhhhhh, "+ playerList.get(winnerIndex).getPlayerName() +" won the game.";
//				finalWinnerIndex=winnerIndex;
//			}
//			gameInfo = "Sorry, the game is over.";
//			gameIsOver = 0;
//		}
//		else if(aliveNum == 0 || !roundWinner.aliveJudge()){
//			status = roundString()+"Hhhhhhh, someone won but now has no card bye!!!";	
//			gameIsOver = 0;
//		}
//	}
	
	
	public int getHumanLose() {
		return humanLose;
	}

	
	public boolean humanLose() {
		System.out.println(playerList.get(0).aliveJudge()+"isdead");
		System.out.println(playerList.get(0).getCardList().isEmpty()+"isempty");
		if(!playerList.get(0).aliveJudge()){
			status = roundString()+"Hhhhhhh, you are loser!!!";	
			return true;
		}
		return false;
	}
	
	public void autoPlay() {
		System.out.println("hi");
		while(this.getGameIsOver()!=0) {
			this.decideActivePlayers();
			this.draw();
			System.out.println(this.getGameInfo());
			System.out.println(this.getGameStatus());
			if(this.humanIsActivePlayer()==0) {
				this.humanSelect(5);
			}else {
				this.AISelect();
			}
			for(int i=0;i<this.getCardStringOnDeck().length;i++) {
				if(this.getCardStringOnDeck()[i]!=null) {
					System.out.println(this.getCardStringOnDeck()[i]);
				}else {
					System.out.println("null");
				}
			}
		
			System.out.println(this.getGameStatus());
			System.out.println(this.getGameInfo());
			this.showWinner();
			this.gameIsOver();
			System.out.println(this.getGameStatus());
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
	
	
	public String[] getCardStringOnDeck(){
		String[] s = new String[cardOnDeck.length];	
		String temString = "";
		for(int i=0 ; i< cardOnDeck.length ; i++) {
			if(cardOnDeck[i]!=null) {
				
				temString += playerList.get(i).getPlayerName()+"  ";
				temString += "Name:"+cardOnDeck[i].getCardName()+",";
				temString += "x"+(playerList.get(i).getNumOfCards()+1)+","; //plus the card on deck;
				for(int j=0 ; j< cardOnDeck.length ;j++) {
					if(j==cardOnDeck.length-1) {
						temString += cardAttribute[j+1]+": "+cardOnDeck[i].getDescriptions().get(cardOnDeck.length-1);
					}else
					{
						temString += cardAttribute[j+1]+": "+cardOnDeck[i].getDescriptions().get(j)+",";
					}
				}
				s[i] = temString;
				temString = "";
			}else {
				s[i]=null;
			}
		}
		return s;
	}
		
	public String[] getGameResult(){
		ArrayList <String> s = new ArrayList <String>();	
		if(finalWinnerIndex==-1) {
			s.add("Hhhhh , no one won!!!");
		}
		else if(finalWinnerIndex==0) {
			s.add("Congratulaton, the winner is you!!!");
			s.add("you won " + playerList.get(0).getWinTimes()+" rounds.");
		}
		else {
			s.add("Hhhhh look at you, the winner is:");
			s.add(playerList.get(finalWinnerIndex).getPlayerName()+" won " + playerList.get(finalWinnerIndex).getWinTimes()+" rounds.");
		}
		s.add("The poor losers: ");
		for(int i=0 ; i< playerList.size() ; i++) {
			if(finalWinnerIndex==i) {
				
			}else {
				s.add(playerList.get(i).getPlayerName()+" won " + playerList.get(i).getWinTimes()+ " rounds.");
			}
		}
		String[] strr = s.toArray(new String[s.size()]); 
		return strr;
	}	
		
	
	public int getRound() {
		return round;
	}
	public String getGameStatus() {
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
	
	public int getRoundWinnerIndex() {
		return roundWinnerIndex;
	}
	public boolean getHumanIsActivePlayer() {
		if(activePlayer.equals(playerList.get(0))) {
			return true;
		}
		else {
			return false;
		}
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
