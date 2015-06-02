/**
 * Modules
 */
var express        = require('express');            // node.js lightweight framework
var mongoose       = require('mongoose');           // mongodb object modeling
var passport       = require('passport');           // authentication middleware
var bodyParser     = require('body-parser');        // body parsing middleware
var cookieParser   = require('cookie-parser');      // cookie parsing middleware
var methodOverride = require('method-override');
var session        = require('express-session');    // session middleware
var logger         = require('morgan');             // HTTP request logger middleware
var flash          = require('connect-flash');      // flash message middleware

var app            = express();

/**
 * Database (MongoDB)
 */
require('./config/db');

/**
 * Express App Configuration
 */

// Set the path for static files. E.g. /public/img will be /img for users.
app.use(express.static(__dirname + '/public'));

app.use(cookieParser());

// For parsing POST bodies in json and x-www-form-url-encoded forms.
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Explicitly set session variables.
// see more at https://www.npmjs.com/package/express-session
app.use(session({
    secret: 'AonDor',           // used to sign/hash session ID cookie
    saveUninitialized: true,    // force save uninitialized (read: new but not modified) sessions to the store
    resave: true                // save session to the store, even if uninitialized
}));

app.use(passport.initialize());
app.use(passport.session());

// override with the X-HTTP-Method-Override header in the request. simulate DELETE/PUT
app.use(methodOverride('X-HTTP-Method-Override'));

// TODO: uncomment after placing favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));

app.use(logger('dev'));

app.use(flash());

// Set default view directory and engine.
app.set('views', __dirname + '/app/views');
app.set('view engine', 'jade');

/**
 * Routes (use ./app/routes.js)
 *
 * NB: This MUST come after the Express app config so as to not break Passport auth.
 *     (see http://passportjs.org/guide/configure/)
 */
var routes = require('./config/routes');
app.use('/', routes);

/**
 * Authentication (Passport)
 */
require('./config/passport');

/**
 * App start up
 */

// Set our port.
var port = process.env.PORT || 8080;

// Startup our app at http://localhost:8080.
app.listen(port);

// Shoutout to the user.
console.log('Group shopping list app started on port ' + port);

// Expose app.
module.exports = app;