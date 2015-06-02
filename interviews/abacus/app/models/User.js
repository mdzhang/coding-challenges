var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var ObjectId = Schema.ObjectId;

// Dependencies
var _ = require('underscore');

var UserSchema = new Schema({
    name: { type: String, default: '' },        // e.g. Michelle Z
    email: { type: String, default: '' },       // e.g. zhang.michelle.d@gmail.com
    username: { type: String, default: '' },    // e.g. mdzhang
    provider: { type: String, default: '' },    // e.g. github
    github: {}
});

// Statics
UserSchema.statics = {};

module.exports = mongoose.model('User', UserSchema);