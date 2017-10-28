angular.module('prayerModule')
	.factory('quoteService', function($http, $filter){
		var service = {};
		
		service.index = function(){
			return $http({
				method : 'GET',
				url : 'api/quotes'
			})
		};
		
		return service;
	});