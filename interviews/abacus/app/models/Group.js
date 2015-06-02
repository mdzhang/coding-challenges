var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var ObjectId = Schema.ObjectId;

// Dependencies
var User = require("../models/User");

var GroupSchema = new Schema({
    users : [{ type: ObjectId, ref: 'User' }]
});

GroupSchema.methods = {

    /**
     * Add a user to the group.
     *
     * @param user
     * @param cb
     */
    addUser: function(user, cb) {
        this.users.push(user.id);
        this.save(cb);
    }

};

module.exports = mongoose.model('Group', GroupSchema);