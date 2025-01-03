var app = angular.module("app", [
    // 'ui.router'
]);

/*var app = angular.module("app", [
    'ui.router',
    'app.home'
    /!*'app.menu',
    'app.search'*!/
])*/

    app.controller('AppController',function () {
        // console.log(123334567765)
    });

/*app.config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');
        // $urlRouterProvider.when('/users', '/users/list');
        // $urlRouterProvider.when('/flat-owners', '/flat-owners/list');

        $stateProvider
            .state('/', {
                url: '/',
                templateUrl: 'index.html',
                controller: 'APPController'
            })
           .state('home', {
                url: '/home',
                templateUrl: 'components/templates/home.html',
                controller: 'HomeController'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'components/templates/login.html',
                controller: 'LoginController'
            })
            .state('registration', {
                url: '/registration',
                templateUrl: 'components/templates/registration.html',
                controller: 'RegistrationController'
            })
            .state('privateAccount', {
                url: '/privateAccount',
                templateUrl: 'components/templates/privateAccount.html',
                controller: 'PrivateAccountController'
            })
            .state('search', {
                url: '/search',
                templateUrl: 'components/templates/search.html',
                controller: 'SearchController'
            })
            .state('basket', {
                url: '/basket',
                templateUrl: 'components/templates/basket.html',
                controller: 'BasketController'
            })
            .state('help', {
                url: '/help',
                templateUrl: 'components/templates/help.html',
                controller: 'HelpController'
            })
    }
);*/


