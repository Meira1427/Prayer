angular.module("authModule").component("register", {
	templateUrl : "app/authModule/register/register.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies) {
		var vm = this; 
		
		vm.register = function(user) {
			console.log("inside register")
			console.log(user);
			console.log(authService.getToken().id);
			console.log(user.id);
			
			authService.register(user)
			.then(function(response){
				$location.path("/users/"+ authService.getToken().id
						+"/registerprofiles");
			})
		}
	},
			
			controllerAs: "vm"
	})
	


