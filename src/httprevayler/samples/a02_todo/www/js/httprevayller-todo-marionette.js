'use strict';

var TodoApp = new Backbone.Marionette.Application();

TodoApp.addRegions({
	itemAddRegion: '#item-add',
	itemsListRegion: '#items-table',
});

TodoApp.on('start', function () {
	Backbone.history.start();
});


TodoApp.module('Models', function (Models, App, Backbone) {
	
	Models.Todo = Backbone.Model.extend({
		idAttribute: "id",
		defaults: {
			description: ''
		}
	});

	Models.TodoList = Backbone.Collection.extend({
		model: Models.Todo,
		url: '/items',
		
		initialize: function() {
			this.on("add", this.onAdd);
			this.on("remove", this.onReorder);
			this.on("reorder", this.onReorder);
		},
		
		move: function(from, to) {
			var model = this.at(from);
			this.remove(model);
			this.add(model, {at:to});
			this.trigger('reorder');
		},
		
		onAdd: function(item) {
			item.set("counter", this.indexOf(item) + 1);
		},
		
		onReorder: function() {
			var counter = 1;
			this.each(function(item, id) {
				item.set("counter", counter++);
			});
		}
	});
	
});


TodoApp.module('Views', function (Views, App, Backbone, Marionette, $) {

	Views.ItemAddView = Marionette.ItemView.extend({
		template: '#item-add-template',
		
		ui: {
			description: '#input-description'
		},
		
		events: {
			'click #btn-add': 'onAdd',
		},
	
		onAdd: function(a) {
			var todoText = this.ui.description.val().trim();

			if (todoText) {
				this.collection.create({
					description: todoText
				});
				this.ui.description.val('');
			}
		}
	});
	
	Views.ItemView = Marionette.ItemView.extend({
		tagName: 'tr',
		template: '#item-template',
		
		ui: {
			delete: '.item-delete'
		},
		
		events: {
			'click .item-delete': 'onDelete',
		},
		
		initialize: function(){
			this.setAttrId();
			this.model.on('change', this.render, this);
			this.model.on('change', this.setAttrId, this);
		},
		
		setAttrId: function() {
			this.$el.attr('cid', this.model.cid);
			this.$el.attr('id', this.model.id);
		},
		
		onDelete: function() {
			this.model.destroy({success: function(model, response) {
				console.log('deleted!');
			}});
		}
	});

	Views.ListView = Marionette.CollectionView.extend({
		tagName: 'tbody',
		itemView: Views.ItemView,
		
		initialize: function() {
			var _this = this;
			this.collection.on('reorder', this.render, this);
			this.$el = this.$el.sortable({
				helper: function(e, ui) {
					ui.children().each(function() {
						$(this).width($(this).width());
					});
					return ui;
				},
				update: function(e, ui) { _this.selectionUpdate(e, ui); }
			});
		},
		
		selectionUpdate: function(e, ui) {
			var cid = ui.item.attr('cid');
			var model = this.collection.get(cid);
			var from = this.collection.indexOf(model);
			var arrayIndexes = this.$el.sortable("toArray");
			var to = arrayIndexes.indexOf(ui.item.attr('id'));
			this.collection.move(from, to);
			var to = this.collection.indexOf(model);
			
			//console.log(JSON.stringify({"id":parseInt(from), "to_position":parseInt(to)}));
			
			//TODO: refactor to use backbone/marionette
			$.postJSON("/items_sort", JSON.stringify({"from":parseInt(from), "to":parseInt(to)}));
		}		
	});

});

TodoApp.module('Controller', function (TodoList, App, Backbone, Marionette, $, _) {
	
	TodoList.Controller = function () {
		this.todoList = new App.Models.TodoList();
	};

	_.extend(TodoList.Controller.prototype, {
		start: function () {
			this.showTodoList(this.todoList);
			this.todoList.fetch();
		},
		
		showTodoList: function (todoList) {
			App.itemAddRegion.show(new App.Views.ItemAddView({
				collection: todoList
			}));
			App.itemsListRegion.show(new App.Views.ListView({
				collection: todoList
			}));
		}
	});
	
	TodoList.addInitializer(function (options) {
		App.controller = new TodoList.Controller(options);
		App.controller.start();
	});
});
