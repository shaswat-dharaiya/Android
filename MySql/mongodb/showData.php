<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;

$employ = $client->employ;
$emp_data = $employ->emp_data;
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