var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var ObjectId = Schema.ObjectId;

var _ = require('underscore');

// Dependencies
var User = require("../models/User");
var Tag  = require("../models/Tag");

// Schema
var ShoppingListItemSchema = new Schema({
    userAddedBy   : { type: ObjectId, ref: 'User', required: true }
  , name          : { type: String, required: true }
  , description   : String
  , quantity      : { type: Number, min: 0 }
  , purchased     : { type: Boolean, default: false }
  , tags          : [{ type: ObjectId, ref: 'Tag' }]
});

// Validations

// Methods
ShoppingListItemSchema.methods = {
};

// Statics
ShoppingListItemSchema.statics = {

    /**
     * Get the shopping list item with the given id.
     *
     * @param {String} id
     * @param {Function} cb
     */
    get: function (id, cb) {
        this.findById(id)
            .populate('userAddedBy')
            .exec(cb);
    },

    /**
     * List shopping list items based on provided options.
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
            .populate('userAddedBy')
            .exec(cb);
    }
};

module.exports = mongoose.model('ShoppingListItem', ShoppingListItemSchema);