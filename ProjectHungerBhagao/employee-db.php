<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$name1 = $_POST['name'];
$gen1=$_POST['gen'];
$pos1=$_POST['pos'];
$contact1=$_POST['contact'];
$addr1=$_POST['addr'];
$dob1=$_POST['dob'];
$img=$_POST['Item_img'];

$decodedImage=base64_decode("$img");
file_put_contents("images/".$id1,$decodedImage);
$imagePath=("http://192.168.43.49/ProjectHungerBhagao/emp/".$name1);


$sql="insert into employee values
      ('$name1','$gen1','$pos1','$contact1','$addr1','$dob1','$imagePath')"; 

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