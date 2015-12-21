/**
 * Created by borisbondarenko on 21.12.15.
 */

angular.module('pinmap', ['LocalStorageModule', 'ui.router'])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'home.html',
                controller: 'homeCtrl',
                controllerAs: 'ctrl',
                authenticate: true
            })
            .state('login', {
                url: '/login',
                templateUrl: 'login.html',
                controller: 'loginCtrl',
                controllerAs: 'ctrl',
                authenticate: false
            });

        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');

        // Send to login if the URL was not found
        $urlRouterProvider.otherwise('/home');
    })
    .run(function ($state, $rootScope, authService) {

        $rootScope.$on('$stateChangeStart', function (event, toState) {

            if (toState.authenticate) {
                authService.authenticate(function (authenticated) {
                    if (!authenticated) {
                        authService.redirectToLogin();
                        event.preventDefault();
                    }
                });
            }
        });
    });