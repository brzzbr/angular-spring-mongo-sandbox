/**
 * Created by borisbondarenko on 29.12.15.
 *
 * @description
 * A which helps to handle enter-key press in the controll.
 *
 * @author brzzbr
 */
(function () {

    angular.module('pinmap')
        .directive('ngEnter', function () {
            return function (scope, element, attrs) {
                element.bind("keydown keypress", function (event) {
                    if (event.which === 13) {
                        scope.$apply(function () {
                            scope.$eval(attrs.ngEnter);
                        });

                        event.preventDefault();
                    }
                });
            };
        });
})();