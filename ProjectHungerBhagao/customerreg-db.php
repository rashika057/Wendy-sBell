<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$name1 = $_POST['name'];
$username1=$_POST['username'];
$email1=$_POST['email'];
$password1=$_POST['password'];
$confpassword1=$_POST['confpassword'];
$mobile1=$_POST['mobile'];
echo "$username1";
$sql="insert into Customer 
values('$name1','$username1','$email1','$password1','$confpassword1','$mobile1')"; 


 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Data Entry");
   echo json_encode($a);
    
  }

  else
{
      echo 'Data Entry failure';
  }
  mysqli_close($con);



?>