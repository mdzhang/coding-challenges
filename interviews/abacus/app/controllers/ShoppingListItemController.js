var mongoose = require('mongoose');
var _ = require('underscore');

// Dependencies
var Utils = require("../lib/Utils");
var ShoppingList = require("../models/ShoppingList");
var ShoppingListItem = require("../models/ShoppingListItem");

/**
 * Create a new shoppingListItem from the given params.
 */
exports.createJson = function(req, res) {
    var required = ['shoppingListId', 'name', 'quantity'];
    var optional = ['description']; // TODO: tags

    Utils.assertRequiredRequestQueryParams(req, required);

    ShoppingList.get(req.query.shoppingListId, function(err, shoppingList) {
        if (err) return res.json({ success: false, message: 'No shopping list found with that id' });

        var shoppingListItemParams = _(req.query).extend({ userAddedBy: req.user });
        var shoppingListItem = new ShoppingListItem(shoppingListItemParams);

        shoppingListItem.save(function(err) { // TODO: refactor save error handling
            if (err) return res.json({ success: false, message: err });

            shoppingList.addShoppingListItem(shoppingListItem, function(err) {
                if (err) return res.json({ success: false, message: 'Error adding to shopping list' });

                res.json({ success: true, 'shoppingListItem': shoppingListItem });
            });
        });
    });
};

/**
 * Get the shoppingListItem of the given id in JSON format.
 */
exports.getJson = function(req, res) {
    var required = ['id'];
    var optional = [];

    Utils.assertRequiredRequestQueryParams(req, required);

    ShoppingListItem.get(req.query.id, function(err, shoppingListItem) {
        if (err) return res.render('500'); // TODO: refactor

        res.json({ 'success': true, 'shoppingListItem': shoppingListItem });
    });
};

/**
 * Update the shoppingListItem of the given id.
 */
exports.updateJson = function(req, res) {
    var required = ['id'];
    var optional = ['name', 'quantity', 'description', 'purchased'];

    Utils.assertRequiredRequestQueryParams(req, required);

    ShoppingListItem.get(req.query.id, function(err, shoppingListItem) {
        shoppingListItem = _(shoppingListItem).extend(req.query);
        shoppingListItem.save(function(err) {
            if (err) return res.render('500'); // TODO: refactor

            res.json({ 'success': true, 'shoppingListItem': shoppingListItem });
        });
    });
};

// Delete

/**
 * List all shoppingListItems.
 */
exports.listJson = function(req, res) {
    var required = [];
    var optional = [];

    Utils.assertRequiredRequestQueryParams(req, required);

    // TODO: refactor to take in map specifying desired fields; have default map stored on model
    ShoppingListItem.list({}, function(err, shoppingListItems) {
        if (err) return res.render('500'); // TODO: refactor

        console.log('found shoppingListItems: ', shoppingListItems);

        shoppingListItems = _(shoppingListItems).filter(function(shoppingListItem) {
            return shoppingListItem.userAddedBy.id.toString() === req.user.id;
        });

        console.log('filtered shoppingListItems: ', shoppingListItems);

        res.json({ 'success': true, 'shoppingListItems': shoppingListItems });
    });
};
