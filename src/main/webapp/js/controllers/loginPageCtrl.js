/**
 * Created by borisbondarenko on 21.12.15.
 *
 * @description
 * A controller for a login page.
 *
 * @author brzzbr
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

        function login() {

            authService.login({
                username: thisCtrl.username,
                password: thisCtrl.password
            }, function (err) {

                if (err) {
                    thisCtrl.message = 'Wrong credentials!';
                }
                else {
                    thisCtrl.message = '';
                    navigationService.goToDefaultState();
                }

            });
        }
    }
})();