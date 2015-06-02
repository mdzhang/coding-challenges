/**
 * Main app logic code.
 *
 * Web site consists of a number of API calls, but only a single client-facing angular web app.
 * When a user visits the site, they'll be redirected to '/', which will load the index.jade file
 * from app/views, which will in turn start up this angular app.
 */

// TODO: create client side service for automating API calls/data extraction

var groupShoppingListApp = angular.module('groupShoppingListApp', ['ui.router']);

groupShoppingListApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    // Default url.
    $urlRouterProvider
        .otherwise('/shoppingList/list');

    $stateProvider
        .state('/shoppingList/list', {
            url: '/shoppingList/list',
            templateUrl: 'partials/shoppingList/list.html',
            controller: 'ManageShoppingListsCtrl'
        })
            .state('/shoppingList/view', {
                url: '/shoppingList/view',
                templateUrl: 'partials/shoppingList/view.html',
                controller: 'ShoppingListCtrl',
                params: { shoppingList: {} }
            })
            .state('/shoppingList/create', {
                url: '/shoppingList/create',
                templateUrl: 'partials/shoppingList/create.html',
                controller: 'ShoppingListCtrl',
                params: {}
            })
        .state('/shoppingListItem/create', {
            url: '/shoppingListItem/create',
            templateUrl: 'partials/shoppingListItem/create.html',
            controller: 'ShoppingListItemCtrl',
            params: { shoppingListItem: {}, shoppingList: {} }
        });

}]);
