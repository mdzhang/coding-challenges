// Dependencies
var Utils            = require("../app/lib/Utils");
var ShoppingList     = require("../app/models/ShoppingList");
var ShoppingListItem = require("../app/models/ShoppingListItem");

// Call before allowing a user to make any calls where they must be authenticated.
exports.requiresLogin = function (req, res, next) {
    if (req.isAuthenticated()) {
      return next();
    }
    res.redirect('/user/login')
};

// ShoppingList Auth
exports.shoppingList = {
    hasAuthorization: function(req, res, next) {
        ShoppingList.get({ _id: req.user.id }, function(err, shoppingList) {
            if (err) return req.flash({ success: false, err: err });

            if (shoppingList && shoppingList.isUserInGroup(req.user)) {
                return next();
            }

            req.flash({ success: false, err: "You're not allowed to change this shopping list."})
        });
    }
};

// ShoppingListItem Auth
exports.shoppingListItem = {
    hasAuthorization: function (req, res, next) {
        next();
        // TODO
    }
};