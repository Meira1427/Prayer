angular.module('prayerModule')
	.component('prayerList', {
		templateUrl: 'app/appModule/prayerList/prayerList.component.html',
		
		controller: function(prayerService, $filter, $location, $routeParams, $scope){
			var vm = this;
			
			vm.currentRequests = [];
			
			var getRequests = function() {
				console.log("entering getRequests")
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
			
			vm.returnNgClass = function(num){
				return "box " + num;
			}
		
		
		
		},

		controllerAs: 'vm'	
	});