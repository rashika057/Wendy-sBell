<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$mainmenu1 = $_POST['mainmenu'];
$submenu1=$_POST['submenu'];
//$sql="select *  from Snacks";
$sql="select * from $mainmenu1 where Sub_Menu = '$submenu1'";
$result=mysqli_query($con,$sql);

$response=array();
while($row=mysqli_fetch_array($result))
{
array_push($response,array(
"Id"=>$row["Id"],
"Name"=>$row["Name"],
"Price"=>$row['Price'],
"Type"=>$row['Type'],
"Description"=>$row['Description'],
"Picture"=>$row['Picture'],
"MainMenu"=>$row['MainMenu']
));
}

 echo json_encode($response);


?>
