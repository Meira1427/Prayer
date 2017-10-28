angular.module('prayerModule')
	.component('enterPrayer', {
		templateUrl: 'app/appModule/enterPrayer/enterPrayer.component.html',
		
		controller: function($location, $routeParams){
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