<?php
require "conn.php";

$mysql_qry = "select * from emp_data;";
$result = mysqli_query($conn,$mysql_qry);
if ($result = $conn->query($mysql_qry)) {
		
	 while ($row = $result->fetch_assoc()) {
		$id = $row["id"];
		$name = $row["name"];
		$surname = $row["surname"];
		$age = $row["age"];
		$username = $row["username"];
		$password = $row["password"];
        echo ("$id:$name:$surname:$age:$username:$password\n");
    }
	
	//echo "'$name' '$surname' '$age' '$username' '$password'";
}

else{
	echo "Failed";
}

?>