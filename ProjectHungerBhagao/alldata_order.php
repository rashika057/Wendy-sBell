<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$sql="select * from orderlist";
$result=mysqli_query($con,$sql);

$response=array();
while($row=mysqli_fetch_array($result))
{
array_push($response,array(
"Id"=>$row['Id'],
"Name"=>$row['Name'],
"Address"=>$row['Address'],
"OrderKey"=>$row['OrderKey'],
"BillAmount"=>$row['Bill_Amount'],
"datetime"=>$row['DateTime']
));
}

 echo json_encode($response);


?>
