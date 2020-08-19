<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client;

$employ = $client->employ;
$result = $employ->selectCollection('emp_data');
var_dump($result->find());

/*
foreach($employ->listCollections() as $collection){
	var_dump($collection);
}*/

/*$result = $employdb->createCollection('empCollection');

var_dump($result);*/
?>