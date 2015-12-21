/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .service('navigationService', ['$window', '$state', navigationService]);

    function navigationService($window, $state) {

        return{
            goToPreviousState: goToPreviousState,
            goToDefaultState: goToDefaultState,
            goToLoginState: goToLoginState
        };

        function goToPreviousState() {
            /**
             * Вытягиваем структуру предыдущего состояния.
             * Формат: { state: string, params: object }
             */
            var returnParams = $window.localStorage.getItem('returnParams');
            // Удаляем информацию о предыдущем состоянии
            $window.localStorage.removeItem('returnParams');
            // Пробуем преобразовать то что вытащили в JSON object.
            // Если это не получилось, то идём в стандартное состояние
            var defaultState = getDefaultState();
            try {
                returnParams = JSON.parse(returnParams);
                // Вытаскиваем наименование предыдущего состояния
                var state = returnParams && !returnParams.state ? defaultState.state : returnParams.state;
                // Вытаскиваем параметры предыдущего состояния
                // В случае если предыдущего состояния нет, то параметры будут пустыми
                var params = returnParams && returnParams.state && returnParams.params ? returnParams.params : {};
                // Мы идём с экрана логина или блокировки - незачем возвращаться на них или на logout
                if (state === 'login' || state === 'logout') {
                    //получаем state по умолчанию
                    $state.go(defaultState.state, defaultState.params);
                } else {

                    $state.go(state, params);
                }
            } catch (e) {

                $state.go(defaultState.state, defaultState.params);
            }
        }

        function goToDefaultState(){
            var defaultState = getDefaultState();
            $state.go(defaultState.state, defaultState.params);
        }

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