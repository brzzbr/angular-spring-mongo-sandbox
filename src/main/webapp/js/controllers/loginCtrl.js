/**
 * Created by borisbondarenko on 21.12.15.
 */
(function () {

    angular.module('pinmap')
        .controller('loginCtrl', ['authService', 'navigationService', loginCtrl]);

    function loginCtrl(authService, navigationService) {

        var thisCtrl = this;

        // Имя пользователя
        thisCtrl.username = '';
        // Пароль
        thisCtrl.password = '';
        // Сообщение об ошибке
        thisCtrl.message = '';
        // Нажатие кнопки Войти
        thisCtrl.login = login;

        function login(event) {
            event.preventDefault();
            authService.login({
                username: username,
                password: password
            }).then(function () {

                thisCtrl.message = '';
                navigationService.goToDefaultState();

            }).catch(function () {
                thisCtrl.message = 'Wrong credentials!';
            });
        }
    }
})();