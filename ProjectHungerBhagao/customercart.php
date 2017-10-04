<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 $user_name = $_POST["username"];
$sql="create table  $user_name(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
itemname VARCHAR(30) NOT NULL,
itemid VARCHAR(15) NOT NULL,
type VARCHAR(20),
quantity VARCHAR(15),
totalprice VARCHAR(15)
)";



 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Cart Entry");
   echo json_encode($a);
    
  }

  else
{
      echo 'Registration failure';
  }
  mysqli_close($con);



?>