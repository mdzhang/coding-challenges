var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var ObjectId = Schema.ObjectId;

// Dependencies

var TagSchema = new Schema({
    name    : { type: String, required: true } // TODO: unique: true
});

module.exports = mongoose.model('Tag', TagSchema);