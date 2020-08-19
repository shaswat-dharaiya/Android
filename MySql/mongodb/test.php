<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;
$emp_data = $client->employ->emp_data;
$result = $emp_data->find([],['projection' => [ '_id' => 1,'username' => 1]]);
$i = 0;
$j = 0;
$userID = $_GET["userID"];
$ID = explode(":",$userID);
//$infinity = 1000;
//array_push($ID,$infinity);

foreach($result as $data){
	echo "<br>";
	echo($data["username"]);
	
	/*foreach($data as $key => $val){
		//echo($key." ");
		//echo($val." ");
		if($ID[$j] == $i){
			echo " true";
			$j++;
		}
		else{
			echo " false";
		}
		$i++;
	}*/

}
?>
