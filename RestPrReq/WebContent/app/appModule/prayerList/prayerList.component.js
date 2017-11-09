angular.module('prayerModule')
	.component('prayerList', {
		templateUrl: 'app/appModule/prayerList/prayerList.component.html',
		
		controller: function(prayerService, $filter, $location, $routeParams, $scope){
			var vm = this;
			
			vm.currentRequests = [];
			
			var getRequests = function() {
				prayerService.current()
				.then(function(resp){
					console.log(resp.data);
					vm.currentRequests = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}
			
			getRequests();
			
			vm.newRequest = function(prayer){
				console.log(prayer);
				prayerService.create(prayer);
				getRequests();
			};
		
		},

		controllerAs: 'vm'	
	});