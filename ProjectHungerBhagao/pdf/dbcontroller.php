<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','db1');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 echo "Connection established";
/*$sql="create table  toys(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
itemname VARCHAR(30) NOT NULL,
itemid VARCHAR(15) NOT NULL,
type VARCHAR(20),
quantity VARCHAR(15),
totalprice VARCHAR(15)
)";



 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Cart Formation");
   echo json_encode($a);
    
  }

  else
{
      echo 'Registration failure';
  } */
$sql="insert into toys values
('Ben 10 Watch', 'Battery Toys'),
('Angry Birds Gun', 'Mechanical Toys'),
('Remote Car', 'Remote Toys'),
('Uno Cards', 'Card Game'),
('Keyboard', 'Musical Toys'),
('Jigsaws', 'Board Game')";

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