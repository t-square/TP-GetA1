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
    	
			<div class="pricing-header px-1 pt-3 md-1 pb-3 mx-auto text-center">
				<h1 class="display-5">Top Trumps Game</h1>
			</div>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-auto mb-3"style="float:left;width:30em;">
						<div id="newGame" class="card mb-4 shadow-sm">
							
							<div class="card-header">
								<h5>Start a new Top Trumps Game</h5>
							</div>

							<div id="newGame" class="card-body">
								<a href="http://localhost:7777/toptrumps/game"><button class="btn btn-outline-dark btn-block" >New Game</button></a>
							</div>
						</div>
					</div>

					<div class="col-auto mb-3"style="float:right;width:30em;">
						<div id="newGame" class="card mb-4 shadow-sm">
							<div id="" class="card-header">
								<h5>Get Statistics from past Game</h5>
							</div>
							<div class="card-body">
								
								<a href="http://localhost:7777/toptrumps/stats"><button class="btn btn-outline-dark btn-block" >Game Statistics</button></a>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<footer class="pt-4 my-md-5 pt-md-5 border-top"></footer>	
	</body>
</html>