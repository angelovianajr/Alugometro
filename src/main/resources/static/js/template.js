$(function() {

	// Slider JqueryUI
	$( "#slider-range" ).slider({
		range: true,
		min: 1,
		max: 1000,
		values: [ 49, 949 ],
		slide: function( event, ui ) {
			$( "#amount" ).val( "R$ " + ui.values[ 0 ] + " - R$ " + ui.values[ 1 ] );

			$("#minval").val(ui.values[ 0 ]);

			$("#maxval").val(ui.values[ 1 ]);
		}
	});

	// Label de preços gerada pelo Slider
	$( "#amount" ).val( "R$ " + $( "#slider-range" ).slider( "values", 0 ) +
		" - R$ " + $( "#slider-range" ).slider( "values", 1 ) );

	// Carrega inputs para adicionar fotos
	$('#btn-fotos').click(function() {
		var divDemaisFotos = $('#fotos-div');
		var labelDemaisFotos = '<label><span>Demais Fotos:</span><input type="file" data-max-size="5242880" class="upload-file input-field" name="imagens" accept="image/*" /></label>';
		$(divDemaisFotos).append($(labelDemaisFotos));
	});

	// Faz o close no alert
	$('#close').click(function(e) {
		$('#alert').addClass('hidden');
		console.log('teste');
		console.log($('#alert'));
	});

	// Paginação Slick
	$('.image-pagination').slick({
		dots: true,
		infinite: true,
		speed: 300,
		slidesToShow: 1,
		adaptiveHeight: true
	});

	/*
	// Scritp paginação ajax
	var jsonFotos = '/anuncio/rest/';
	var idAnuncio = $('#idAnuncio').val();
	var image = '<img class="image-details" />';

	var fotos = $.getJSON(jsonFotos + idAnuncio, function(data) {
		var length = data.length;
		console.log(length);
		for (var i = 0; i < length; i++) {
			$('#image-pagination').append($('<div></div>')
				.append($(image).attr('src', data[i].urlFoto))
				);
		}
	});
*/

});

// Slide de fotos com OWL e json
/*
$(document).ready(function() {

	var jsonFotos = '/anuncio/rest/';
	var idAnuncio = $('#idAnuncio').val();

	console.log(jsonFotos);
	console.log(idAnuncio);

	$('#owl-demo').owlCarousel({
		jsonPath : jsonFotos + idAnuncio,
		jsonSuccess : customDataSuccess,
		navigation : true,
		slideSpeed : 300,
		paginationSpeed : 400,
		singleItem:true,
		autoHeight : true,
		transitionStyle:"fade"
	});

	function customDataSuccess(data) {
		var content = '';
		for(var i in data['fotos']) {

			var img = data['fotos'][i].url;

			content += '<div><img class="image-details" src=\"' +img+ '\" /></div>'
		}
		$('#owl-demo').html(content);
	}

});
*/

/*
var json = '/anuncio/rest';
var dados = $.getJSON(json);

var itens = 2;
var pagina = 0;

var imagem = '<div class="image-list pull-left grow img-hover"><a th:href="${ '/anuncio/' + anuncio.idAnuncio }" id="imagem"><img width="250" height="200" src="" /></a></div>';

function paginar() {
	var anuncio = $('#anuncio');
	for (var i = pagina * tamanhoPagina; i < dados.length && i < (pagina + 1) *  tamanhoPagina; i++) {
		anuncio.append(
			$(imagem))
	}
	$('#numeracao').text('Página ' + (pagina + 1) + ' de ' + Math.ceil(dados.length / tamanhoPagina));
}
*/