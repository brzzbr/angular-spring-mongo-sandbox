/**
 * Unit-tests for login page controller
 */
describe('LoginController tests', function () {

    var correctUsername = 'username';
    var correctPassword = 'password';
    var incorrectPassword = 'foo';

    beforeEach(module('pinmap'));

    var mockAuthService = {
        login: function (credentials, callback) {
            if (credentials.username === correctUsername &&
                credentials.password === correctPassword) {
                return callback();
            }
            else {
                return callback('error');
            }
        }
    };

    var mockNavigationService = {
        goToDefaultState: function(){}
    };

    /**
     * With correct credentials user proceeds with login
     */
    it('should correctly login',
        inject(function ($rootScope, $controller) {

            // Arrange
            var ctrl = $controller('loginCtrl', {
                authService: mockAuthService,
                navigationService: mockNavigationService
            });
            // Имя пользователя
            ctrl.username = correctUsername;
            // Пароль
            ctrl.password = correctPassword;

            // Act
            ctrl.login();

            // Assert
            expect(ctrl.message).toEqual('');
        })
    );

    /**
     * With incorrect credentials user wouldn't login
     */
    it('should fail login',
        inject(function ($rootScope, $controller) {

            // Arrange
            var ctrl = $controller('loginCtrl', {
                authService: mockAuthService,
                navigationService: mockNavigationService
            });
            // Имя пользователя
            ctrl.username = correctUsername;
            // Пароль
            ctrl.password = incorrectPassword;

            // Act
            ctrl.login();

            // Assert
            expect(ctrl.message).not.toEqual('');
        })
    );
});