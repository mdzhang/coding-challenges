var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var ObjectId = Schema.ObjectId;

var _ = require('underscore');

// Dependencies
var ShoppingListItem = require("../models/ShoppingListItem");
var Group            = require("../models/Group");

// Schema
var ShoppingListSchema = new Schema({
    shoppingListItems     : [{ type: ObjectId, ref: 'ShoppingListItem' }]
  , group                 : { type: ObjectId, ref: 'Group' }
  , name                  : { type: String, required: true }
  , description           : String
});

// Validations

// Methods
ShoppingListSchema.methods = {

    /**
     * Add an item to the shopping list. Assumes shoppingListItem has already been validated.
     *
     * @param {Object} shoppingListItem
     * @param {Function} cb
     */
    addShoppingListItem: function(shoppingListItem, cb) {
        this.shoppingListItems.push(shoppingListItem._id);
        this.save(cb);
    },

    /**
     * Remove an item from the shopping list.
     *
     * @param {String} shoppingListItemId
     * @param {Function} cb
     */
    removeShoppingListItem: function(shoppingListItemId, cb) {
        // Remove the shoppingListItem whose id matches the one given from this shopping list's
        // array of shoppingListItems.
        this.shoppingListItems = _(this.shoppingListItems).filter(function(itemId) {
            return itemId != shoppingListItemId;
        });

        this.save(cb);
    },

    /**
     * Add a user to this shoppingList's group.
     */
    addUser: function(user, cb) {
        var _this = this;
        var group = this.group || new Group();
        group.addUser(user, function(err, group) {
            _this.group = group;
            _this.save(cb);
        });
    }
};

// Statics
ShoppingListSchema.statics = {

    /**
     * Get the shopping list with the given id.
     *
     * @param {String} id
     * @param {Function} cb
     */
    get: function (id, cb) {
        this.findById(id)
            .populate('shoppingListItems')
            .populate('group')
            .exec(cb);
    },

    /**
     * List shopping lists based on provided options.
     *
     * @param {Object} options e.g. { limit: 50, criteria: }
     * @param {Function} cb
     */
    list: function(options, cb) {

        // TODO: move to static variable
        var defaultOptions = { limit: 50, criteria: {} };

        options = _(defaultOptions).extend(options);

        // TODO: have find take in param to specify which fields to return

        this.find(options.criteria)
            .limit(options.limit)
            .populate('shoppingListItems')
            .populate('group', 'users')
            .exec(cb);
    }
};

module.exports = mongoose.model('ShoppingList', ShoppingListSchema);
