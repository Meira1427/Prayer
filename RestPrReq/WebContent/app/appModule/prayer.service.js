angular.module('prayerModule')
	.factory('prayerService', function($http, $filter){
		var service = {};
		
		service.index = function(){
			return $http({
				method : 'GET',
				url : 'api/prayers'
			})
		};
		
		return service;
	});