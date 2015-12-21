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

        function login() {

            authService.login(thisCtrl.username, thisCtrl.password, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded");
                    navigationService.goToPreviousState();
                } else {
                    console.log("Login failed");
                    thisCtrl.message = "Wrong credentials!";
                }
            });
        }
    }
})();