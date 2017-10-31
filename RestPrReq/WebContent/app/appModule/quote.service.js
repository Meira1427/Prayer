angular.module('prayerModule')
	.factory('quoteService', function($http, $filter){
		var service = {};
		
		//returns all quotes
		service.indexOfQuote = function(){
			return $http({
				method : 'GET',
				url : 'api/quotes'
			})
		};
		
		//returns quotes for specific keyword
		service.indexByKeyword = function(keyword){
			return $http({
				method : 'GET',
				url : 'api/quotes/keywords/' + keyword
			})
		};
		
		//returns all keywords
		service.indexOfKeyword = function(){
			return $http({
				method : 'GET',
				url : 'api/keywords'
			})
		};
		
		service.show = function(id) {
			return $http({
				method : 'GET',
				url : 'api/quotes' + id
			})
		};
		
		service.create = function(quote) {
			return $http({
				method : 'POST',
				url : 'api/quotes',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : quote
			})
		};
		
		service.update = function(id, quote) {
			return $http({
				method : 'PUT',
				url : 'api/quotes/' + id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : quote
			})
		};

		service.destroy = function(id) {
			return $http({
				method : 'DELETE',
				url : 'api/quotes/' + id
			})
		};
		
		return service;
	});