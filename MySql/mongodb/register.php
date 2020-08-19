<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;
$emp_data = $client->employ->emp_data;

$name = $_POST["name"];
$surname = $_POST["surname"];
$age = $_POST["age"];
$username = $_POST["username"];
$password = $_POST["password"];

$result = $emp_data->countDocuments(
	['username'=>$username]
	);

if($result>0){	
	echo "Failed\n\nUsername Exists.\nPlease select a different Username.";
}
else{
	$result = $emp_data->countDocuments();
	$new_result = $emp_data->insertOne(
	["id" => ($result+1), 
	 "name" => $name,
	 "surname"=>$surname,
	 "age"=>$age,
	 "username"=>$username,
	 "password"=>$password
	 ]);

echo("Success\n\nNew user created." );
}
?>