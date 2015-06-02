// Modules
var mongoose = require('mongoose');
var passport = require('passport');

// Dependencies
var User = require("../../app/models/User");

var GOOGLE_CLIENT_ID = '549143509739-96p3svhavujrnnu81nni9mvuu1tr3qqd.apps.googleusercontent.com';
var GOOGLE_CLIENT_SECRET = 'bZaLrATvYm32-sT3Q7RWNyAF';
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;

module.exports = (new GoogleStrategy({
        clientID: GOOGLE_CLIENT_ID,
        clientSecret: GOOGLE_CLIENT_SECRET,
        callbackURL: 'http://127.0.0.1:8080/auth/google/callback'
    },
    function(accessToken, refreshToken, profile, done) {

        var options = {
            criteria: {
                'google.id': profile.id
            },
            newUserInfo: {
                name: profile.displayName,
                email: profile.emails[0].value,
                username: profile.username,
                provider: 'google',
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