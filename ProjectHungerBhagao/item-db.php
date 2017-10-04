<?php
  define('HOST','localhost');
define('USER','root');
define('PASS',"");
define('DB','dborder');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Connection Failed');
 
$name1 = $_POST['name'];
$type1=$_POST['type'];
$price1=$_POST['price'];
$mainmenu1=$_POST['mainmenu'];
$submenu1=$_POST['submenu'];
$desc1=$_POST['desc'];
$img=$_POST['Item_img'];
$id1=rand(100,100000);

echo 'Hello'.$submenu1;

$decodedImage=base64_decode("$img");
file_put_contents("images/".$id1,$decodedImage);
$imagePath=("http://192.168.43.49/ProjectHungerBhagao/images/".$id1);


$sql="insert into $mainmenu1 values
      ('$id1','$name1','$type1','$price1','$desc1','$mainmenu1','$submenu1','$imagePath')"; 
//('$id1','$name1','$type1','$price1','$desc1','$mainmeu1','$submenu1','$imagePath')"; 
//('1','a','b','35','d','e','f','g')";


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