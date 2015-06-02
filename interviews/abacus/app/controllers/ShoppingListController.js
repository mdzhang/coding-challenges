// Modules
var mongoose = require('mongoose');
var _ = require('underscore');

// Dependencies
var Utils        = require("../lib/Utils");
var ShoppingList = require("../models/ShoppingList");
var Group        = require("../models/Group");

/**
 * Create a new shoppingList from the given params.
 */
exports.createJson = function(req, res) {
    var required = ['name'];
    var optional = [];

    Utils.assertRequiredRequestQueryParams(req, required);

    // TODO: pluck required params (static var on shoppingList) from req.query
    var shoppingList = new ShoppingList(req.query);

    shoppingList.save(function (err) {
        if (err) return res.json({ success: false, err: err });

        shoppingList.addUser(req.user, function(err, group) {
            res.json({ success: true, 'shoppingList': shoppingList });
        });
    });
};

/**
 * Get the shoppingList of the given id in JSON format.
 */
exports.getJson = function(req, res) {
    var required = ['id'];
    var optional = [];

    Utils.assertRequiredRequestQueryParams(req, required);

    ShoppingList.get(req.query.id, function(err, shoppingList) {
        if (err) return res.render('500'); // TODO: refactor

        res.json({ 'success': true, 'shoppingList': shoppingList });
    });
};

// Update (Edit)

// Delete

/**
 * List all shopping lists.
 */
exports.listJson = function(req, res) {
    var required = [];
    var optional = [];

    Utils.assertRequiredRequestQueryParams(req, required);

    // TODO: refactor to take in map specifying desired fields; have default map stored on model
    var options = { criteria: {} };

    ShoppingList.list(options.criteria, function(err, shoppingLists) {
        if (err) return res.render('500');

        // TODO: move into mongoose nested query
        // TODO: reduce to one pass
        shoppingLists = _(shoppingLists).filter(function(shoppingList) {
            var userIds = _(shoppingList.group.users).map(function(uid) {
                console.log('uid: ', uid);
                return uid.toString();
            });
            return _(userIds).contains(req.user.id);
        });

        res.json({ 'success': true, 'shoppingLists': shoppingLists });
    });
};
