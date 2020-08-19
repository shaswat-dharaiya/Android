<?php
require 'vendor/autoload.php';

$userID = $_POST["userID"];
$ID = explode(":",$userID);

$client = new MongoDB\Client;
$emp_data = $client->employ->emp_data;
$result = $emp_data->find([],['projection' => ['username' => 1]]);
$i = 0;
$j = 0;

$infinity = 1000;
array_push($ID,$infinity);

foreach($result as $data){
		if($ID[$j] == $i){
			echo "Employ ".($i+1)."\tUsername: ".$data["username"]."\n";
			$result_new = $emp_data->deleteOne(['username' => $data["username"]]);
			$j++;
		}
		$i++;

}
echo "\n";

$result = $emp_data->find(
	[],
	['projection' => [   'id' => 1,
					   'name' => 1,
					'surname' => 1,
						'age' => 1,
				   'username' => 1,
				   'password' => 1,
						'_id' => 0]]
	);
foreach($result as $row){
		foreach($row as $data){
			echo(":".$data);
		}
		echo "\n";
}
?>
