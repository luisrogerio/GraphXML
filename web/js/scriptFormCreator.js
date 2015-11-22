//SCRIPT PARA OCULTAR A MENSAGEM APOS 4S;
$(document).ready(function() {
setTimeout("$('#mensagem').hide()", 4000);
});
//SCRIPT PARA CRIAR OS ELEMENTOS E O CARAI A 4;
$(function () {
    var i = 1;
    var j = 1;
    $('#adiconaCampo').live('click', function () {
        $('<p><label for="no"><input type="text" id="no" size="20" name="nos" value="" placeholder="Insira nome do Nó" /></label> \n\
            <a href="#" id="removerNo">Remove</a></p>').appendTo(formulario);
        i++;
        return false;
    });
    $('#adiconaAresta').live('click', function () {
        $('<p><label for="no"><input type="text" id="aresta" size="20" name="arestas" value="" placeholder="A,B" /></label> \n\
            <a href="#" id="removerAresta">Remove</a></p>').appendTo(arestas);
        j++;
        return false;
    });

    $('#removerNo').live('click', function () {
        if (i > 1) {
            $(this).parents('p').remove();
            i--;
        }
        return false;
    });
    $('#removerAresta').live('click', function () {
        if (j > 1) {
            $(this).parents('p').remove();
            j--;
        }
        return false;
    });

});