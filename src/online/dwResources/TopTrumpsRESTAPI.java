package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.GameModel;

import java.util.*;
@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	GameModel model ;

	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		model = new GameModel(5);
		
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/viewSelectAtt")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void viewSelectAtt(@QueryParam("Word") int i) throws IOException {
		get(i);
	}
	
	public void get(int i) {
		System.out.println(i);
	}
	
	
	@GET
	@Path("/updataViewGameStatus")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String GameStatus() throws IOException {
		String gameS = "Round "+roundTest()+": Please finish model and database!!!";
		String selectS = "They select speed";
		String[] s = new String[2];
		s[0]=gameS;
		s[1]=selectS;
		
		return arrayTrans(s);
	}
		
	@GET
	@Path("/categorySelect")
	public void categorySelect() {
	    update();
	}
	

	int roundTest = 0;
	String [] gameRoleTest = {"The active player is you","The active player is AI"}; 
	public String roundTest() {
		roundTest ++;
		return Integer.toString(roundTest);
		
	}
	public void update() {
		roundTest();
	}
	

	
	@GET
	@Path("/gameRole")
	
	public String gameRole() {

		System.out.println(roundTest%2);
		if(roundTest%2==0) {
			return gameRoleTest[0];
		}else {
			return gameRoleTest[1]+roundTest;
		}
	}
	
	@GET
	@Path("/updataViewGameOver")
	public boolean updataViewGameOver() {
		if(model.getGameIsOver()%2==0) {

			return true;
		}
		return false;
	}
	
	
	
	@GET
	@Path("/updateViewActivePlayers")
	public void updateViewActivePlayers() {
		model.decideActivePlayers();
	}
	
	@GET
	@Path("/updateViewGameInfo")
	public String updateViewGameInfo() {
		return model.getGameInfo();
	}
	
	@GET
	@Path("/updateViewGameInformPackage")
	public String updateViewGameInformPackage() {
		String [] s = new String[2];
		s[0]= model.getGameStatus();
		s[1]= model.getGameInfo();
		return arrayTrans(s);
	}
	
	
	
	
	@GET
	@Path("/requestAISelect")
	public void requestAISelect() {
		model.AISelect();
	}
	
	
	
	@GET
	@Path("/updateHumanIsActivePlayer")
	public int updateViewHumanIsActivePlayer() {
		return model.humanIsActivePlayer();
	}
	
	@GET
	@Path("/askViewHumanSelect")
	public void askViewHumanSelect(@QueryParam("Word") int num) {
		 model.humanSelect(num);
	}
	

	@GET
	@Path("/updateViewGameIsOver")
	public int updateViewGameIsOver() {
		System.out.print(model.getHumanLose()+"yooooooo");
		if(model.getHumanLose()==0) return model.getHumanLose();
		return model.getGameIsOver();
	}
	
	@GET
	@Path("/updataViewPlayerResult")
	public String updataViewPlayerResult() {
		return arrayTrans(model.getGameResult());
	}
	
	
	
	@GET
	@Path("/updateViewCardSOnDeck")
	public String updateViewCardOnDeck() {
		return arrayTrans(model.getCardStringOnDeck());
		
	}
	
	
	@GET
	@Path("/updateViewHumanCardOnDeck")
	public String updateViewHumanCardOnDeck() {
		System.out.print(model.getCardStringOnDeck()[0]);
		return model.getCardStringOnDeck()[0];
	}
	
	int cmd = 0;
	@GET
	@Path("/webMaster")
	public int webMaster() {
		return cmd;	
	}
	
	
	
	@GET
	@Path("/userRequestGameInitialised")
	public void userRequestGameInitialised() {
		model.initialiseGame(5);
		model.decideActivePlayers();
		model.draw();
		setViewActivePlayer();
	}
	
	
	
	
	
	
	@GET
	@Path("/userPressNewTurn")
	public void userPressNewTurn() {
		model.decideActivePlayers();
		model.draw();
		setViewActivePlayer();
	}
	
	
	public void setViewActivePlayer() {
		if(model.getHumanIsActivePlayer()) {
			cmd = 2;
		}else {
			cmd = 4;
		}
	}
	
	
	
	@GET
	@Path("/userPressSelect")
	public void userPressSelect() {
		if(model.getHumanIsActivePlayer()) {
			cmd = 3;
		}else {
			model.AISelect();
			cmd = 5;
		}
	}
	
	
	
	@GET
	@Path("/userSelect")
	public void userSelect(@QueryParam("Word") int num) {
		 model.humanSelect(num);
		 cmd = 5;
	}
	
	@GET
	@Path("/userPressShowWinner")
	public void userPressShowWinner() {
		model.showWinner();
		model.gameIsOver();
		if(model.getHumanLose()==0||model.getGameIsOver()==0){
			cmd = 7;
		}
		else {
			cmd = 6;
		}
	}
	
	@GET
	@Path("/updateRoundWinner")
	public int updateRoundWinner() {
		return model.getRoundWinnerIndex();
	}
	public String arrayTrans(String[] in) {
		String s = "";
		s+=in[0]+"|?|";
		for(int i = 1; i<in.length-1;i++) {
			s+=in[i]+"|?|";
		}
		s+=in[in.length-1];
		return s;
	}
	
}
