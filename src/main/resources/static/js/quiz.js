var recomend = [0,0,0,0,0,0,0];
var x = [0,0,0,0,0,0,0];
var resultado;

//RECEBER SEGMENTO
let segment = "Alimentos e Bebidas"; //RECEBER SEGMENTO DO BACK

segment =  (segment.split(" ").join("")).replace(/[\r\n]/gm, '');
switch(segment){
    case "AlimentoseBebidas":
      x = [3,3,1,0,2,2,2];
      somar(x);
      break;
    
    case "ArteeDecoração":
        x = [2,2,3,2,1,3,3];
        somar(x);
      break;

      case "Automóveis":
        x = [2,1,3,2,2,1,1];
        somar(x);
      break;

      case "ConstruçãoeReforma":
        x = [1,1,2,1,2,1,2];
        somar(x);
      break;

    case "ConveniênciaePresentes":
    x = [2,1,2,2,3,2,1];
    somar(x);
      break;

    case "Educação":
        x = [2,1,0,2,1,3,2];
        somar(x);
      break;

    case "EventoseTurismo"://
        x = [2,1,0,3,2,2,2];
        somar(x);
      break;
    
      case "ModaeEstética":
        x = [2,2,2,2,1,3,2];
        somar(x);
      break;

      case "NaturezaePets":
        x = [2,2,2,2,3,3,2];
        somar(x);
      break;

      case "Saúde":
        x = [2,2,3,2,2,2,2];
        somar(x);
      break;

      case "Serviços":
        x = [2,1,0,1,2,3,1];
        somar(x);
      break;

      case "TecnologiaeInovação":
        x = [3,1,3,1,1,1,2];
        somar(x);
      break;

    default:
        x = [0,0,0,0,0,0,0];
    break;
  }


  function calcularRecomend(){
    //PERGUNTA 1
    if(document.forms['quiz-recomendacao'].elements['1-Opt1'].checked){
        x = [2,2,3,3,1,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['1-Opt2'].checked){
        x = [2,1,1,3,3,3,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['1-Opt3'].checked){
        x = [2,2,2,2,2,2,2];
        somar(x);
    }

    //PERGUNTA 2
    if(document.forms['quiz-recomendacao'].elements['2-Opt1'].checked){
        x = [2,1,3,3,3,3,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['2-Opt2'].checked){
        x = [1,2,1,1,3,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['2-Opt3'].checked){
        x = [1,3,3,3,1,2,1];
        somar(x);
    }

    //PERGUNTA 3
    if(document.forms['quiz-recomendacao'].elements['3-Opt1'].checked){
        x = [2,1,2,1,0,3,0];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['3-Opt2'].checked){
        x = [2,1,3,2,1,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['3-Opt3'].checked){
        x = [3,2,2,3,2,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['3-Opt4'].checked){
        x = [3,2,2,3,3,2,2];
        somar(x);
    }

    //PERGUNTA 4
    if(document.forms['quiz-recomendacao'].elements['4-Opt1'].checked){
        x = [0,0,2,3,2,3,0];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['4-Opt2'].checked){
        x = [2,1,2,2,2,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['4-Opt3'].checked){
        x = [3,2,1,3,2,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['4-Opt4'].checked){
        x = [2,3,0,0,2,2,3];
        somar(x);
    }

    //PERGUNTA 5
    if(document.forms['quiz-recomendacao'].elements['5-Opt1'].checked){
        x = [2,3,2,1,2,3,3];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['5-Opt2'].checked){
        x = [2,2,2,2,2,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['5-Opt3'].checked){
        x = [1,1,2,3,1,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['5-Opt4'].checked){
        x = [2,2,2,2,3,2,2];
        somar(x);
    }

    //PERGUNTA 6
    if(document.forms['quiz-recomendacao'].elements['6-Opt1'].checked){
        x = [2,0,3,3,1,3,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['6-Opt2'].checked){
        x = [2,1,2,2,2,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['6-Opt3'].checked){
        x = [3,2,1,1,3,1,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['6-Opt4'].checked){
        x = [1,3,1,1,3,0,3];
        somar(x);
    }

    //PERGUNTA 7
    if(document.forms['quiz-recomendacao'].elements['7-Opt1'].checked){
        x = [2,1,2,3,1,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['7-Opt2'].checked){
        x = [1,1,2,2,2,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['7-Opt3'].checked){
        x = [2,2,3,3,1,1,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['7-Opt4'].checked){
        x = [2,2,2,2,1,2,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['7-Opt5'].checked){
        x = [1,2,3,2,2,1,2];
        somar(x);
    }

    //PERGUNTA 8
    if(document.forms['quiz-recomendacao'].elements['8-Opt1'].checked){
        x = [3,2,2,1,2,2,3];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['8-Opt2'].checked){
        x = [2,2,2,2,2,1,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['8-Opt3'].checked){
        x = [2,3,3,3,3,3,3];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['8-Opt4'].checked){
        x = [3,2,3,2,3,2,1];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['8-Opt5'].checked){
        x = [2,2,2,2,2,2,3];
        somar(x);
    }

    //PERGUNTA 9
    if(document.forms['quiz-recomendacao'].elements['9-Opt1'].checked){
        x = [1,2,3,2,1,2,3];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['9-Opt2'].checked){
        x = [1,3,1,2,3,1,2];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['9-Opt3'].checked){
        x = [2,3,2,1,2,2,1];
        somar(x);
    }

    //PERGUNTA 10
    if(document.forms['quiz-recomendacao'].elements['10-Opt1'].checked){
        x = [2,2,3,0,0,2,0];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['10-Opt2'].checked){
        x = [2,3,2,0,1,1,3];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['10-Opt3'].checked){
        x = [3,3,3,0,0,0,0];
        somar(x);
    }
    else if(document.forms['quiz-recomendacao'].elements['10-Opt4'].checked){
        x = [3,1,0,2,1,2,2];
        somar(x);
    }

    document.getElementById("resultadoRecomendacao").value = recomend;



  }



function somar(y){
    for(i = 0; i < recomend.length; i++){
        recomend[i] = recomend[i] + y[i];
    }
}

function classificar(rec){
    let maior = 0;
    let resultado = "";
    for(i = 0; i < rec.length; i++){
        if(rec[i] > maior) maior = rec[i];
    }
    if(rec[0] == maior) resultado = resultado + " Desenvolvimento WEB";
    if(rec[1] == maior) resultado = resultado + " Desenvolvimento Mobile";
    if(rec[2] == maior) resultado = resultado + " Marketplace e E-commerce";
    if(rec[3] == maior) resultado = resultado + " Marketing Digital";
    if(rec[4] == maior) resultado = resultado + " Consultoria de Marketing";
    if(rec[5] == maior) resultado = resultado + " Rede Social";
    if(rec[6] == maior) resultado = resultado + " Identidade Visual da Marca";
    
    return resultado;

}