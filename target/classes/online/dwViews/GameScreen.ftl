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

			<p id="roundStatus">Visit Microsoft!</p>

			<p id="gameInform"></p>
			
			<div id="showWinner">
				<button  onclick="showRe()">Show winner</button>
			</div>

			<div id="nextRound">
				<button  onclick="nextRo()">Next round</button>
			</div>


			<div id="select">
				<button  onclick="Pselect()">Select</button>
			</div>

			<div className = "card">
				<h4 id="gameRole"></h4>

			</div>
			
			<div id="card1" className = "card">
				<h3 id="cardContent1"></h3>
			</div>

			<div id="card2" className = "card">
				<h3 id="cardContent2"></h3>
			</div>

			<div id="card3" className = "card">
				<h3 id="cardContent3"></h3>
			</div>

			<div id="card4" className = "card">
				<h3 id="cardContent4"></h3>
			</div>
			
			<div id="card5" className = "card">
				<h3 id="cardContent5"></h3>
			</div>

			
		</div>
		
		<script type="text/javascript">
			var numPlayer = 5;
			// Method that is called on page load
			function initalize() {
				
				draw();
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				fuck(100);
				
			}
			
			function draw() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				fuck(100);
				// For example, lets call our sample methods
				roundStatus();
				clear();
				document.getElementById("select").style.display = 'block';
				document.getElementById("card1").style.display = 'block';
				pdata();
				roleUpdate();
			}


			function Pselect(){
				clear();
				data();
				document.getElementById("showWinner").style.display = 'block';
			}


			function nextRo(){
				draw();
			}


			function showRe(){
				clear();
				clearCard();
				document.getElementById("nextRound").style.display = 'block';
			}


			function clear(){
				document.getElementById("showWinner").style.display = 'none';
				document.getElementById("nextRound").style.display = 'none';
				document.getElementById("select").style.display = 'none';
			}

			function clearCard(){
				for(i = 0 ; i<numPlayer; i++){
					cardI = "card"+(1+i);
					
					  document.getElementById(cardI).style.display = 'none';  
					  console.log(cardI);
				}
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
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function fuck(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/fuck?Word="+word); // Request type and URL+parameters
				
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
			
			//123
			function roundStatus() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundStatus"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					  document.getElementById("roundStatus").innerHTML = responseText;
				}
					 // lets produce an alert
				
				a++;
				// We have done everything we need to prepare the CORS request, so send it
					
				
				xhr.send();	


			}

			//123
			function re() {
						
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/reset"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					  document.getElementById("roundStatus").innerHTML = responseText;
					

				};
				
				// We have done everything we need to prepare the CORS request, so send it
					
				
				xhr.send();	


			}

			//123
			function data() {
						
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
					  strr = responseText.split('|?|');
					  for(i = 0 ; i<strr.length; i++){
						  cardI = "card"+(1+i);
						  cardCI = "cardContent"+(1+i);
					  if(strr[i]=="null"){
						document.getElementById(cardI).style.display = 'none';  
					  }
					  else{	
						  console.log(strr[i]);
						  console.log(cardCI);
						  var tem = strr[i];
						  strr[i]=strr[i].replace(/\n/g,'<br>');
						document.getElementById(cardI).style.display = 'block';  
						document.getElementById(cardI).innerHTML = strr[i]; 
					  }
					}
				}
				
				// We have done everything we need to prepare the CORS request, so send it
					
				
				xhr.send();	


			}

			function roleUpdate() {
						
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

			function pdata() {
						
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
					responseText=responseText.replace(/\n/g,'<br>');
					document.getElementById("card1").innerHTML = responseText; 		
				}
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();

			}



		</script>
		
		</body>
</html>