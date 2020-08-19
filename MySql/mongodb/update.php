<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;
$emp_data = $client->employ->emp_data;



if($result>0){	
	echo "Username Exists.\nPlease select a different Username.";
}
else{
	$new_result = $emp_data->insertOne(
	["name" => $name,"surname"=>$surname,"age"=>$age,"username"=>$username,"password"=>$password]
	);

echo("New user created." );
}
?>