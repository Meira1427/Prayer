angular.module('prayerModule')
	.component('quote', {
		templateUrl: 'app/appModule/quote/quote.component.html',
		
		controller: function(quoteService, $filter, $location, $routeParams){
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