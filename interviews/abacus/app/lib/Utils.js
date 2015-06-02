var mongoose = require('mongoose');

// Dependencies
var _ = require('underscore');

/**
 * Convert the given string id to an Mongoose ObjectId.
 *
 * @param {String} id
 * @return {ObjectId} objectId
 */
exports.toObjectId = function(id) {
    return mongoose.Types.ObjectId(id);
};

/**
 * Check that all params listed in the required array are included in req.query.
 * Further, check that the values of each param is neither null nor empty.
 *
 * @param [Array] required
 */
exports.assertRequiredRequestQueryParams = function(req, required) {
    // TODO: should throw on error; API calls that include it should be wrapped in function that catches
    //       thrown errors and renders success failed JSON
};


// TODO: function to convert e.g. params map to nested object structure

