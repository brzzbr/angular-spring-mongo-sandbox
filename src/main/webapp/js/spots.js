angular.module('pinmap', [ 'ngRoute' ]).config(function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).otherwise('/');

}).controller('navigation',

function($rootScope, $scope, $http, $location, $route) {

	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	var authenticate = function(callback) {

		$http.get('user').success(function(data) {
			$rootScope.authenticated = !!data.name;
			callback && callback();
		}).error(function() {
			$rootScope.authenticated = false;
			callback && callback();
		});

	};

	authenticate();

	$scope.credentials = {};
	$scope.login = function() {
		$http.post('login', $.param($scope.credentials), {
			headers : {
				"content-type" : "application/x-www-form-urlencoded"
			}
		}).success(function () {
			authenticate(function() {
				if ($rootScope.authenticated) {
					console.log("Login succeeded");
					$location.path("/");
					$scope.error = false;
					$rootScope.authenticated = true;
				} else {
					console.log("Login failed with redirect");
					$location.path("/login");
					$scope.error = true;
					$rootScope.authenticated = false;
				}
			});
		}).error(function () {
			console.log("Login failed");
			$location.path("/login");
			$scope.error = true;
			$rootScope.authenticated = false;
		})
	};

	$scope.logout = function() {
		$http.post('/logout', {}).success(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		}).error(function () {
			console.log("Logout failed");
			$rootScope.authenticated = false;
		});
	}

}).controller('home', function($scope, $http) {
	$http.get('http://localhost:7788').success(function(data) {
		$scope.greeting = data;
	})
});
