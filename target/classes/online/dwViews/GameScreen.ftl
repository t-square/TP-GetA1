<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload=initalize()> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
						 		


			
			<div class="container mt-6 mb-6" id="startPanel">	
				<div class="pricing-header px-1 pt-5 md-1 pb-4 mx-auto text-center">
					<h1 class="display-2"></h1>
				</div>
				<div class="pricing-header px-1 pt-5 md-1 pb-2 mx-auto text-center">
					<h1 class="display-2">Are you ready?</h1>
				</div>

				<div class="pricing-header px-1 pb-3 pt-md-1 md-1 mx-auto text-center">
					<p class="lead">Hello dear player</p>
				</div>
				
			
				<div class="row mt-3 justify-content-center" >
					<div class="col-3 mt-3  pt-3 py-0">
						<button class="btn btn-outline-dark btn-block btn-lg mt-3 mb-0" onclick="startGame()">Start</button>
					</div>
				</div>
			</div>

			<div class="container"  id="gamePanel">

			<div class="pricing-header px-1 pt-3 md-1 pb-md-1 mx-auto text-center">
			<h1 class="display-5">Top Trumps Game</h1>
			</div>
			
			<div class="pricing-header px-1 pb-3 pt-md-1 md-1 mx-auto text-center">
			<p id = gameStatus class="lead"></p>
			</div>


			<div class="row ">
				<div class="col col-xl-3 mb-5 ml-5  pl-5 mr-3 pr-3">
					<div class="card  mb-4 " style="width: 12rem;">
						<div class="card-header ">
							<h6 class="my-0 py-0 px-0 font-weight-normal text-left" id="gameRole"></h6>
						</div>
						<div  class="card-body text-center">

							<div id="selectStatus" class=" mb-3">
								<h4></h4>
							</div>
							<div id="showWinner">
								<button class="btn btn-outline-dark btn-lg  mb-0" onclick="winnerStage()">Show winner</button>
							</div>

							<div id="nextRound">
								<button class="btn btn-outline-dark btn-lg mt-3 mb-0" onclick="nextRound()">Next round</button>
							</div>


							<div id="humanselectButton">
								<button class="btn btn-outline-dark btn-lg mt-3 mb-0" onclick="humanActived()">Select</button>
							</div>

							<div id="AISelectButton">
								<button class="btn btn-outline-dark btn-lg mt-3 mb-0" onclick="showAISelection()">Show AI's selection..</button>
							</div>
							

							<div id = selectList class="list-group">
								<li class="list-group-item list-group-item-action list-group-item-light" onclick="selectAttr1()">Size</li>
								<li class="list-group-item list-group-item-action list-group-item-light" onclick="selectAttr2()">Speed</li>
								<li class="list-group-item list-group-item-action list-group-item-light" onclick="selectAttr3()">Range</li>
								<li class="list-group-item list-group-item-action list-group-item-light" onclick="selectAttr4()">Firepower</li>
								<li class="list-group-item list-group-item-action list-group-item-light" onclick="selectAttr5()">Cargo</li>
							</div>
							
							<div >
								<ul id = "playerResult" class=" list-unstyled text-left mt-0 mb-4 px-3">
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
									<h6 class= "result"></h6>
								</ul>
							</div>

							<div id="returnToMenu">
								<a href=http://localhost:7777/toptrumps><button class="btn btn-outline-dark btn-lg mb-0">Retrun to menu</button></a>
							</div>

							
					</div>	
					</div>	
					<div class="row mt-3">
						<div class="col-md-auto mb-3">
							<a href="http://localhost:7777/toptrumps/">
								<h5>
									<span class="badge badge-warning">Let me leave</span>
								</h5>
							</a>
						</div>
					</div>
				</div>
				<div class="col-md-auto mb-3">
					<div id="card1" class="card mb-4 shadow-sm" style="width: 10rem;">
						<div class="card-header">
							<div class="row">
							<div class="col-auto mx-0 pr-0">
								<h5 class="my-0 font-weight-normal">You</h5>
							</div>
							<div class="col-auto mx-0 pl-1">
								<span id = numCards1 class="badge badge-warning"></span>
							</div>
							</div>
						</div>
						<div  class="card-body mt-1 pt-2">
								<h5 id = "cardName1"></h5>
							<div id = "cardContent1" class=" list-unstyled mt-0 mb-4">
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-auto mb-3">
					<div id="card2" class="card mb-4 shadow-sm" style="width: 10rem;">
						<div class="card-header">
							<div class="row">
							<div class="col-auto mx-0 pr-0">
								<h5 class="my-0 font-weight-normal">AI Player1</h5>
							</div>
							<div class="col-auto mx-0 pl-1">
								<span id = numCards2 class="badge badge-warning"></span>
							</div>
							</div>
						</div>
						<div  class="card-body mt-1 pt-2">
								<h5 id = "cardName2"></h5>
							<div id = "cardContent2" class=" list-unstyled mt-0 mb-4">
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-auto mb-3">
					<div id="card3" class="card mb-4 shadow-sm" style="width: 10rem;">
						<div class="card-header">
							<div class="row">
							<div class="col-auto mx-0 pr-0">
								<h5 class="my-0 font-weight-normal">AI Player2</h5>
							</div>
							<div class="col-auto mx-0 pl-1">
								<span id = numCards3 class="badge badge-warning"></span>
							</div>
							</div>
						</div>
						<div  class="card-body mt-1 pt-2">
								<h5 id = "cardName3"></h5>
							<div id = "cardContent3" class=" list-unstyled mt-0 mb-4">
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
								<h6 class= "attr"></h6>
							</div>
						</div>
					</div>
				</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-auto mb-3">
						<div id="card4" class="card mb-4 shadow-sm" style="width: 10rem;">
							<div class="card-header">
								<div class="row">
								<div class="col-auto mx-0 pr-0">
									<h5 class="my-0 font-weight-normal">AI Player3</h5>
								</div>
								<div class="col-auto mx-0 pl-1">
									<span id = numCards4 class="badge badge-warning"></span>
								</div>
								</div>
							</div>
							<div  class="card-body mt-1 pt-2">
									<h5 id = "cardName4"></h5>
								<div id = "cardContent4" class=" list-unstyled mt-0 mb-4">
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-auto mb-3">
						<div id="card5" class="card mb-4 shadow-sm" style="width: 10rem;">
							<div class="card-header">
								<div class="row">
								<div class="col-auto mx-0 pr-0">
									<h5 class="my-0 font-weight-normal">AI Player4</h5>
								</div>
								<div class="col-auto mx-0 pl-1">
									<span id = numCards5 class="badge badge-warning"></span>
								</div>
								</div>
							</div>
							<div  class="card-body mt-1 pt-2">
									<h5 id = "cardName5"></h5>
								<div id = "cardContent5" class=" list-unstyled mt-0 mb-4">
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
									<h6 class= "attr"></h6>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<footer class="pt-4 my-md-5 pt-md-5 border-top">
			
			</footer>
			</div>
		</body>

	<script type="text/javascript">
			var numPlayer = 5;
			// Method that is called on page load
			function initalize() {
				requestGameInitialised();
				document.getElementById("gamePanel").style.display = 'none';
				clear();
				document.getElementById("startPanel").style.display = 'block';
			}

			function startGame() {
				document.getElementById("gamePanel").style.display = 'block';
				requestDraw();
			}
			
			function drawStage() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				// For example, lets call our sample methods

				requestDraw();
			}
			
			function selectStage(){			
				getGameInfoPackage();
				HumanIsActivePlayer();
				getHumanCardOnDeck();

			}


			function winnerStage(){
				requestWinner();
			}


			function humanActived(){
				clear();
				document.getElementById("card1").style.display = 'block';
				document.getElementById("selectList").style.display = 'block';
				
			}






			function showAISelection(){
				requestAISelect();
				document.getElementById("AISelectButton").style.display  = 'none';
				document.getElementById("showWinner").style.display  = 'block';
				getCardOnDeck();
			}

		

			function nextRound(){
				requestDraw();
			}


			function clear(){
				document.getElementById("startPanel").style.display = 'none';
				document.getElementById("showWinner").style.display = 'none';
				document.getElementById("nextRound").style.display = 'none';
				document.getElementById("humanselectButton").style.display = 'none';
				document.getElementById("AISelectButton").style.display = 'none';
				document.getElementById("selectList").style.display = 'none';
				document.getElementById("selectStatus").style.display = 'none';
				document.getElementById("returnToMenu").style.display = 'none';

				clearCard();
			}

			function clearCard(){
				for(i = 0 ; i<numPlayer; i++){
					cardI = "card"+(1+i);
					  document.getElementById(cardI).style.display = 'none';
					  document.getElementById(cardI).style.borderColor = "";
					document.getElementById(cardI).style.borderWidth = "";  
				}
			}



			function selectAttr1(){
				sendPlayerSelect("1");
			}
			
			function selectAttr2(){
				sendPlayerSelect("2");
			}

			function selectAttr3(){
				sendPlayerSelect("3");
			}

			function selectAttr4(){
				sendPlayerSelect("4");
			}

			function selectAttr5(){
				sendPlayerSelect("5");
			}

			function playerSelect(num){
				sendPlayerSelect(num);
				
			}

			function decodeString(s){

				var strr = new Array();
				strr = s.split(',');
				return strr;

			}

			function setUpElements(s, targetID, targetCl){
				for(j=0 ; j<s.length ; j++){
					document.getElementById(targetID).getElementsByClassName(targetCl)[j].innerHTML=s[j];
				}
			}

			function setUpCard(s,n){
				var name = new Array();
				var num = new Array();
				var attr =new Array();
				var cardNameI= "cardName"+(n+1);
				var numCardI= "numCards"+(n+1);
				var cardCI= "cardContent"+(n+1);
				name =s[0].split('Name:');
				num	= s[1];
				document.getElementById(cardNameI).innerHTML = name[1];
				document.getElementById(numCardI).innerHTML = num;
				for(j=2 ; j<s.length; j++){
					document.getElementById(cardCI).getElementsByClassName("attr")[j-2].innerHTML=s[j];
				}
			}

			function javaArrayDecode(input){
					var strr = new Array();
					strr = input.split('|?|');
					return strr;
			}
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
			var a =1;
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			

			function sendPlayerSelect(num) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/askViewHumanSelect?Word="+num); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {	
					document.getElementById("selectList").style.display = 'none';
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
				getCardOnDeck();
				getGameInfoPackage();
				document.getElementById("showWinner").style.display  = 'block';
			}


		
			function getGameOver(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateViewGameIsOver"); // Request type and URL+parameters
					if (!xhr) {
						alert("CORS not supported");
					}
					xhr.onload = function(e) {
						if(xhr.response == 0 || xhr.response == 1){
							console.log(xhr.response+ "!!!!!");
							document.getElementById("showWinner").style.display  = 'none';
							document.getElementById("nextRound").style.display  = 'none';
							getPlayerResult();
						}else{
							console.log(xhr.response+ "gameisnoover");
							document.getElementById("showWinner").style.display  = 'none';
							document.getElementById("nextRound").style.display  = 'block';
						}
					}
				xhr.send();

			}


			
				
			function getPlayerResult(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updataViewPlayerResult"); // Request type and URL+parameters
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					let responseText = xhr.response; // the text of the response
					var strr = new Array();
					strr = javaArrayDecode(responseText);
					setUpElements(strr,"playerResult", "result");
					document.getElementById("playerResult").style.display = 'block';
					document.getElementById("showWinner").style.display = 'none';	
					document.getElementById("returnToMenu").style.display = 'none';	
				}
				
				xhr.send();
			}
			//123
			function getDeskCards() {
						
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/data"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					let responseText = xhr.response; // the text of the response
					  var strr = new Array();
					  strr = javaArrayDecode(responseText);
					  for(i = 0 ; i<strr.length; i++){
						  cardI = "card"+(1+i);
						  cardCI = "cardContent"+(1+i);
					  	if(strr[i]=="null"){
							document.getElementById(cardI).style.display = 'none';  
					 	 }
					  	else{
							setUpCard(decodeString(strr[i]),i);
							document.getElementById(cardI).style.display = 'block';
						}
					}
				}
				
				// We have done everything we need to prepare the CORS request, so send it
					
				
				xhr.send();	

			}


			function requestDraw() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/requestGameDraw"); 
				if (!xhr) {
					alert("CORS not supported");
				}
				clear();
				xhr.onload = function(e) {
					getGameInfoPackage();
					selectStage();
				}
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function requestWinner() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/requsetViewShowWinner"); 
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response;
					var cardI = "card"+(parseInt(responseText)+1);
					document.getElementById(cardI).style.display = 'block';
					document.getElementById(cardI).style.borderColor = "black";
					document.getElementById(cardI).style.borderWidth = "thick";
					getGameInfoPackage();
					getGameOver();
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
				return cardI;
			}

			function requestAISelect(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/requestAISelect"); // Request type and URL+parameters
					if (!xhr) {
						alert("CORS not supported");
					}
					xhr.onload = function(e) {
						getGameInfoPackage();
					}
				xhr.send();

			}








			function getGameInfoPackage(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateViewGameInformPackage"); 
					if (!xhr) {
						alert("CORS not supported");
					}
					xhr.onload = function(e) {
						var responseText = xhr.response; // the text of the response
						var strr = new Array();
						strr=javaArrayDecode(responseText);
						document.getElementById("gameStatus").innerHTML = strr[0];
						document.getElementById("gameRole").innerHTML = strr[1];
					}
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
			}





			function getRole() {
						
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/gameRole"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					  document.getElementById("gameRole").innerHTML = responseText;
				}
				
				// We have done everything we need to prepare the CORS request, so send it
					
				
				xhr.send();	
	

			}

			function getHumanCardOnDeck() {
				console.log("getHumanCardOnDeck");
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateViewHumanCardOnDeck"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					console.log(responseText);
					if(responseText=='null'){
							document.getElementById("card1").style.display = 'none';  
					}
					else{
						setUpCard(decodeString(responseText),0);
						document.getElementById("card1").style.display = 'block';
					}
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}



			function getCardOnDeck() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateViewCardSOnDeck"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					let responseText = xhr.response; // the text of the response
					  var strr = new Array();
					  strr = javaArrayDecode(responseText);
					console.log(strr);
					  for(i = 0 ; i<strr.length; i++){
						  cardI = "card"+(1+i);
						  cardCI = "cardContent"+(1+i);
					  	if(strr[i]=='null'){
							document.getElementById(cardI).style.display = 'none';  
					 	 }
					  	else{
							setUpCard(decodeString(strr[i]),i);
							document.getElementById(cardI).style.display = 'block';
						}
					}
				}
				xhr.send();
			}

			function HumanIsActivePlayer() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateHumanIsActivePlayer"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					if(responseText==0){
						document.getElementById("humanselectButton").style.display = 'block';
						getGameInfoPackage();
					}else{
						document.getElementById("humanselectButton").style.display = 'none';
						document.getElementById("AISelectButton").style.display = 'block';
						getGameInfoPackage();
					}
					getGameInfoPackage();
					getHumanCardOnDeck();
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			function requestGameInitialised() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/requestGameInitialised"); 
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}


		</script>
</html>