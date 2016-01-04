// Karma configuration
// Generated on Wed Feb 25 2015 12:59:44 GMT+1000 (RTZ 9 (зима))

module.exports = function (config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '',

        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        // Подключать всё необходимо именно в том порядке, в котором они описаны в index.html
        files: [
            // Подключаем все необходимые библиотеки
            'lib/jquery/dist/jquery.js',
            'lib/lodash/lodash.js',
            'lib/angular/angular.js',
            'lib/angular-simple-logger/dist/angular-simple-logger.js',
            'lib/angular-google-maps/dist/angular-google-maps.js',
            'lib/angular-local-storage/dist/angular-local-storage.js',
            'lib/angular-notify/dist/angular-notify.js',
            'lib/angular-resource/angular-resource.js',
            'lib/angular-ui-router/release/angular-ui-router.js',
            'lib/bootstrap/dist/js/bootstrap.js',
            // Инжектор для тестов
            'node_modules/angular-mocks/angular-mocks.js',
            // Исходники нашего основного приложения
            'js/pinmap.js',
            'js/controllers/homePageCtrl.js',
            'js/controllers/loginPageCtrl.js',
            'js/directives/ngEnter.js',
            'js/factories/authInterceptor.js',
            'js/dependencies/date-format.js',
            'js/services/authService.js',
            'js/services/navigationService.js',
            'js/services/notifyService.js',
            'js/services/pinService.js',
            'js/services/subService.js',
            'js/services/userService.js',
            'js/services/xAuthProvider.js',
            '../../test/js/unit/**/*.js'
        ],

        // list of files to exclude
        exclude: [],

        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {
            'src/app/**/*.js': ['coverage']
        },

        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress'],

        // web server port
        port: 9876,

        // enable / disable colors in the output (reporters and logs)
        colors: true,

        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO ||
        // config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // start these browsers
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: false,

        plugins: [
            'karma-phantomjs-launcher',
            'karma-jasmine',
            'karma-coverage',
            'karma-junit-reporter'
        ]
    });
};
