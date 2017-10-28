angular.module('prayerModule')
	.component('prayerList', {
		templateUrl: 'app/appModule/prayerList/prayerList.component.html',
		
		controller: function(prayerService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.getBackgroundImage = function() {
				switch($location.path()) {
					case '/':
						return 'quotebg';
					case '/quote':
						return 'quotebg';
					case '/prayer':
						return 'prayerbg';
					default:
						return 'quotebg';
				}
			}
		},

		controllerAs: 'vm'	
	});