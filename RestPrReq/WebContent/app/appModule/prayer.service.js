angular.module('prayerModule')
	.factory('prayerService', function($http, $filter){
		var service = {};
		
		service.index = function(){
			return $http({
				method : 'GET',
				url : 'api/prayers'
			})
		};
		
		service.current = function(){
			console.log("calling prayer.service");
			return $http({
				method : 'GET',
				url : 'api/current'
			})
		};
		
		service.show = function(id) {
			return $http({
				method : 'GET',
				url : 'api/prayers' + id
			})
		};
		
		service.create = function(prayer) {
			return $http({
				method : 'POST',
				url : 'api/prayers',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : prayer
			})
		};
		
		service.update = function(id, prayer) {
			return $http({
				method : 'PUT',
				url : 'api/prayers/' + id,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : prayer
			})
		};

		service.destroy = function(id) {
			return $http({
				method : 'DELETE',
				url : 'api/prayers/' + id
			})
		};
		
		return service;
	});