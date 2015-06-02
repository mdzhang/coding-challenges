// TODO: separate into separate controller files; edit layout.jade (static) or app.js (dynamic) to load files in

// Manages view of all shopping lists.
(function() {

    ManageShoppingListsCtrl.$inject = ['$scope', '$http', '$log', '$state', '$stateParams'];

    function ManageShoppingListsCtrl($scope, $http, $log, $state, $stateParams) {

        $scope.shoppingList = null;
        $scope.shoppingLists = null;

        // Select a shopping list to edit/view/etc.
        $scope.selectShoppingList = function(shoppingList) {
            $state.go('/shoppingList/view', { shoppingList: shoppingList }, { location: true, inherit: false });
        };

        // Create a brand new shopping list.
        $scope.addShoppingList = function() {
             $state.go('/shoppingList/create', { location: true, inherit: false });
        };

        // Load in all shopping lists that belong to this user.
        function _getShoppingLists() {
            $http.get('/shoppingList/listJson')
                .success(function(data) {
                    if (data.success) {
                        $scope.shoppingLists = data.shoppingLists;
                    }
                    else {
                        // TODO: throw up a flash message
                    }
                })
                .error(function(error) {
                    // TODO: throw up a flash message
                });
        }

        function _init() {
            // TODO: this is called _every_ time someone returns to this page, regardless of whether or not
            //       they've been here before in this same session
            _getShoppingLists();
        }

        _init();
    }

    groupShoppingListApp.controller('ManageShoppingListsCtrl', ManageShoppingListsCtrl);
})();

// Manage a single shopping list.
(function() {

    ShoppingListCtrl.$inject = ['$scope', '$http', '$log', '$state', '$stateParams'];

    function ShoppingListCtrl($scope, $http, $log, $state, $stateParams) {

        $scope.shoppingList = null;
        $scope.showPurchasedItems = false;

        // Add an item to this shopping list.
        $scope.addShoppingListItem = function() {
            $state.go('/shoppingListItem/create', { shoppingList: $scope.shoppingList }, { location: true, inherit: false });
        };

        $scope.createAndAddShoppingList = function() {
            $http.get('/shoppingList/createJson', { params: $scope.shoppingList })
                .success(function(data) {
                    if (data.success) {
                        $scope.shoppingList = _($scope.shoppingList).extend(data.shoppingList);
                        $scope.goBackToShoppingListsView();
                    }
                    else {
                        // TODO: throw up a flash message
                    }
                })
                .error(function(error) {
                    // TODO: throw up a flash message
                });
        };

        // Return to the list of all shopping lists for the current user.
        $scope.goBackToShoppingListsView = function() {
             $state.go('/shoppingList/list', null, { location: true, inherit: false });
        };

        // Toggle whether or not show already or not yet purchased items.
        $scope.switchPurchaseFilter = function(value) {
            $scope.showPurchasedItems = value;
        };

        // When a user clicks the quantity next to a shopping list item, it is understood that they've
        // purchased another one of those items. Once they've purchased all they need, we should mark it
        // as already purchased.
        $scope.markOnePurchased = function(shoppingListItem) {
            // Don't decrement the quantity if they're already at or below 0.
            if (shoppingListItem.quantity > 0) {
                shoppingListItem.quantity -= 1;
            }

            // If they've hit quantity 0, everything has been purchased.
            if (shoppingListItem.quantity <= 0) {
                shoppingListItem.purchased = true;
            }

            // TODO: neater way to handle _id v id
            shoppingListItem.id = shoppingListItem._id;

            $http.get('/shoppingListItem/updateJson', { params: shoppingListItem })
                .success(function(data) {
                    if (!data.success) {
                        // TODO: throw up a flash message
                    }
                })
                .error(function(err) {
                    // TODO: throw up a flash message
                });
        };

        function _init() {
            // Try and pull the shopping list off stateParams.
            $scope.shoppingList = $stateParams.shoppingList || null;

            // TODO: if not found, go back to list of all shopping lists and throw up a flash message
        }

        _init();
    }

    groupShoppingListApp.controller('ShoppingListCtrl', ShoppingListCtrl);
})();

// Manage a single shopping list item.
(function() {
    ShoppingListItemCtrl.$inject = ['$scope', '$http', '$log', '$state', '$stateParams'];

    function ShoppingListItemCtrl($scope, $http, $log, $state, $stateParams) {

        $scope.shoppingListItem = null;

        $scope.goBackToShoppingListView = function() {
            $state.go('/shoppingList/view', { shoppingList: $scope.shoppingList }, { location: true, inherit: false });
        };

        $scope.createAndAddShoppingListItem = function() {

            var requestParams = _($scope.shoppingListItem).extend({ shoppingListId: $scope.shoppingList._id });

            $http.get('/shoppingListItem/createJson', { params: $scope.shoppingListItem })
                .success(function(data) {
                    if (data.success) {
                        var shoppingListItem = data.shoppingListItem;
                        $scope.shoppingList.shoppingListItems.push(shoppingListItem);
                        $scope.goBackToShoppingListView();
                    }
                    else {
                        // TODO: throw up a flash message
                    }
                })
                .error(function(err) {
                    // TODO: throw up a flash message
                });
        };

        function _init() {
            // Pull the shopping list off stateParams. Notable since we need to keep track of its id
            // for shoppingListItem/createJson calls.
            $scope.shoppingList = $stateParams.shoppingList || null;

            // Initialize to default value.
            $scope.shoppingListItem = {
                 name: '',
                 description: '',
                 quantity: 1,
                 purchased: false,
                 //tags: []
            };
        }

        _init();
    }

    groupShoppingListApp.controller('ShoppingListItemCtrl', ShoppingListItemCtrl);
})();