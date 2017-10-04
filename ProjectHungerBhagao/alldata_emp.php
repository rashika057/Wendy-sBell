<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$sql="select * from employee";
$result=mysqli_query($con,$sql);

$response=array();
while($row=mysqli_fetch_array($result))
{
array_push($response,array(
"Name"=>$row["Name"],
"Gender"=>$row["Gender"],
"Position"=>$row['Position'],
"ContactNo"=>$row['ContactNo'],
"Address"=>$row['Address'],
"DateOfBirth"=>$row['DateOfBirth'],
"Picture"=>$row['Picture']
));
}

 echo json_encode($response);


?>
