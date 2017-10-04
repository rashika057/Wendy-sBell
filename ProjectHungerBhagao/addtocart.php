<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$cart1 = $_POST['cartName'];
$id1=$_POST['id'];
$name1=$_POST['name'];
$type1=$_POST['type'];
$quantity1=$_POST['quantity'];
$amount1=$_POST['amount'];

$sql="insert into  $cart1(itemname,itemid,type,quantity,totalprice)
values('$name1','$id1','$type1','$quantity1','$amount1')"; 


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