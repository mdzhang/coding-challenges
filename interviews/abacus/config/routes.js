// Modules
var express = require('express');
var router = express.Router();
var passport = require('passport');

// Dependencies
var auth = require('../config/auth');

// Models
var ShoppingList = require('../app/models/ShoppingList');
var ShoppingListItem = require('../app/models/ShoppingListItem');

// Controllers
var ShoppingListController = require('../app/controllers/ShoppingListController');
var ShoppingListItemController = require('../app/controllers/ShoppingListItemController');
var UserController = require('../app/controllers/UserController');

// Route Middlewares
var shoppingListAuth = [auth.requiresLogin, auth.shoppingList.hasAuthorization];
var shoppingListItemAuth = [auth.requiresLogin, auth.shoppingListItem.hasAuthorization];

router.get('/home', function(req, res, next) {
    res.redirect('/');
});

// Home page is a single angular app.
router.get('/', auth.requiresLogin, function(req, res, next) {
    res.render('index', { title: 'Group Shopping List', user: req.user });
});

// User routes
router.get('/user/login', UserController.login);
router.get('/user/logout', UserController.logout);

// Auth routes
router.get('/auth/github', passport.authenticate('github'));
router.get('/auth/github/callback',
    passport.authenticate('github', { failureRedirect: '/user/login' }),
    function(req, res) {
        // Successful authentication, redirect home.
        res.redirect('/');
    });

router.get('/auth/google', passport.authenticate('google', { scope: ['profile', 'email'] }));
router.get('/auth/google/callback',
    passport.authenticate('google', {
        successRedirect: '/',
        failureRedirect: '/user/login'
    }));

// ShoppingList routes
router.get('/shoppingList/createJson', auth.requiresLogin, ShoppingListController.createJson);
router.get('/shoppingList/getJson', shoppingListAuth, ShoppingListController.getJson);
router.get('/shoppingList/listJson', auth.requiresLogin, ShoppingListController.listJson);

// ShoppingListItem routes
router.get('/shoppingListItem/createJson', auth.requiresLogin, ShoppingListItemController.createJson);
router.get('/shoppingListItem/getJson', shoppingListItemAuth, ShoppingListItemController.getJson);
router.get('/shoppingListItem/listJson', auth.requiresLogin, ShoppingListItemController.listJson);
router.get('/shoppingListItem/updateJson', shoppingListItemAuth, ShoppingListItemController.updateJson);

module.exports = router;