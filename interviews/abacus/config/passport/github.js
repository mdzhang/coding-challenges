// Modules
var mongoose = require('mongoose');
var passport = require('passport');

// Dependencies
var User = require("../../app/models/User");

var GITHUB_CLIENT_ID = 'b8129bda378e487e84f8';
var GITHUB_CLIENT_SECRET = 'c70a8e3304cf7fb807eee94d01f5ce60f11cd614';
var GitHubStrategy = require('passport-github').Strategy;

module.exports = (new GitHubStrategy({
        clientID: GITHUB_CLIENT_ID,
        clientSecret: GITHUB_CLIENT_SECRET,
        callbackURL: 'http://127.0.0.1:8080/auth/github/callback'
    },
    function(accessToken, refreshToken, profile, done) {

        var options = {
            criteria: {
                'github.id': profile.id
            },
            newUserInfo: {
                name: profile.displayName,
                email: profile.emails[0].value,
                username: profile.username,
                provider: 'github',
                github: profile._json
            }
        };

        User.findOne(options.criteria, function(err, user) {
            if (err) return done(err);

            // If we couldn't find the user, create and save a new one.
            if (!user) {
                user = new User(options.newUserInfo);

                user.save(function(err) {
                    return done(err, user);
                });
            } else {
                return done(err, user);
            }
        });
    }
));