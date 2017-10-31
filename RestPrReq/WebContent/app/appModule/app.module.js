angular.module('prayerModule', ['ngRoute'])
.config(function($routeProvider){
	console.log("in prayerModule");
	$routeProvider
	.when('/', {
		template: '<quote></quote>'
	})
	.when('/quote', {
		template: '<quote></quote>'
	})
	.when('/prayer', {
		template: '<prayer-list></prayer-list>'
	})
	.when('/current', {
		template: '<prayer-list></prayer-list>'
	})
	.when('/enter-prayer', {
		template: '<enter-prayer></enter-prayer>'
	})
	.otherwise({
		template: '<not-found></not-found>'
	})
});