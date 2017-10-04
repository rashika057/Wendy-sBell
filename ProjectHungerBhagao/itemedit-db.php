<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$name1 = $_POST['name'];
$price1=$_POST['price'];
$mainmenu1=$_POST['mainmenu'];
$sql = "UPDATE $mainmenu1 SET  Price= '$price1' WHERE Name='$name1'";
 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Removal");
   echo json_encode($a);
    
  }

  else
{
      echo 'Data Entry failure';
  }
  mysqli_close($con);



?>