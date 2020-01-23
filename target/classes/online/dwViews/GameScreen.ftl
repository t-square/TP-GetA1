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

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
						 		


			<div class="pricing-header px-1 pt-3 md-1 pb-md-1 mx-auto text-center">
			<h1 class="display-5">Top Trumps Game</h1>
			</div>
			
			<div class="pricing-header px-1 pb-3 pt-md-1 md-1 mx-auto text-center">
			<p id = gameStatus class="lead"></p>
			</div>

			<div class="container">

			<div class="row ">
				<div class="col col-xl-3 mb-5 ml-5  pl-5 mr-3 pr-3">
					<div class="card  text-center mb-4 " style="width: 12rem;">
						<div class="card-header ">
							<h6 class="my-0 py-0 px-0 font-weight-normal" id="gameRole"></h6>
						</div>
						<div  class="card-body">

							<div id="selectStatus" class=" mb-3">
								<h4></h4>
							</div>

							<div id="showWinner">
								<button class="btn btn-outline-dark btn-lg  mb-0" onclick="winnerStage()">Show winner</button>
							</div>

							<div id="nextRound">
								<button class="btn btn-outline-dark btn-lg mt-3 mb-0" onclick="nextRo()">Next round</button>
							</div>


							<div id="select">
								<button class="btn btn-outline-dark btn-lg mt-3 mb-0" onclick="activePlayer()">Select</button>
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
								</ul>
							</div>

							<div id="returnToMenu">
								<a href=http://localhost:7777/toptrumps><button class="btn btn-outline-dark btn-lg mb-0">Retrun to menu</button></a>
							</div>


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
				
				drawStage();

			}
			
			function drawStage() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				// For example, lets call our sample methods
				getGameStatus();
				clear();
				document.getElementById("select").style.display = 'block';
				document.getElementById("card1").style.display = 'block';
				getHumanDeskCards();
				getRole();
				
			}
			

			function winnerStage(){
				clear();
				getGameOver();
			}

			function activePlayer(){
				if(0==0){
					humanActived();
				}else
				document.getElementById("selectStatus").style.display = 'block';
			}

			function humanActived(){
				clear();
				document.getElementById("card1").style.display = 'block';
				document.getElementById("selectList").style.display = 'block';
				
			}



			function activePlayerResult(){

				document.getElementById("playerResult").style.display = 'block';
			}

			

			function nextRo(){
				drawStage();
			}


			function showRe(){
				
			}


			function clear(){
				document.getElementById("showWinner").style.display = 'none';
				document.getElementById("nextRound").style.display = 'none';
				document.getElementById("select").style.display = 'none';
				document.getElementById("selectList").style.display = 'none';
				document.getElementById("selectStatus").style.display = 'none';
				document.getElementById("returnToMenu").style.display = 'none';

				clearCard();
			}

			function clearCard(){
				for(i = 0 ; i<numPlayer; i++){
					cardI = "card"+(1+i);
					  document.getElementById(cardI).style.display = 'none';  
				}
			}



			function selectAttr1(){
				playerSelect("1");
			}
			
			function selectAttr2(){
				playerSelect("2");
			}

			function selectAttr3(){
				playerSelect("3");
			}

			function selectAttr4(){
				playerSelect("4");
			}

			function selectAttr5(){
				playerSelect("5");
			}

			function playerSelect(num){
				sendPlayerSelect(num);
				document.getElementById("selectList").style.display = 'none';
				document.getElementById("showWinner").style.display  = 'block';
				getDeskCards();
			}

			function decodeString(s){

				var strr = new Array();
				strr = s.split(',');
				return strr;

			}

			function setUpElements(s, targetID, targetCl){
				console.log(s.length);
					console.log(s);
				for(j=0 ; j<s.length ; j++){
					
					console.log(document.getElementById(targetID).getElementsByClassName(targetCl)[j]);
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
				name =s[0].split('name:');
				num	= s[1];
				document.getElementById(cardNameI).innerHTML = name[1];
				document.getElementById(numCardI).innerHTML = num;

				for(j=2 ; j<s.length ; j++){
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
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/viewSelectAtt?Word="+num); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {	
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}


		
			function getGameStatus() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updataViewGameStatus"); // Request type and URL+parameters
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					var strr = new Array();
					strr = javaArrayDecode(responseText);
					console.log(strr);
					document.getElementById("gameStatus").innerHTML = strr[0];
					document.getElementById("selectStatus").innerHTML = strr[1];
				}
				xhr.send();
			}
			function getGameOver(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updataViewGameOver"); // Request type and URL+parameters
					if (!xhr) {
						alert("CORS not supported");
					}
					xhr.onload = function(e) {
						console.log(xhr.response);
						if(xhr.response == "true"){
							getPlayerResult();
						}else{
							document.getElementById("nextRound").style.display = 'block';
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
				}
				document.getElementById("playerResult").style.display = 'block';
				document.getElementById("returnToMenu").style.display = 'block';	
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


			function setContent(id, c) {

				


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

			function getHumanDeskCards() {
						
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/pdata"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
		
					setUpCard(decodeString(responseText),0);


				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();

			}
		</script>
</html>