function validaCampo() {
			if(document.cadform.nome.value==""){
			alert("Campo 'Nome' obrigatório");
			return false;
		}	if(document.cadform.email.value==""){
			alert("Campo 'E-mail' obrigatório");
			return false;
		}	if(document.cadform.telefone.value==""){
			alert("Campo 'Telefone' obrigatório");
			return false;
		}	if(document.cadform.assunto.value==""){
			alert("Campo 'Assunto' obrigatório");
			return false;
		}	if(document.cadform.menssagem.value==""){
			alert("Campo 'Menssagem' obrigatório");
			return false;
		}	if(document.cadform.finalidade.value==""){
			alert("Campo 'Finalidade' obrigatório");
			return false;
		}	if(document.cadform.valor.value==""){
			alert("Campo 'Valor' obrigatório");
			return false;
		}	if(document.cadform.tipoImovel.value==""){
			alert("Campo 'Tipo do Imóvel' obrigatório");
			return false;
		}	if(document.cadform.celular.value==""){
			alert("Campo 'Celular' obrigatório");
			return false;
		}
			else
			return true;
		}
		
function Mascara(telefone){ 
   if(telefone.value.length == 0)
     telefone.value = '(' + telefone.value;

   if(telefone.value.length == 3)
      telefone.value = telefone.value + ')';

 if(telefone.value.length == 8)
     telefone.value = telefone.value + '-';
}

function MascaraCel(celular){ 
   if(celular.value.length == 0)
     celular.value = '(' + celular.value;

   if(celular.value.length == 3)
      celular.value = celular.value + ')';

 if(celular.value.length == 8)
     celular.value = celular.value + '-';
}