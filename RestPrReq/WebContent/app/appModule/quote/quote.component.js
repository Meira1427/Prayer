angular.module('prayerModule')
	.component('quote', {
		templateUrl: 'app/appModule/quote/quote.component.html',
		
		controller: function(quoteService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.keywords = [];
			vm.relevantQuotes = [];
			vm.currentQuote = null;
			vm.selectedWord = null;
			
			var getKeywords = function(){
				quoteService.indexOfKeyword()
				.then(function(res){
					console.log(res.data);
					vm.keywords = res.data;
				})
				.catch(function(err){
					console.log(err);
				});
			};
			
			getKeywords();
			
			vm.getAQuote = function(){
				console.log('entering getAQuote')
				quoteService.indexByKeyword(vm.selectedWord)
				.then(function(res){
					console.log("no error:" + res.data);
					vm.relevantQuotes = res.data;
					var num = Math.floor(Math.random()*vm.relevantQuotes.length);
					console.log(vm.relevantQuotes[num]);
					console.log("Random Num is " + num);
					vm.currentQuote = vm.relevantQuotes[num];
				})
				.catch(function(err){
					console.log(err);
				})
			};
			
			
		
		},

		controllerAs: 'vm'	
	});