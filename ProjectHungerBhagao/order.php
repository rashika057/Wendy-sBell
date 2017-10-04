<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
$time1=date('Y-m-d H:i:s');
$name1 = $_POST['name'];
$address1=$_POST['address'];
$phone1=$_POST['phone'];
$billamount1=$_POST['billamount'];
$orderkey1=$_POST['orderkey'];
$id1=rand(100,100000);
$sql="insert into orderlist values
      ('$id1','$name1','$address1','$orderkey1','$billamount1',NOW())"; 

 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Data Entry");
   echo json_encode($a);
    
  }

  else
{
      echo 'Data Entry failure';
  }
$sql="select * from customer where User_Name='orderkey1'";
$result=mysqli_query($con,$sql);
$sql="select * from $orderkey1";
$result=mysqli_query($con,$sql);
require('pdf/tfpdf.php');
$pdf = new TFPDF();
$pdf->AddPage();
$pdf->SetFont('Arial','',12);
$pdf->Image('pdf/logo.png',10,10,-300);	
$pdf->Cell(72);	
$pdf->Cell(46,12,"Sales Invoice",0,0,'C');
$pdf->Ln();
$pdf->Cell(71);
$pdf->Cell(46,6,"WENDY's BELL RESTAURANT",0,0,'C');
 $pdf->Ln();
$pdf->Cell(70);
$pdf->Cell(46,6,"167,HyperDrive Lane,",0,0,'C');
 $pdf->Ln();
$pdf->Cell(70);
$pdf->Cell(46,6,"Adhyatmik Nagar,Lucknow-226019",0,0,'C');
 $pdf->Ln();
$pdf->Cell(69);
$pdf->Cell(46,6,"Ph. No. :- 8042717700",0,0,'C');
$pdf->Ln();
$pdf->Line(10, 50, 210-10, 50);
 $pdf->Ln();
$pdf->Cell(92,8,"Customer Name: ".$name1,0,0,'L');
$pdf->Cell(32);
date_default_timezone_set("India/Delhi");
$pdf->Cell(68,8,"Date & Time: ".date("Y/m/d")." ".date("h:i:sa"),0,0,'R');
$pdf->Ln();
$pdf->Cell(92,8,"Address: ".$address1,0,0,'L');
$pdf->Ln();

$pdf->Cell(92,8,"Contact No.: ".$phone1,0,0,'L');
$pdf->Ln();
$pdf->Cell(20,12,"Item Id",1,0,'C');
$pdf->Cell(40,12,"Name",1,0,'C');
$pdf->Cell(20,12,"Type.",1,0,'C');
$pdf->Cell(20,12,"Quantity",1,0,'C');
$pdf->Cell(20,12,"Total",1,0,'C');
$pdf->Ln();
foreach($result as $row) {
         $a=0;
	foreach($row as $column)
               {
                $a++;
                if($a==3)
                continue;
                if($a==2)
		$pdf->Cell(40,12,$column,1,0,'L');  
                else
                $pdf->Cell(20,12,$column,1,0,'L');  
}  
$pdf->Ln();   
}

$pdf->Output('orders/'.$orderkey1.$id1.'.pdf','F');
$sql="truncate table $orderkey1 ";
mysqli_query($con,$sql);
mysqli_close($con);
?>