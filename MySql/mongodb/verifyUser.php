<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;
$emp_data = $client->employ->emp_data;
$result_new = array();

$verUpd = $_POST["verUpd"];
$user_name = $_POST["user_name"];
	
if($verUpd == "false"){
	
	$result = $emp_data->countDocuments(['username'=>$user_name]);
	if($result>0){	
		echo "Verified";
	}
	else{
		echo "User does not exist.";
	}
}

if($verUpd == "true"){
	$result_new = $emp_data->findOne(['username'=>$user_name]);				

	$data = array();
	$data["id"] = $result_new["id"];
	$data["name"] = $_POST["name"];
	$data["surname"] = $_POST["surname"];
	$data["age"] = $_POST["age"];
	$data["username"] = $_POST["username"];
	$data["password"] = $_POST["password"];
	
	if(!empty($data["username"])){
		if ($data["username"] != $result_new["username"]){
			echo "Failed\n\n";
			echo "Username already exists.\nSelect a different username.";
		}
	}
	else{
	
		foreach($data as $key => $val){
			if(empty($val)){
				$data[$key] = $result_new[$key];
			}
		}
		
		$result = $emp_data->updateOne(
		['username'=>$user_name],
		['$set' =>  ['name' => $data["name"],
					'surname' => $data["surname"],
					'age' => $data["age"],
					'username' => $data["username"],
					'password' => $data["password"]]]
		);
		echo "Success\n\n";
		echo "Fields are updated.\nRedirecting you to Login page.";
	}
}

?>