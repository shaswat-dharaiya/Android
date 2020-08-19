<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;

$user_name = $_POST["usr_name"];
$user_pass = $_POST["password"];

$employ = $client->employ;
$emp_data = $employ->emp_data;
$rows = $emp_data->countDocuments(['username' => $user_name,'password' => $user_pass]);

if ($rows>0){
	echo "Success\n\nWelcome ";
	$result = $emp_data->find(
	['username' => $user_name,
	 'password' => $user_pass],
	['projection' => [ 
				   'name' => 1,
				   'surname' => 1,
						'_id' => 0]]
	);
	foreach($result as $row){
		foreach($row as $data){
			echo($data);
			echo " ";
		}
		//echo "<br>";
}
}
else{
	echo "Failed\n\nRetry";
}

?>