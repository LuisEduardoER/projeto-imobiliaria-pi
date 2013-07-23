<?php
if ($_POST) {

	if (strpos($_POST['Email'],"@")) {
	
		$body = "
		<html>
		<head>
		<title>{$_POST['assunto']}</title>
		<style type='text/css'>
		body,td,th {
			font-family: Tahoma, Arial, Verdana;
			font-size: 11px;
		}
		body {
			margin-left: 20px;
			margin-top: 15px;
			margin-right: 20px;
			margin-bottom: 20px;
		}
		a {
			font-family: Tahoma, Arial, Verdana;
			font-size: 11px;
			color: #003399;
		}
		a:link {
			text-decoration: none;
		}
		a:visited {
			text-decoration: none;
			color: #003399;
		}
		a:hover {
			text-decoration: underline;
			color: #003399;
		}
		a:active {
			text-decoration: none;
			color: #003399;
		}
		</style>
		</head>
		<body>
		";
		
		if (!empty($_POST['logo'])) {			
			$body .= "<img src='{$_POST['logo']}' vspace='5' hspace='10'><br><br>";
		}
		
		$body .= "<b>Formulário enviado do site {$_SERVER['HTTP_HOST']}</b><br><br>";
		$body .= "<b>Data de Envio:</b> ".date("d/m/Y H:i:s")."<br>";
		
		// array com os elementos que não devem ir para o formulário
		$noFields = array("logo","assunto","eDestino","redirect","Submit","Submit_x","Submit_y","Reset","imageField_x","imageField_y");
		
		foreach ($_POST as $campo => $valor) {
			
			if ($campo!="Enviar" && $campo!="enviar"){
				if (!in_array($campo,$noFields)) {
				
					$nCampo = str_replace("_"," ",$campo);
					$nValor = strip_tags($valor);
					$body .= "<b>$nCampo:</b> $nValor<br>";
				
				}
			}
		
		}
		
		$body .= "
		<br>___________________________________________________________________________<br>
		                 
		</body>
		</html>
		";
		
		$to = $_POST['eDestino'];
		$from = $_POST['Email'];
		$subject = $_POST['assunto'];
		$headers = "MIME-Version: 1.0\r \n"; 
		$headers .= "Content-Type: text/html;\n\tcharset=\"utf-8\"\n"; 
		$headers .= "From: $from\r\n";
		$headers .= "Reply-To: $from\r \n"; 
		
		$sendMail = @mail($to,$subject,$body,$headers);
		
		if ($sendMail == true) {		
			
			
			if ($_POST['redirect']!=""){
				echo "<script>window.location = '{$_POST['redirect']}';</script>";
			}else{
				echo "response=ok&";
			}
		
		} else {
		
			echo "
			<script>
			alert('Seu e-mail não pode ser enviado. Tente novamente mais tarde');
			history.back();
			</script>
			";
		
		}
	
	} else {
	
		echo "
		<script>
		alert('Informe uma conta de E-Mail válida!');
		history.back();
		</script>
		";
	
	}
	
} else {

	echo "
	<script>
	alert('Acesso Inválido');
	window.location = 'http://{$_SERVER['HTTP_HOST']}';
	</script>
	";

}
?>