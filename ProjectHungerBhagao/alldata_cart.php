<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$cartname1 = $_POST['cartName'];
$sql="select * from $cartname1";
$result=mysqli_query($con,$sql);

$response=array();
while($row=mysqli_fetch_array($result))
{
array_push($response,array(
"Type"=>$row["type"],
"Name"=>$row["itemname"],
"Itemid"=>$row['itemid'],
"Quantity"=>$row['quantity'],
"TotalPrice"=>$row['totalprice']
));
}

 echo json_encode($response);


?>
