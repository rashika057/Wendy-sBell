<?php
define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
$user_name = $_POST["username"];
$user_pass = $_POST["password"];


$sql="select * FROM customer where User_Name ='$user_name' and Password = '$user_pass'";
$result=mysqli_query($con,$sql);

$response=array();
while($row=mysqli_fetch_array($result))
{
array_push($response,array(

"Name"=>$row['Name'],
"Username"=>$row['User_Name']
));
}

 echo json_encode($response);





?>