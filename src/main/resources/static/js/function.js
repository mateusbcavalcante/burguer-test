function retrieveHamburguerEdit(hamburguerId) {
	var url = '/hamburguer';

	if (hamburguerId != '') {
		url = url + '/' + hamburguerId;
	}

	$.ajax({
		url : url,
		success : function(data) {

			$('#resultsBlock').html(data);
			$("#myModal").modal("show");
		}
	});

}

function addHamburguerToCart(hamburguerId) {
	var url = '/hamburguer/add/cart/';

	if (hamburguerId != '') {
		url = url + '/' + hamburguerId;
	}

	$.ajax({
		url : url,
		success : function(data) {
			console.log('sucess');
			window.location.reload();
		}
	});

}

function removeHamburguerToCart(hamburguerId) {
	var url = '/hamburguer/remove/cart/';

	if (hamburguerId != '') {
		url = url + hamburguerId;
	}

	$.ajax({
		url : url,
		success : function(data) {
			console.log('sucess');
			window.location.reload();
		}
	});

}

function editIngredientPriceAjaxPost(ingredientId) {
	var url = '/ingredient';

	if (ingredientId != '') {
		url = url + '/' + ingredientId;
	}

	$.ajax({
		url : url,
		success : function(data) {

			$('#resultsBlock').html(data);
			$("#myModalIngredient").modal("show");
		}
	});

}

function addIngredientHamburguerAjaxPost(hamburguerId) {
	
	var ingredientId = $(ingredienteSelecionado).val();

	$.ajax({
		type : "POST",
		url : "/hamburguer/add/" + hamburguerId + "/" + ingredientId,
		success : function(data) {
			console.log('sucess');
			window.location.reload();
		}
	});

}

function submitIngredientPriceAjaxPost(ingredientId, priceValue) {

	$.ajax({
		type : "POST",
		url : "/ingredient/edit/" + ingredientId + "/" +priceValue ,
		success : function(data) {
			console.log('sucess');
			window.location.reload();
		}
	});

}

function removeIngredientHamburguerAjaxPost(hamburguerId, ingredientId) {

	$.ajax({
				type : "POST",
				url : "/hamburguer/remove/" + hamburguerId + "/"
						+ ingredientId,
				success : function(data) {
					console.log('sucess');
					window.location.reload();
				}
			});

}