<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Lang" content="en">
<link href="practica1.css" type="text/css" rel="stylesheet">
<title>Twitter Sentiment Analysis | Práctica 1 ISBC</title>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <!-- html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 } -->
	<style type="text/css">
      html { height: 100% }
      body { height: 100%; }
      #map-canvas { width: 700px; height: 450px }
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBtVkyhLjELt0QQ55d9TY0n9PQrY3o2dmc&sensor=true">
    </script>


</head>
<body>
<h1>Twitter Sentiment Analysis | Práctica 1 ISBC: Álvaro Hernando Gavilán, Carlos Rodríguez Díaz, Antonio Irizar López</h1>
<p>Escribe una palabra, @usuario o #hashtag para obtener su Sentiment Analysis en Twitter:</p>
<form method="GET">
    <label>Keyword: </label> <input type="text" name="q" /> 
    <input type="submit" value="Analizar" />
</form>

<?php

if(isset($_GET['q']) && $_GET['q']!='') {
			
	include_once(dirname(__FILE__).'/config.php');
    include_once(dirname(__FILE__).'/lib/TwitterSentimentAnalysis.php');
	include_once(dirname(__FILE__).'/stemmer-es1.0/stemm_es.php');

    $TwitterSentimentAnalysis = new TwitterSentimentAnalysis(DATUMBOX_API_KEY,TWITTER_CONSUMER_KEY,TWITTER_CONSUMER_SECRET,TWITTER_ACCESS_KEY,TWITTER_ACCESS_SECRET);

    //Search Tweets parameters as described at https://dev.twitter.com/docs/api/1.1/get/search/tweets
    $twitterSearchParams=array(
        'q'=>$_GET['q'],
        'lang'=>'es',
        'count'=>100,
    );
	
    $results=$TwitterSentimentAnalysis->sentimentAnalysis($twitterSearchParams);
	$locations = array();
	$names = array();
	$geos = array();

    ?>
    <h1>Resultados para "<?php echo $_GET['q']; ?>"</h1>
    <table border="0" cellpadding="5" cellspacing="1">
        <tr>
            <td bgcolor="#d2d5db">Usuario</td>
			<td bgcolor="#d2d5db">Dirección</td>
			<td bgcolor="#d2d5db">Geolocalización</td>
            <td bgcolor="#d2d5db">Tweet</td>
            <td bgcolor="#d2d5db">URL</td>
            <td bgcolor="#d2d5db">Valoración</td>
        </tr>
        <?php
		
		$gestor = @fopen("lexicon.txt", "r");
$dfile=fopen("lexicon-stem.txt","w+");
if ($gestor) {
    while (($bufer = fgets($gestor, 4096)) !== false) {
		$palabras=explode("\t",$bufer);
		$pstem=stemm_es::stemm($palabras[0]);
		fwrite($dfile,$pstem . PHP_EOL);
    }
    if (!feof($gestor)) {
        echo "Error: fallo inesperado de fgets()\n";
    }
    fclose($gestor);
	fclose($dfile);
}

		$lexiconStem = file_get_contents("lexicon-stem.txt");
		$eRegulares = file_get_contents("e_regulares.txt");
		$eRegArray = explode("\n",$eReg);
		$lexiconArray = explode("\n",$lexiconStem);
		$lineasL = count($lexiconStem); // numero de lineas del fichero = n (posiciones lexemas desde 0 a n-1)
		
		$filtro = array("el","la","los","las","y","o","que","a","para","porque","por que","un","una","uno","unos","unas","yo","tu","el","ella","ello","nosotros","vosotros","ellos","ellas","RT","me","de","en","es","si","no","aunque","le","lo","");
		
		$positivos=0;
		$neutral=0;
		$negativos=0;
		
        foreach($results as $tweet) {
			$tweettext=explode(" ",$tweet['text']);
			$puntuacionLinea=0;
			$analizadas = array();
			
			
			foreach($tweettext as $word){
				
				$wordS = stemm_es::stemm($word);
				
				$filtrada=false;
//				if (true){
				if (in_array($wordS,$filtro)==false){
				
				$puntuacion=0;
				foreach($lexiconArray as $nLinea => $lex){
					
					
					if ($lex==$wordS){
						$analizadas[]=$wordS;
						//echo $nLinea . " " .$wordS."<br>";
						$lexiconOrig = @fopen("lexicon.txt", "r");
						for ($i = 1; $i <= $nLinea; $i++){
							$fila=fgets($lexiconOrig);
						}
						$fila=fgets($lexiconOrig);
						$pal=explode("\t",$fila);
						//echo $pal[0]."<br>";
						$valoracion=$pal[2];
						//echo $valoracion."<br>";
						//echo strcmp("pos",$valoracion);
						if (substr_compare("pos",$valoracion,0,3,false)==0){
							$puntuacion=1;
							//echo "punt: 1";
							}
						else{
							$puntuacion=-1;
							//echo "punt: -1";
							}
						
						if ($pal[3]!=null){
							$valoracion2=$pal[3];
								if (substr_compare("pos",$valoracion2,0,3,false)==0)
									$puntuacion=$puntuacion+1;
								else
									$puntuacion=$puntuacion-1;
						}
						fclose($lexiconOrig);
				
					}
					
				}
				$puntuacionLinea=$puntuacionLinea+$puntuacion;
				
				} // fin filtro
						
			}
			
            ?>
            <tr>
                <td><?php 
				echo $tweet['user']; ?></td>
				<td><?php 
				if (!$tweet['location']==null){
				$locations[]=$tweet['location'];
				$names[]=$tweet['user'];
				}
				echo $tweet['location']; ?></td>
				<td><?php 
				if (!($tweet['geo']==null)){
				$geos[]=$tweet['geo'];
				$geos[]=$tweet['user'];
				echo '<b>('.$tweet['geo'][0].', '.$tweet['geo'][1].'</b>)';
				} else
				echo "No disponible"; ?></td>
                <td><?php echo $tweet['text']."<br>";
							//print_r ($analizadas); ?></td>
                <td><a href="<?php echo $tweet['url']; ?>" target="_blank">Ver</a></td>
				<td><?php 
				if ($puntuacionLinea>0){
					echo "<font color='green'><b>Positivo</b></font>";
					$positivos=$positivos+1;
					}
				else {
					if ($puntuacionLinea==0){
						echo "<font color='orange'><b>Neutral</b></font>";
						$neutral=$neutral+1;
						}
					else {
						echo "<font color='red'><b>Negativo</b></font>";
						$negativos=$negativos+1;
						}
				
				}
				

				?></td>
            </tr>
            <?php
        }
		
		
        ?>
		
    </table>
	<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
	
		var positivos = "<?php echo $positivos; ?>" ;
		var neutral = "<?php echo $neutral; ?>" ;
		var negativos = "<?php echo $negativos; ?>" ;

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Positivos', <?php echo $positivos; ?>],
          ['Neutral', <?php echo $neutral; ?>],
          ['Negativos', <?php echo $negativos; ?>],
        ]);

        // Set chart options
        var options = {'title':'Sentiment Analysis Chart',
                       'width':550,
                       'height':500,
					   colors: ['green', 'orange', 'red']};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
	<table border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td>
	
	    <script type="text/javascript">
      function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(40.4167754, -3.7037901999999576),
          zoom: 2,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
		var geocoder = new google.maps.Geocoder();
		
		
		<?php
		foreach ($locations as $n => $loc){ // aqui recorremos el array en el que hemos guardado todas las direcciones de los tweets para internet obtener las coordendas si es una direccion que reconoce la API de Google Maps
		
		?>
			
			geocoder.geocode({'address': '<?php echo $loc ?>'}, function(results, status){
			  if (status == google.maps.GeocoderStatus.OK) {
				var marker = new google.maps.Marker({
					map: map,
					position: results[0].geometry.location,
					title: "<?php echo $names[$n] ?> (por dirección)"
				});
			  } else {
				//alert("Geocode was not successful for the following reason: " + status);
			  }
			});
			
		<?
		}
		?>
		
		
		
				<?php
		foreach ($geos as $n => $geoPoint){ // aqui recorremos el array de las coordendas que hemos guardado de los tweets que tenian activada la geolocalización
		?>
				
				var myLatlng = new google.maps.LatLng(<?php echo $geoPoint[0]; ?>,<?php echo $geoPoint[1]; ?>);
				var marker = new google.maps.Marker({
					map: map,
					position: myLatlng,
					title: "<?php echo $geos[$n+1]; ?> (por geolocalización)"
				});
			
		<?
		}
		?>
		

      }
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
	

	<div id="chart_div"></div></td><td><div id="map-canvas"></div><br>Mapa geográfico con las localizaciones de los Tweets cuyos usuarios han especificado una dirección correcta o tienen activado el servicio de geolocalización. <b>(Google Maps API tiene un cupo máximo de consultas, lo que hace que a veces lo superemos y por eso no se muestra el mapa, pero funciona perfectamente. Probar con otra búsqueda en este caso, por favor.)</b></td>
	</tr>
	</table>
    <?php
	
}

?>
  
</body>
</html>
