<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$id1 = $_POST['id'];
$mainmenu1=$_POST['mainmenu'];
$sql = "DELETE FROM $mainmenu1 WHERE id='$id1'";
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