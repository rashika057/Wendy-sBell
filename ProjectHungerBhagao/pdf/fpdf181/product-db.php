
<?php
require('fpdf.php');
define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','db1');
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
$pdf=new FPDF();
$pdf->AddPage();
$filename = "Hellaa".".pdf";
header("Content-Type: application/pdf");
header("Pragma: public");
header("Expires: 0");
header("Cache-Control: must-revalidate, post-check=0, pre-check=0");
header("Content-Type: application/force-download");
header("Content-Type: application/octet-stream");
header("Content-Type: application/download");
header('Content-Disposition: attachment; filename="'.$filename.'"');
header("Content-Transfer-Encoding: binary ");
$pdf->SetFont('Arial','B',10);
$pdf->Ln();
$pdf->Ln();
$pdf->SetFont('times','B',10);
$pdf->Cell(25,7,"ID");
$pdf->Cell(30,7,"Name");
$pdf->Cell(40,7,"Brand");
$pdf->Cell(30,7,"Price");
$pdf->Cell(30,7,"Warranty");
$pdf->Cell(30,7,"Quantity");
$pdf->Ln();
$pdf->Cell(450,7,"----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
$pdf->Ln();

        
        $sql = "SELECT * FROM product";
        $result=mysqli_query($con,$sql);

        while($row=mysqli_fetch_array($result))
        {
            $Id=$row["Id"];
           $Name=$row["Name"];
           $Brand=$row["Brand"];
            $Price=$row['Price'];
            $Warranty=$row['Warranty'];
            $Quantity=$row['Quantity'];
             $Description=$row['Description'];
             
            $pdf->Cell(25,7,$Id);
            $pdf->Cell(30,7,$Name);
            $pdf->Cell(40,7,$Brand);
            $pdf->Cell(30,7,$Price);
            $pdf->Cell(30,7,$Warranty);
            $pdf->Cell(30,7,$Quantity); 
            $pdf->Ln(); 
        }
$pdf->Output('php://output');
?>


