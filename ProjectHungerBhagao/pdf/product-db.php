<?php
        require(fpdf181/fpdf.php);
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');	
	define('DB','db1');	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
$pdf=new PDF();
class PDF extends FPDF
{
        function Header()
        {
            $this->Image('lifehead.jpg',25,10,150);
            $this->SetFont('Arial','B',12);
            $this->Cell(80);
            $this->Ln(20);
        }
}
$pdf=new PDF();
$pdf->AliasNbPages();
$pdf->AddPage();
$pdf->SetFont('Arial','','8'); 
$pdf->Table("select * from product"); 
$pdf->Output();
?>

