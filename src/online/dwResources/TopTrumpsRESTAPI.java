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
	
	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
		
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
	@Path("/fuck")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void fuck(@QueryParam("Word") int i) throws IOException {
		get(i);
	}
	
	public void get(int i) {
		System.out.println(i);
	}
	
	
	@GET
	@Path("/roundStatus")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String roundStatus() throws IOException {
		return "Round "+roundTest()+": ";
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
	@Path("/data")
	public String dataG() {
		String []test =new String[5];
		String t1 = "name: player\npower: 100";
		String t2 = "name: ai\npower: 12";
		test[0]= t1;
		test[1]= t2;
		return 	arrayTrans(test);
	}
	
	
	
	
	@GET
	@Path("/pdata")
	public String pdataG() {
		String t1 = "name: player\npower: 100";
		return 	t1;
	}
	
	
	@GET
	@Path("/gameRole")
	
	public String gameRole() {
		System.out.println(roundTest);
		System.out.println(roundTest%2);
		if(roundTest%2==0) {
			return gameRoleTest[0];
		}else {
			return gameRoleTest[1]+roundTest;
		}
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
