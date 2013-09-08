var items = null;

$(function() {
	loadFromServer();
	
	$('#form-todo').submit(function() {
		var name = $('#input-description').val();
		$.postJSON("/items", JSON.stringify({"description": name}), function(data) {
			loadFromServer();
		});
		return false;
	});

	$('#list-items').on('click', '.item-delete', function(){
		var id = $(this).parent().parent().attr('id');
		$.deleteJSON("/items", JSON.stringify({"id": id}), function(data) {
			loadFromServer();
		});
	});

	$(".sortable").sortable({
		helper: function(e, ui) {
			ui.children().each(function() {
				$(this).width($(this).width());
			});
			return ui;
		},
		update: function(event, ui) {
			fromIdx = ui.item.attr('id');
			orderedIdx = $(".sortable").sortable("toArray");
			toIdx = orderedIdx.indexOf(fromIdx);
			reindexTable();
			$.postJSON("/items_sort", JSON.stringify({"from":parseInt(fromIdx), "to":toIdx}));
		}
	}).disableSelection();
});

function loadFromServer() {
	$.getJSON("/items", function(data) {
		items = data;
		renderItems();
	});
}

function renderItems() {
	$("#list-items").empty();
	$(items).each(function(key, item){
		$('<tr id="' + key + '"><td class="span1">' + (key+1) + '</td><td>' + item.description + 
				'</td><td class="span1"><a href="javascript:void(0)" class="item-delete btn btn-mini btn-danger">delete</a></td></tr>')
				.appendTo("#list-items");
	});
}

function reindexTable() {
	var newIdx = 0;
	$("#list-items > tr").each(function(idx, tr) {
		$($(tr).find('td').get(0)).html(newIdx+1);
		$(tr).attr('id', newIdx++);
	});
}
