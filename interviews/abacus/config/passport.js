// Modules
var passport = require('passport');

// Dependencies
var User = require('../app/models/User');

// After a user has been logged in and authenticated, determine what properties should be stored on the session,
// namely, on req.user. We store the id for easy de/serialization.
passport.serializeUser(function(user, done) {
    done(null, { id: user._id, username: user.username });
});

// When a user makes a request to the server, we've serialized them so we only pass back and forth their
// id and username, so to get additional data we find the user by their id in the database.
// TODO: wanted to use findById, but that was returning an array instead of a single object
passport.deserializeUser(function(serializedUser, done) {
    User.findOne({ _id: serializedUser.id }, function (err, user) {
        done(err, user);
    });
});

var github = require('./passport/github');
passport.use(github);

var google = require('./passport/google');
passport.use(google);