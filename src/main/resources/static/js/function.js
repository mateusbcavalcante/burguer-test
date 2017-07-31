function retrieveHamburguer(hamburguerId) {
	var url = '/hamburguer/';
	
	if ($(hamburguerId).val() != '') {
		url = url + '/' + $(hamburguerId).val();
	}
	
	$("#resultsBlock").load(url);
}