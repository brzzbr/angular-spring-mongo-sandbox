/**
 * Created by borisbondarenko on 21.12.15.
 */

angular.module('pinmap', ['LocalStorageModule', 'ui.router', 'uiGmapgoogle-maps', 'ngResource', 'cgNotify'])
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
        // Send to login if the URL was not found
        $urlRouterProvider.otherwise('/home');

        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');
    })
    .run(function ($state, $rootScope, authService, navigationService) {

        $rootScope.$on('$stateChangeStart', function (event, toState) {

            if (toState.authenticate) {
                authService.authorize(function (authenticated) {
                    if (!authenticated) {
                        navigationService.goToLoginState();
                        event.preventDefault();
                    }
                });
            }
        });
    });