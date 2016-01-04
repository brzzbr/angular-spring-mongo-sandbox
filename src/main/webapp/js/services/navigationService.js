/**
 * Created by borisbondarenko on 21.12.15.
 *
 * @description
 * Primitive navigation service that allows to roam among two
 * states -- login and default.
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .service('navigationService', ['$state', navigationService]);

    function navigationService($state) {

        return{
            goToDefaultState: goToDefaultState,
            goToLoginState: goToLoginState
        };

        /**
         * go to default state with map and pins
         */
        function goToDefaultState(){
            var defaultState = getDefaultState();
            $state.go(defaultState.state, defaultState.params);
        }

        /**
         * go to login page
         */
        function goToLoginState(){
            $state.go('login');
        }

        function getDefaultState() {
            //параметры по умолчанию
            return {
                state: 'home',
                params: {}
            }
        }

    }
})();