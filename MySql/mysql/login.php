<?php
require "conn.php";
$user_name = $_POST["usr_name"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from emp_data where username like '$user_name' and password like '$user_pass';";
$result = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	$surname = $row["surname"];
	echo "Success\n\nHello ".$name." ".$surname;
}
else{
	echo "Failed";
}
?>