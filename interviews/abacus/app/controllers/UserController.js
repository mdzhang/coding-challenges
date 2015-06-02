
exports.login = function(req, res) {
    res.render('user/login', {
        title: 'Login'
    });
};

exports.logout = function(req, res) {
    req.logout();
    res.redirect('/user/login');
};
