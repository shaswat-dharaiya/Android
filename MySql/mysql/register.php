<?php
require "conn.php";

$name = $_POST["name"];
$surname = $_POST["surname"];
$age = $_POST["age"];
$username = $_POST["username"];
$password = $_POST["password"];
$mysql_qry = "insert into emp_data (name, surname, age, username, password) values('$name','$surname','$age','$username','$password');";

if($conn->query($mysql_qry) === TRUE){
	echo "Success. New User '$name' '$surname'";
}
else{
	echo "Failed.\n".$mysql_qry."\n".$conn->error;
}
$conn->close();
?>