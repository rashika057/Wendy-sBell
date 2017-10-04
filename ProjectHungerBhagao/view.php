<?php
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','dborder');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$name1 = $_POST['name'];
$address1=$_POST['address'];
$billamount1=$_POST['billamount'];
$orderkey1=$_POST['orderkey'];
$sql="insert into orderlist values
      ('$name1','$address1','$orderkey1','$billamount1')"; 

 if(mysqli_query($con,$sql))
 {
  $a=array("Successful Data Entry");
   echo json_encode($a);
    
  }

  else
{
      echo 'Data Entry failure';
  }
$sql="select * from $orderkey1";
$result=mysqli_query($con,$sql);
require('pdf/tfpdf.php');
$pdf = new TFPDF();
$pdf->AddPage('L');
$pdf->SetFont('Arial','',12);
$pdf->Image('pdf/logo.png',10,10,-300);	
$pdf->Cell(230);	
$pdf->Cell(46,12,"Order list ",1,0,'C');
$pdf->Ln();
foreach($result as $row) {
         $pdf->Ln();
	foreach($row as $column)
		$pdf->Cell(46,12,$column,1,0,'L');
         
}
$pdf->Output('orders/'.$orderkey1.'.pdf','F');

?>